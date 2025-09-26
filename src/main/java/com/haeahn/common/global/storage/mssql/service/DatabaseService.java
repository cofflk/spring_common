package com.haeahn.common.global.storage.mssql.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haeahn.common.global.storage.mssql.config.ConnectionPool;
import com.haeahn.common.global.storage.mssql.repository.ProcedureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DatabaseService {
    private final ConnectionPool conn;
    private final ProcedureRepository proc;

    public enum ProcResultType {
        DATATABLE,  // 단일 ResultSet
        DATASET,    // 여러 ResultSet
        NONE        // 결과 없음
    }

    public ProcResultType getResultType(Object result) {
        if (result instanceof List<?> list) {
            if (list.isEmpty()) {
                return ProcResultType.NONE;
            }
            Object first = list.get(0);
            if (first instanceof Map<?, ?>) {
                return ProcResultType.DATATABLE;
            } else if (first instanceof List<?>) {
                return ProcResultType.DATASET;
            }
        }
        return ProcResultType.NONE;
    }
    /*
    e.g.
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> deleteToken(String refreshToken) {
        try {
            SqlParameterSource params = new MapSqlParameterSource()
                    .addValue("REF_TOKEN", refreshToken);
            Object result = dt.result("org", "USP_DEL_LOGIN_REFRESH_TOKEN", params);

            if (dt.getResultType(result) == DatabaseService.ProcResultType.DATATABLE) {
                return (List<Map<String, Object>>) result;
            }
            return Collections.emptyList();
        } catch (Exception e) {
            throw e;
        }
    }
     */

    public Object result(DataSource ds, String procName, SqlParameterSource params) {
        try {
            Map<String, Object> result = proc.executeProcedure(ds, procName, params);
            List<List<Map<String, Object>>> resultSets = new ArrayList<>();

            for (Map.Entry<String, Object> entry : result.entrySet()) {
                String key = entry.getKey();
                if (key.startsWith("#result-set-")) {
                    @SuppressWarnings("unchecked")
                    List<Map<String, Object>> rs = (List<Map<String, Object>>) entry.getValue();
                    resultSets.add(rs);
                }
            }
            if (resultSets.size() == 1) {
//            return resultSets.get(0); // 단일 ResultSet일 경우 리스트만 반환
                return resultSets.getFirst(); // 단일 ResultSet일 경우 리스트만 반환
            } else {
                return resultSets;        // 여러 ResultSet이면 배열로 반환
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Object result(String server, String db, String procName, SqlParameterSource params) {
        try {
            DataSource ds = conn.getDataSource(server, db);
//        return proc.executeProcedure(ds, procName, params).get("#result-set-1");

            return result(ds, procName, params);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Object result(String db, String procName, SqlParameterSource params) {
        try {
            DataSource ds = conn.getDataSource("default", db);
//        return proc.executeProcedure(ds, procName, params).get("#result-set-1");

            return result(ds, procName, params);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // datatable to dto
    public <T> List<T> mapperToDto(List<Map<String, Object>> result, Class<T> dto) {
        if (dto == null) throw new IllegalArgumentException("DTO class must not be null");
        if (result == null || result.isEmpty()) return Collections.emptyList();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // null 무시

        try {
            // DTO 클래스의 @JsonProperty 값이나 필드 이름을 수집
            Set<String> validKeys = Arrays.stream(dto.getDeclaredFields())
                    .map(field -> {
                        JsonProperty prop = field.getAnnotation(JsonProperty.class);
                        return prop != null ? prop.value() : field.getName();
                    })
                    .collect(Collectors.toSet());

            return result.stream()
                    .filter(Objects::nonNull) // null row 제거
                    .map(row -> {
                        // DTO에 존재하는 필드만 필터링
                        Map<String, Object> filtered = row.entrySet().stream()
                                .filter(entry -> validKeys.contains(entry.getKey()))
                                //.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                                .collect(Collectors.toMap(
                                        Map.Entry::getKey,
                                        entry -> {
                                            Object value = entry.getValue();
                                            Object defaultValue = getDefaultForField(dto, entry.getKey());
                                            return value != null ? value.toString() : (defaultValue != null ? defaultValue : "");
                                        }
                                        //(existing, replacement) -> replacement // key 충돌 시 덮어쓰기
                                ));

                        return objectMapper.convertValue(filtered, dto);
                    })
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Result is not of type List<Map<String, Object>> or mapping failed", e);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * DTO 필드 타입에 따라 기본값 반환
     */
    private Object getDefaultForField(Class<?> dto, String fieldName) {
        try {
            Field field = Arrays.stream(dto.getDeclaredFields())
                    .filter(f -> {
                        JsonProperty prop = f.getAnnotation(JsonProperty.class);
                        String name = (prop != null) ? prop.value() : f.getName();
                        return name.equals(fieldName);
                    })
                    .findFirst()
                    .orElse(null);

            if (field == null) return null;

            Class<?> type = field.getType();
            if (type.equals(int.class) || type.equals(Integer.class)) return 0;
            if (type.equals(boolean.class) || type.equals(Boolean.class)) return false;
            if (type.equals(long.class) || type.equals(Long.class)) return 0L;
            if (type.equals(double.class) || type.equals(Double.class)) return 0.0;
            return ""; // 나머지 타입은 빈 문자열
        } catch (Exception e) {
            return ""; // 안전하게 기본값 처리
        }
    }
}

//package com.haeahn.common.global.storage.cache.redis.reference;
//
//
//import com.haeahn.common.global.config.env.ConfigReader;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.util.Map;
//
//@Repository
//@RequiredArgsConstructor
//public class RedisRepository {
//    private final ConfigReader configReader;
//    private final Map<String, RedisTemplate<String, Object>> redisTemplates;
//
//    private RedisTemplate<String, Object> getTemplate(String dbName) {
//        RedisTemplate<String, Object> template = redisTemplates.get(dbName);
//        if (template == null) {
//            throw new IllegalArgumentException("Redis database not found: " + dbName);
//        }
//        return template;
//    }
//
////    opsForValue	Strings를 쉽게 Serialize / Deserialize 해주는 Interface
////    opsForList	List를 쉽게 Serialize / Deserialize 해주는 Interface
////    opsForSet	Set를 쉽게 Serialize / Deserialize 해주는 Interface
////    opsForZSet	ZSet를 쉽게 Serialize / Deserialize 해주는 Interface
////    opsForHash	Hash를 쉽게 Serialize / Deserialize 해주는 Interface
//
//    public void set(String dbName, String key, Object value) {
//        RedisTemplate<String, Object> template = getTemplate(dbName);
//        template.opsForValue().set(key, value);
//    }
//
//    public Object get(String dbName, String key) {
//        RedisTemplate<String, Object> template = getTemplate(dbName);
//        return template.opsForValue().get(key);
//    }
//
//    public void delete (String dbName, String key) {
//        getTemplate(dbName).delete(key);
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
/////**
//// * 리스트에 접근하여 다양한 연산을 수행합니다.
//// *
//// * @return ListOperations<String, Object>
//// */
////public ListOperations<String, Object> getListOperations() {
////    return this.redisTemplate().opsForList();
////}
////
/////**
//// * 단일 데이터에 접근하여 다양한 연산을 수행합니다.
//// *
//// * @return ValueOperations<String, Object>
//// */
////public ValueOperations<String, Object> getValueOperations() {
////    return this.redisTemplate().opsForValue();
////}
////
////
/////**
//// * Redis 작업중 등록, 수정, 삭제에 대해서 처리 및 예외처리를 수행합니다.
//// *
//// * @param operation
//// * @return
//// */
////public int executeOperation(Runnable operation) {
////    try {
////        operation.run();
////        return 1;
////    } catch (Exception e) {
////        System.out.println("Redis 작업 오류 발생 :: " + e.getMessage());
////        return 0;
////    }
////}

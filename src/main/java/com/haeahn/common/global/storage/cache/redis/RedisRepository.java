package com.haeahn.common.global.storage.cache.redis;

import com.haeahn.common.global.config.properties.PropertiesReader;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RedisRepository {
    private final ObjectMapper objectMapper;

    private final PropertiesReader propertiesReader;
    private final RedisTemplate<String, Object> redisTemplate;
    private String serverName;

    @PostConstruct
    public void init() {
        serverName = propertiesReader.getProperty("spring.application.name");
    }


//    공통 start ==========
//        newKey = {serverName}:{prefix1}:{prefix2}:{localKey}

//    private String keygen(String prefix1, String prefix2, String key, String type) {
//        return String.format("%s:%s:%s:%s:%s", serverName, prefix1, prefix2, type, key);
//    }
//    private String keygen(String prefix1, String prefix2, String key) {
//        return keygen(prefix1, prefix2, key, "value");
//    }
//    private String keygenHash(String prefix1, String prefix2, String key) {
//        return keygen(prefix1, prefix2, key, "hash");
//    }

    private String keygen(String... keys) {
        String joinedKey = String.join(":", keys);
        return String.format("%s:%s", serverName, joinedKey);
    }
    private String SharedKeygen(String SharedServerName, String... keys) {
        String joinedKey = String.join(":", keys);
        return String.format("%s:%s", SharedServerName, joinedKey);
    }

    /**
     * redis data 삭제
     * @param key
     */
    public void delete (String key) { redisTemplate.delete(key); }
    public void delete (String... keys) { delete(keygen(keys)); }

    /**
     * redis key 존재 여부
     * @param key
     * @return
     */
    public boolean exists (String key) {
        try {
            return Boolean.TRUE.equals(redisTemplate.hasKey(key));
        } catch (RedisConnectionFailureException e) {
            throw e;
        }
    }
    public boolean exists (String... keys) {
        try {
            return exists(keygen(keys));
        } catch (RedisConnectionFailureException e) {
            throw e;
        }
    }

//    String ==========

    /**
     * set String value
     * @param value
     * @param expire
     * @param key
     */
    public void setValue(Object value, int expire, String key) {
        try {
            redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(expire));
        } catch (RedisConnectionFailureException e) {
            throw e;
        }
    }
    public void setValue(Object value, int expire, String... keys) {
        try {
            setValue(value, expire, keygen(keys));
        } catch (RedisConnectionFailureException e) {
            throw e;
        }
    }

    /**
     * get String value
     * @param key
     * @return
     */
    public Optional<String> getValue(String key) {
        try {
            Object value = redisTemplate.opsForValue().get(key);
            return Optional.ofNullable(value != null ? value.toString() : null);
        } catch (RedisConnectionFailureException e) {
            throw e;
        }
    }
    public Optional<String> getValue(String... keys) {
        try {
            return getValue(keygen(keys));
        } catch (RedisConnectionFailureException e) { throw e; }
    }

    /**
     * get String value - 다른 서버에서 등록한 data
     * @param sharedServerName
     * @param keys
     * @return
     */
    public Optional<String> sharedGetValue(String sharedServerName, String... keys) {
        try {
            return getValue(SharedKeygen(sharedServerName, keys));
        } catch (RedisConnectionFailureException e) { throw e; }
    }
    
//    hash ==========

    /**
     * set Hash value
     * @param value
     * @param expire
     * @param key
     */
    public void setHashValue(Object value, int expire, String key) {
        try {
            HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
            Map<String, Object> map = (Map<String, Object>) convertToMap(value);
            hashOperations.putAll(key, map);
            redisTemplate.expire(key, Duration.ofSeconds(expire));
        } catch (RedisConnectionFailureException e) {
            throw e;
        }
    }
    public void setHashValue(Object value, int expire, String... keys) {
        try {
            setHashValue(value, expire, keygen(keys));
        } catch (RedisConnectionFailureException e) {
            throw e;
        }
    }

    /**
     * get hash value
     * @param key
     * @return
     */
    public Map<Object, Object> getHashValue(String key) {
        try {
            return redisTemplate.opsForHash().entries(key);
        } catch (RedisConnectionFailureException e) {
            throw e;
        }
    }
    public Map<Object, Object> getHashValue(String... keys) {
        try {
            return getHashValue(keygen(keys));
        } catch (RedisConnectionFailureException e) {
            throw e;
        }
    }

    /**
     * get hash value - 다른 서버에서 등록한 data
     * @param sharedServerName
     * @param keys
     * @return
     */
    public Map<Object, Object> sharedGeHashValue(String sharedServerName, String... keys) {
        try {
            return getHashValue(SharedKeygen(sharedServerName, keys));
        } catch (RedisConnectionFailureException e) { throw e; }
    }

    /**
     * add item (key, value) - hash value
     * @param itemKey
     * @param value
     * @param key
     */
    public void addHashItem (String itemKey, String value, String key) {
        try {
            if (this.exists(key)) {
                redisTemplate.opsForHash().put(key, itemKey, value);
            }
        } catch (RedisConnectionFailureException e) {
            throw e;
        }
    }

    public void addHashItem (String itemKey, String value, String... keys) {
        try {
            addHashItem(itemKey, value, keygen(keys));
        } catch (RedisConnectionFailureException e) {
            throw e;
        }
    }

    /**
     * delete item - hash value
     * @param itemKey
     * @param key
     */
    public void removeHashItem(String itemKey, String key){
        try {
            redisTemplate.opsForHash().delete(key, itemKey);
        } catch (RedisConnectionFailureException e) {
            throw e;
        }
    }
    public void removeHashItem(String itemKey, String... keys){
        try {
            removeHashItem(itemKey, keygen(keys));
        } catch (RedisConnectionFailureException e) {
            throw e;
        }
    }

    private Map<?, ?> convertToMap(Object obj) {
        return objectMapper.convertValue(obj, Map.class);
    }
}
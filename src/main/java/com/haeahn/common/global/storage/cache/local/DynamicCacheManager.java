package com.haeahn.common.global.storage.cache.local;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component
public class DynamicCacheManager {
    // 캐시 이름별로 Cache 객체를 저장하는 Map
    private final ConcurrentHashMap<String, Cache<String, Object>> cacheMap = new ConcurrentHashMap<>();

    public Cache<String, Object> createCustomCache(long ttlMinutes, long maxSize) {
        return Caffeine.newBuilder()
                .expireAfterWrite(ttlMinutes, TimeUnit.MINUTES)
                .maximumSize(maxSize)
                .recordStats()
                .build();
    }

//    캐시 이름을 지정해서 생성 - 해당 캐시가 있으면 무시
    public Cache<String, Object> createCustomNamedCache(String cacheName, long ttlMinutes, long maxSize) {
        return cacheMap.computeIfAbsent(cacheName, name ->
                Caffeine.newBuilder()
                        .expireAfterWrite(ttlMinutes, TimeUnit.MINUTES)
                        .maximumSize(maxSize)
                        .recordStats()
                        .build()
        );
    }

//    /**
//     * 캐시 이름을 지정하여 캐시 생성 또는 재설정 (기존 캐시가 있으면 새로운 설정으로 교체)
//     */
//    public Cache<String, Object> updateCustomNamedCache(String cacheName, long ttlMinutes, long maxSize) {
//        return cacheMap.compute(cacheName, (name, existingCache) -> {
//            // 기존 캐시가 있으면 정리
//            if (existingCache != null) {
//                existingCache.invalidateAll();
//            }
//
//            // 새로운 설정으로 캐시 생성
//            return Caffeine.newBuilder()
//                    .expireAfterWrite(ttlMinutes, TimeUnit.MINUTES)
//                    .maximumSize(maxSize)
//                    .recordStats()
//                    .build();
//        });
//    }

    /**
     * 캐시 설정 변경 (기존 캐시가 있을 때만)
     */
    public boolean updateCustomCache(String cacheName, long ttlMinutes, long maxSize) {
        Cache<String, Object> existingCache = cacheMap.get(cacheName);
        if (existingCache == null) {
            return false; // 캐시가 존재하지 않음
        }

        // 기존 캐시 정리 후 새로운 설정으로 교체
        existingCache.invalidateAll();
        Cache<String, Object> newCache = Caffeine.newBuilder()
                .expireAfterWrite(ttlMinutes, TimeUnit.MINUTES)
                .maximumSize(maxSize)
                .recordStats()
                .build();

        cacheMap.put(cacheName, newCache);
        return true;
    }

//    등록된 캐시 목록
    public Object getCacheNames() {
        return cacheMap.keySet();
    }

//    캐시 존재 체크
    public boolean cacheExists(String cacheName) {
        return cacheMap.containsKey(cacheName);
    }

    // 캐시 value 삭제
    public void evictCacheValue(String cacheName, String key) {
        Cache<String, Object> cache = cacheMap.get(cacheName);
        if (cache != null) {
            cache.invalidate(key);
        }
    }

    // 캐시 조회
    public Cache<String, Object> getCache(String cacheName) {
        return cacheMap.get(cacheName);
    }

    // 캐시 삭제
    public void evictCache(String cacheName) {
        Cache<String, Object> cache = cacheMap.remove(cacheName);
        if (cache != null) {
            cache.invalidateAll();
        }
    }

    // 모든 캐시 삭제
    public void clearAllCaches() {
        cacheMap.values().forEach(Cache::invalidateAll);
        cacheMap.clear();
    }
}

/*
* Usage

Cache<String, Object> tempCache = dynamicCacheManager.createDynamicCache(30, 100);
tempCache.put("someKey", "someValue");

String value = (String) tempCache.getIfPresent("someKey");
 */
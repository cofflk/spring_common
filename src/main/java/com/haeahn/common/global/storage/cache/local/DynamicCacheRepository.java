package com.haeahn.common.global.storage.cache.local;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DynamicCacheRepository {
    private final DynamicCacheManager dynamicCacheManager;


// dynamic cache start ==========
    public void setDynamicCache(String cacheName, String key, Object val) {
        Cache<String, Object> customCache = dynamicCacheManager.createCustomNamedCache(cacheName, 10, 100);
        customCache.put(key, val);
    }

    public Object getDynamicCache(String cacheName, String key) {
//        if (!dynamicCacheManager.cacheExists(cacheName)) return null;
        Cache<String, Object> cache = dynamicCacheManager.getCache(cacheName);
        return cache != null ? cache.getIfPresent(key) : null;
    }
// dynamic cache end ==========










}

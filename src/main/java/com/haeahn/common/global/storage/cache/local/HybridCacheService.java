package com.haeahn.common.global.storage.cache.local;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class HybridCacheService {
    private final CacheManager cacheManager;
    private final DynamicCacheManager dynamicCacheManager;


    //    common cache methods
    public Map<String, Object> getAllCaches() {
        Map<String, Object> cacheList = new ConcurrentHashMap<>();
        cacheList.put("namedCaches", cacheManager.getCacheNames());
        cacheList.put("dynamicCaches", dynamicCacheManager.getCacheNames());
        return cacheList;
    }

    public Object getCacheStat(String cacheName) {
        var staticCache = cacheManager.getCache(cacheName);
        if (staticCache != null) {
            if (staticCache instanceof CaffeineCache caffeineCache) {
                Cache<Object, Object> nativeCache = caffeineCache.getNativeCache();
                return getCacheDetails(nativeCache);
            }
        }
        else {
            Cache<String, Object> dynamicCache = dynamicCacheManager.getCache(cacheName);
            return getCacheDetails(dynamicCache);
        }
        return null;
    }

    public Object getCacheDetails(Cache<?, ?> cache) {
        Map<String, Object> stats = new ConcurrentHashMap<>();
        if (cache == null) return null;
        // 통계 정보 (recordStats()가 설정된 경우에만 사용 가능)
        CacheStats cacheStats = cache.stats();
        if (cacheStats != null) {
            Map<String, Object> statistics = new ConcurrentHashMap<>();

            // 히트/미스 통계
            statistics.put("hitCount", cacheStats.hitCount());
            statistics.put("missCount", cacheStats.missCount());
            statistics.put("hitRate", cacheStats.hitRate());

            // 로드 통계
            statistics.put("loadSuccessCount", cacheStats.loadSuccessCount());
            statistics.put("loadFailureCount", cacheStats.loadFailureCount());
            statistics.put("loadFailureRate", cacheStats.loadFailureRate());
            statistics.put("totalLoadTime", cacheStats.totalLoadTime());
            statistics.put("averageLoadPenalty", cacheStats.averageLoadPenalty());

            // 제거 통계
            statistics.put("evictionCount", cacheStats.evictionCount());
            statistics.put("evictionWeight", cacheStats.evictionWeight());

            // 기타 통계
            statistics.put("requestCount", cacheStats.requestCount());

            stats.put("statistics", statistics);
        } else {
            stats.put("statistics", "Statistics not available (recordStats() not enabled)");
        }
        return stats;
    }

}

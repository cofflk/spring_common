//package com.haeahn.common.global.storage.cache.local.reference;
//
//import com.github.benmanes.caffeine.cache.Caffeine;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.caffeine.CaffeineCacheManager;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.concurrent.TimeUnit;
//
//@EnableCaching
//@Configuration
//public class DefaultCacheManager {
//
////    @Bean
////    public Caffeine caffeineConfig() {
////        return Caffeine.newBuilder()
////                .expireAfterWrite(60, TimeUnit.MINUTES)
////                .maximumSize(1000);
////    }
//
////      1. 기본 cacheManager 사용
////      지정한 cacheName 만 사용 - "defaultCache1", "defaultCache2"
////      e.g. 1) @Cachable("defaultCache1")
////      e.g. 2) @Cacheable(value = "defaultCache2", key = "#key1 + ':' + #key2")
////    @Bean
//    public CacheManager cacheManager() {
//        CaffeineCacheManager cacheManager = new CaffeineCacheManager("defaultCache1", "defaultCache2");
//        cacheManager.setCaffeine(
//                Caffeine.newBuilder()
//                        .expireAfterWrite(10, TimeUnit.MINUTES)
//                        .maximumSize(1000)
//                        .recordStats()
//        );
//        return cacheManager;
//    }
//}
//
///*
//Usage
//@Cacheable(value = "weatherCache", key = "#city")
// */
//
///*
//// https://github.com/ben-manes/caffeine/wiki/Specification
//
//Caffeine.newBuilder() parameter
//
//initialCapacity: 내부 해시 테이블의 최소한의 크기를 설정합니다.
//* maximumSize: 캐시에 포함할 수 있는 최대 엔트리 수를 지정합니다.
//maximumWeight: 캐시에 포함할 수 있는 엔트리의 최대 무게를 지정합니다.
//expireAfterAccess: 캐시가 생성된 후, 해당 값이 가장 최근에 대체되거나 마지막으로 읽은 후 특정 기간이 경과하면 캐시에서 자동으로 제거되도록 지정합니다.
//* expireAfterWrite: 항목이 생성된 후 또는 해당 값을 가장 최근에 바뀐 후 특정 기간이 지나면 각 항목이 캐시에서 자동으로 제거되도록 지정합니다.
//refreshAfterWrite: 캐시가 생성되거나 마지막으로 업데이트된 후 지정된 시간 간격으로 캐시를 새로 고칩니다.
//weakKeys: 키를 weak reference로 지정합니다. (GC에서 회수됨)
//weakValues: Value를 weak reference로 지정합니다. (GC에서 회수됨)
//softValues: Value를 soft reference로 지정합니다. (메모리가 가득 찼을 때 GC에서 회수됨)
//* recordStats: 캐시에 대한 Statics를 적용합니다.
//
// */

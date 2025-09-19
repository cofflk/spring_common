package com.haeahn.common.global.storage.cache.local;

import lombok.Getter;


// 서비스에서 사용하는 모든 캐시를 enum 으로 관리할 경우
@Getter
public enum NamedCacheType {

    MINUTE_CACHES1("minCache1", 10, 100),
    MINUTE_CACHES2("minCache2", 10, 200);

    private final String cacheName;
    private long ttlMinutes;
    private long maxSize;

    NamedCacheType(String cacheName, long ttlMinutes, long maxSize) {
        this.cacheName = cacheName;
        this.ttlMinutes = ttlMinutes;
        this.maxSize = maxSize;
    }
}

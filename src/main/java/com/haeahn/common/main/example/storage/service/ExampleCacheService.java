package com.haeahn.common.main.example.storage.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ExampleCacheService {


    @Cacheable(value = "minuteCache", key = "#key")
    public Object getMinuteCache(String key, Object val) {
        try {
            // 캐시가 없을 경우 실행
            System.out.println("no cache");
            return val;
        }
        catch (Exception e) {
            throw e;
        }
    }

//    캐시 등록 조건 설정
//    @Cacheable(value = "minuteCache", key = "#key", condition = "#chk) => chk 값이 true 인 경우만 적용
//    public Object getMinuteCacheWithCondition(String key, boolean chk, Object val) {

    @Cacheable(value = "minuteCache", key = "#key", condition = "#key = 'id'")
    public Object getMinuteCacheWithCondition(String key, Object val) {
        try {
            // 캐시가 없을 경우 실행
            System.out.println("no cache");
            return val;
        }
        catch (Exception e) {
            throw e;
        }
    }

    @Cacheable(value = "dailyCache", key = "#key")
    public Object getDailyCache(String key, Object val) {
        try {
            // 캐시가 없을 경우 실행
            return val;
        }
        catch (Exception e) { throw e;}
    }


    @CachePut(value = "minuteCache", key = "#key")
    public Object setMinuteCache(String key, Object val) {
        try {
            System.out.println("update cache");
            return val;
        }
        catch (Exception e) {
            throw e;
        }
    }

//    캐시 삭제
    @CacheEvict(value = "minuteCache", key = "#key")
    public void removeMinuteCache(String key, Object val) {
        try {
            System.out.println("remove each cache");
        }
        catch (Exception e) {
            throw e;
        }
    }

//    minuteCache 의 내용을 모두 삭제
//    @Scheduled(fixedRate = 1000) // 1초마다 실행
//    @Scheduled(fixedRate = 5000, initialDelay = 3000) // 3초 대기시간 후 5초마다 실행
    @Scheduled(cron = "* * 2 * *") // 매일 02시에 실행 - 초, 분, 시간(0-23), 일, 월, 요일(0-6)
    @CacheEvict(value = "minuteCache", allEntries = true)
    public void removeAllMinuteCache(String key, Object val) {
        try {
            System.out.println("remove all cache");
        }
        catch (Exception e) {
            throw e;
        }
    }
}

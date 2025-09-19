package com.haeahn.common.global.storage.cache.local;


import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NamedCacheRepository {
    private final CacheManager cacheManager;
//      구현제 사용 방법 start

//    @Cacheable(value = "default", key = "#key1")
//    public Object getCache1(String key1, String val) {
//            추가 로직 - 캐시가 없으면 등록, 있으면 조회 + 실행할 내용
//            return val;
//    }
//
//    @Cacheable(value = "dailyCache", key = "#key")
//    public Object getMinuteCache(String key, Object val) {
//            추가 로직 - 캐시가 없으면 등록, 있으면 조회 + 실행할 내용
//            return val;
//    }
//
//    @Cacheable(value = "dailyCache", key = "#key")
//    public Object getDailyCache(String key, Object val) {
//            추가 로직 - 캐시가 없으면 등록, 있으면 조회 + 실행할 내용
//            return val;
//    }
//
//    @CachePut(value = "users", key = "#user.id")
//    public User updateUser(User user) {
//        return userRepository.save(user); // 메소드 결과를 강제로 캐시에 등록
//    }
//
//    @CacheEvict(value = "dailyCache", key = "#user.id")
//    public User deleteUser(User user) {
//        return userRepository.save(user); // 캐시 삭제와 같이 실행
//    }
//
//    allEntries : 캐시의 전체 항목 삭제
//    @CacheEvict(value = "dailyCache", allEntries = true)
//    public User deleteAllUser() {
//        return userRepository.save(user); // 캐시 삭제와 같이 실행
//    }


//      구현제 사용 방법 start


    // named cache start ==========
    public void setNamedCache(String cacheName, String key, Object val) {
        cacheManager.getCache(cacheName).put(key, val);
    }

    public Object getNamedCache(String cacheName, String key) {
        var cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            var wrapper = cache.get(key);
            return wrapper != null ? wrapper.get() : null;
        }
        return null;
    }

    // 캐시 삭제 (특정 키 제거)
    public void evictNamedCache(String cacheName, String key) {
        var cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.evict(key);
        }
    }

    // 캐시 전체 삭제 (이름에 해당하는 캐시 모두 비우기)
    public void clearNamedCache(String cacheName) {
        var cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.clear();
        }
    }
// named cache end ==========
}

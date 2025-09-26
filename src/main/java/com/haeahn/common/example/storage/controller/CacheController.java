package com.haeahn.common.example.storage.controller;

import com.haeahn.common.example.storage.service.ExampleCacheService;
import com.haeahn.common.global.storage.cache.local.DynamicCacheRepository;
import com.haeahn.common.global.storage.cache.local.HybridCacheService;
import com.haeahn.common.global.storage.cache.local.NamedCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/example/storage/cache")
@RequiredArgsConstructor
public class CacheController {
    private final NamedCacheRepository namedCacheRepository;
    private final DynamicCacheRepository dynamicCacheRepository;
    private final HybridCacheService hybridCacheService;

    private final ExampleCacheService exampleCacheService;


//    @RequestMapping("/test")
    @GetMapping()
    public String test() {
        System.out.println("/redis/test");
        return "redis test";
    }


    @GetMapping("/getStaticCache1/{value}")
    public ResponseEntity<Object> getStaticCache1(@PathVariable("value") String val) {
        try {

            Object tmp1 = exampleCacheService.getMinuteCache("minKey1", val);
            Object tmp2 = exampleCacheService.getDailyCache("dayKey1", val);

            Map<String, String> map1 = new ConcurrentHashMap<>();
            map1.put("tmp1", (String) tmp1);
            map1.put("tmp2", (String) tmp2);

            return ResponseEntity.ok(map1);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/getStaticCache2/{value}")
    public ResponseEntity<Object> getStaticCache2(@PathVariable("value") String val) {
        try {
            namedCacheRepository.setNamedCache("cacheName1", "customKey1", val);

            String tmp1 = (String) namedCacheRepository.getNamedCache("cacheName1", "customKey1");
            System.out.println("tmp1: " + tmp1);

            Map<String, String> map1 = new ConcurrentHashMap<>();
            map1.put("tmp1", tmp1);
            return ResponseEntity.ok(map1);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }



    @GetMapping("/getDynamicCache/{value}")
    public ResponseEntity<Object> getDynamicCache(@PathVariable("value") String val) {
        try {
            dynamicCacheRepository.setDynamicCache("customCache1", "customCacheKey1", val);

            String tmp = (String) dynamicCacheRepository.getDynamicCache("customCache1", "customCacheKey1");
            return ResponseEntity.ok(tmp);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    @GetMapping("/getCaches")
    public ResponseEntity<Object> getCaches() {
        try {
            Object tmp = hybridCacheService.getAllCaches();
            return ResponseEntity.ok(tmp);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    @RequestMapping("/stat/{cacheName}")
    public ResponseEntity<Object> getStatus(@PathVariable("cacheName") String cacheName) {
        System.out.println("get status");
//        Object data = redisRepository.getValue("key1", "key2");
        Object tmp = hybridCacheService.getCacheStat(cacheName);
        return ResponseEntity.ok(tmp);
    }








//    @RequestMapping("/set2")
//    public String setValueWithCache() {
//        System.out.println("set redis with cache");
////        redisRepository.setValue("val1", 100, "key1", "key2");
//        return "ok";
//    }
//
//





}

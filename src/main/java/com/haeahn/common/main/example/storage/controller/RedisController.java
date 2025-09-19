package com.haeahn.common.main.example.storage.controller;

import com.haeahn.common.global.storage.cache.redis.RedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/example/storage/redis")
@RequiredArgsConstructor
public class RedisController {

//    private final RedisRepository repository;
    private final RedisRepository redisRepository;

    @RequestMapping("/test")
    public String test() {
        System.out.println("/redis/test");
        return "redis test";
    }

    @RequestMapping("/set1")
    public String setValue() {
        System.out.println("set redis");
        redisRepository.setValue("val1", 100, "key1", "key2");
        return "ok";
    }

    @RequestMapping("/get1")
    public ResponseEntity<Object> getValue() {
        System.out.println("get redis");
        Object data = redisRepository.getValue("key1", "key2");
        return ResponseEntity.ok(data);
    }

    @RequestMapping("/set2")
    public String setValueWithCache() {
        System.out.println("set redis with cache");
//        redisRepository.setValue("val1", 100, "key1", "key2");
        return "ok";
    }











    @RequestMapping("/redis2")
    public String redis2() {
        System.out.println("/redis2");
        try {
            if (!redisRepository.exists("test", "pre1", "key1")) {
                redisRepository.setValue("test", 20, "pre1", "key1", "a1");
            }
            else {
                System.out.println("string key exists");
            }
            Optional<String> val = redisRepository.getValue("test", "pre1", "key1");

            Map<String, String> map1 = new HashMap<>();
            map1.put("item1", "b1");
            map1.put("item2", "b2");
            map1.put("item3", "b3");

            if (!redisRepository.exists("test", "pre2", "key1")) {
                redisRepository.setHashValue(map1, 20, "test", "pre2", "key1");
            }
            else {
                System.out.println("map key exists");
                redisRepository.addHashItem("test", "pre2", "key1", "item5", "c1");
            }
            Map<Object, Object> map2 = redisRepository.getHashValue("test", "pre2", "key1");


            System.out.println("map1");
            System.out.println(map1);
            System.out.println("map2");
            System.out.println(map2);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redis2";
    }
}

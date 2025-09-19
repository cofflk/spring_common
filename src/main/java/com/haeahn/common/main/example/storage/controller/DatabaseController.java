package com.haeahn.common.main.example.storage.controller;

import com.haeahn.common.global.storage.mssql.service.DatabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example/storage/mssql")
@RequiredArgsConstructor
public class DatabaseController {
    private final DatabaseService dt;

    @RequestMapping("/test")
    public String test() {
        System.out.println("/db/test");
        return "db test";
    }

    @RequestMapping("/get")
    public String setValue() {
        System.out.println("set redis");
//        dt.result()
        return "ok";
    }

}

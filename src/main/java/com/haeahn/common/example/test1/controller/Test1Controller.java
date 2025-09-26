package com.haeahn.common.example.test1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example/test1")
@RequiredArgsConstructor
public class Test1Controller {

//    @RequestMapping(value = "/ex1", method = RequestMethod.POST)
    @PostMapping(value = "/ex1")
    public String test1() {
        System.out.println("/redis/test");

//        if (StringUtils.isBlank(name)) {
//            // 문자열이 null 또는 공백
//        }
//
//        if (CollectionUtils.isEmpty(dataMap)) {
//            // map이 null이거나 비어 있음
//        }
//
//        if (ObjectUtils.isAnyNull(user, token)) {
//            // 하나라도 null이면 true
//        }

        return "redis test";
    }

    @PostMapping(value = "/ex2")
    public String test2() {
        System.out.println("/example/test1/ex2");

//        "yyyy-MM-dd"	날짜 (연-월-일)	2025-06-21
//        "yyyy-MM-dd HH:mm"	날짜 + 시간 (시:분)	2025-06-21 14:30
//        "yyyyMMddHHmmss"	파일명 등에 사용	20250621143000

//        String formatted = DateUtils.format(LocalDateTime.now(), "yyyy-MM-dd HH:mm");
//        System.out.println("현재시간: " + formatted);
//
//        LocalDate date = DateUtils.parseToDate("2025-06-21", "yyyy-MM-dd");
//        System.out.println("파싱된 날짜: " + date);
//
//        long diff = DateUtils.daysBetween(LocalDate.of(2025, 6, 1), LocalDate.now());
//        System.out.println("일 수 차이: " + diff);
//
//// 현재 한국 시간
//        LocalDateTime nowKST = DateUtils.nowKSTDateTime();
//        System.out.println("KST 현재시간: " + DateUtils.format(nowKST, "yyyy-MM-dd HH:mm:ss"));
//
//// UTC → KST 변환
//        LocalDateTime utcTime = LocalDateTime.of(2025, 6, 21, 12, 0); // UTC 기준
//        LocalDateTime kstTime = DateUtils.convertUtcToKst(utcTime);
//        System.out.println("UTC 12:00 → KST: " + DateUtils.format(kstTime, "yyyy-MM-dd HH:mm:ss"));
//
//// KST → UTC 변환
//        LocalDateTime newUtc = DateUtils.convertKstToUtc(nowKST);
//        System.out.println("KST → UTC: " + DateUtils.format(newUtc, "yyyy-MM-dd HH:mm:ss"));

        return "redis test";
    }

}

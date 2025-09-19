//package com.haeahn.common.main.example.controller;
//
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@Tag(name = "Mongo DB API", description = "haeahn.com/vanilla")
//@RestController
//@RequestMapping("/test")
//public class MongoDbController {
//
//
////    @PostMapping("/getNavigation2/{empNo}/{modlCode}")
//    @RequestMapping("/mongo1")
//    ResponseEntity<?> getTest1(
//            @Parameter(description = "params1", example = "aaa") @PathVariable String empNo,
//            @Parameter(description = "params2", example = "bbb") @PathVariable String modlCode
//    ) {
////        SqlParameterSource params = new MapSqlParameterSource()
////                .addValue("EMP_NO", empNo)
////                .addValue("MODL_CD", modlCode)
////                .addValue("MENU_CD", "");
////        return ResponseEntity.ok(dt.result("aprv", "USP_GET_MENU", params));
//
//        return ResponseEntity.ok("");
//    }
//
//    @RequestMapping("/mongo2")
//    String getTest2() {
//        return "mongo2";
//    }
//
//
//}

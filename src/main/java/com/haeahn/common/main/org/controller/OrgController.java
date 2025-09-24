package com.haeahn.common.main.org.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Tag(name = "org", description = "조직도 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/org")
public class OrgController {

    @Operation(summary = "로그인 사용자 - 사번, ID", tags = { "auth" })
    @GetMapping("/dept")
    public ResponseEntity<Map<String, String>> getLoginEmpNo(HttpServletRequest request, HttpServletResponse response) {

        return null;
    }
}

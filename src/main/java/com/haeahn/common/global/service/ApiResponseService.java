package com.haeahn.common.global.service;

import com.haeahn.common.global.dto.ApiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ApiResponseService {
    public <T> ResponseEntity<ApiResponseDto<T>> success(T data, String message) {
        return ResponseEntity.ok(
                ApiResponseDto.<T>builder()
                        .success(true)
                        .message(message)
                        .data(data)
                        .status(HttpStatus.OK.value())
                        .build()
        );
    }

    public <T> ResponseEntity<ApiResponseDto<T>> failure(String message, HttpStatus status) {
        return ResponseEntity.ok(
                ApiResponseDto.<T>builder()
                        .success(false)
                        .message(message)
                        .status(status.value())
                        .build()
        );
    }
}

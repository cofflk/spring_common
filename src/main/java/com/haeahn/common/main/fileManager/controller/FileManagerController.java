package com.haeahn.common.main.fileManager.controller;

import com.haeahn.common.global.config.properties.PropertiesReader;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Tag(name = "file", description = "첨부파일 관리")
@RestController
//@RequiredArgsConstructor
@RequestMapping("/file")
public class FileManagerController {
    private final PropertiesReader propertiesReader;
    private final String uploadDir;

    public FileManagerController(PropertiesReader propertiesReader) {
        this.propertiesReader = propertiesReader;
        this.uploadDir = propertiesReader.getProperty("app.storage.path");
    }

    @Operation(summary = "첨부파일 업로드", tags = { "file" })
    @PostMapping("/upload")
//    public ResponseEntity<Map<String, String>> uploadFile(HttpServletRequest request, HttpServletResponse response) {
    public ResponseEntity<String> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        for (MultipartFile file : files) {
            if (file.isEmpty()) continue;

            try {
                String filePath = uploadDir + file.getOriginalFilename();
                file.transferTo(new File(filePath));
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 업로드 실패");
            }
        }
        return ResponseEntity.ok("OK");
    }

    @Operation(summary = "첨부파일 다운로드", tags = { "file" })
    @GetMapping("/download")
    public ResponseEntity<Map<String, String>> downloadFile(HttpServletRequest request, HttpServletResponse response) {

        return null;
    }
}

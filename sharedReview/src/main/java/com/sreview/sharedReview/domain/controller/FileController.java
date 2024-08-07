package com.sreview.sharedReview.domain.controller;

import com.sreview.sharedReview.domain.service.impl.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    private final FileService fileService;
    @GetMapping(value = "/{fileName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public Resource getImage(@PathVariable("fileName") String fileName) {
        return fileService.getImage(fileName);
    }
    @GetMapping(value = "/temp/{fileName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public Resource getTempImage(@PathVariable("fileName") String fileName) {
        return fileService.getTempImage(fileName);
    }

    @PostMapping(value = "/save/temp/image")
    public ResponseEntity<Map<String, String>> uploadTempImage(@RequestParam("file") MultipartFile file) {
        System.out.println("이미지 첨부 백엔드 실행. 클라이언트에서 받아온 데이터 : " + file.toString());
        Map<String, String> result = Map.of("savedName", fileService.uploadTempImage(file));
        System.out.println("클라이언트로 보내줄 데이터 : " + result);
        return ResponseEntity.ok(result) ;
    }
    @PostMapping(value = "/save/image")
    public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("file") MultipartFile file) {
        Map<String, String> result = Map.of("savedName", fileService.uploadImage(file));
        return ResponseEntity.ok(result) ;
    }
}

package com.sreview.sharedReview.domain.controller;

import com.sreview.sharedReview.domain.service.impl.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping(value = "/save/image")
    public String uploadImage(@RequestParam("file") MultipartFile file) {
//    public String uploadImage(@AuthenticationPrincipal String email, @RequestParam("file") MultipartFile file) {
//        log.info("email = {}", email);
        return fileService.uploadImage(file);
    }
}

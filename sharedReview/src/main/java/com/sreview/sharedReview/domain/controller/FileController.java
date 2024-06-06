package com.sreview.sharedReview.domain.controller;

import com.sreview.sharedReview.domain.service.impl.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;
    @GetMapping(value = "/{fileName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public Resource getImage(@PathVariable("fileName") String fileName) {
        return fileService.getImage(fileName);
    }

    @PostMapping(value = "/save/image", consumes = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public String uploadImage(@RequestParam("fileName")MultipartFile file) {
        return fileService.uploadImage(file);
    }
}

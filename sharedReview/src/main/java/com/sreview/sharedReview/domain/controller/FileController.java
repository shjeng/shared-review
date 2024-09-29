package com.sreview.sharedReview.domain.controller;

import com.sreview.sharedReview.domain.jpa.service.EditorRepoService;
import com.sreview.sharedReview.domain.service.impl.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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
    private final EditorRepoService editorRepoService;

    @GetMapping(value = "/{fileName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public Resource getImage(@PathVariable("fileName") String fileName) {
        return fileService.getImage(fileName);
    }

    @GetMapping(value = "/temp/{fileId}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public Resource getTempImage(@PathVariable("fileId") Long fileId) {
        return fileService.getImage(fileId);
    }

    // 에디터 파일 불러오기 로직
    @GetMapping("/editor/{fileId}")
    public ResponseEntity<Resource> getEditorImage(@PathVariable("fileId") Long fileId){
        Resource image = fileService.getImage(fileId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return ResponseEntity.ok().headers(headers).body(image);
    }

    @PostMapping(value = "/save/temp/image")
    public ResponseEntity<Map<String, String>> uploadTempImage(@RequestParam("file") MultipartFile file) {
        Map<String, String> result = Map.of("savedName", fileService.uploadTempImage(file));
        return ResponseEntity.ok(result) ;
    }
    @PostMapping(value = "/save/image")
    public ResponseEntity<Map<String, Object>> uploadImage(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(fileService.uploadImage(file)) ;
    }
}

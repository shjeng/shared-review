package com.sreview.sharedReview.domain.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileService {

    @Value("${file.path}")
    private String filePath;

    @Value("${file.url}")
    public Resource getImage(String fileName){
        try {
            return new FileSystemResource(filePath + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String uploadImage(MultipartFile file) {
        if(file.isEmpty()) return null;
        String originalFileName = file.getOriginalFilename();
        String ext = Objects.requireNonNull(originalFileName).substring(originalFileName.lastIndexOf(".")); // 확장자
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String saveFileName = uuid + ext;
        String savePath = filePath + saveFileName;

        try {
            file.transferTo(new File(savePath));
            return filePath + saveFileName;

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}

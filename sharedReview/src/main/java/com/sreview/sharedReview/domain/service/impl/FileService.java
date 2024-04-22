package com.sreview.sharedReview.domain.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    @Value("${file.path}")
    private String filePath;

    public Resource getImage(String fileName){
        try {
            return new FileSystemResource(filePath + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

package com.sreview.sharedReview.domain.service.impl;

import com.sreview.sharedReview.domain.common.customexception.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileService {

    @Value("${file.path}")
    private String filePath;

    @Value("${file.tempUrl}")
    private String tempUrl;
    @Value("${file.temp}")
    private String tempPath;
    @Value("${file.url}")
    private String fileUrl;

    @Value("${file.maximum}")
    private Long maximumFileSize;

    public Resource getImage(String fileName){
        try {
            return new FileSystemResource(filePath + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Resource getTempImage(String fileName){
        try {
            return new FileSystemResource(tempPath + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String uploadTempImage(MultipartFile file) {
        if(file.isEmpty()) return null;
        fileValidationCheck(file);
        String originalFileName = file.getOriginalFilename();
        String ext = Objects.requireNonNull(originalFileName).substring(originalFileName.lastIndexOf(".")); // 확장자
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String saveFileName = uuid + ext;
        String savePath = tempPath + saveFileName;

        try {
            file.transferTo(new File(savePath));
            return tempUrl + saveFileName;

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public String uploadImage(MultipartFile file) {
        if(file.isEmpty()) return null;
        fileValidationCheck(file);
        String originalFileName = file.getOriginalFilename();
        String ext = Objects.requireNonNull(originalFileName).substring(originalFileName.lastIndexOf(".")); // 확장자
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String saveFileName = uuid + ext;
        String savePath = filePath + saveFileName;

        try {
            file.transferTo(new File(savePath));
            return fileUrl + saveFileName;

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private void fileValidationCheck(MultipartFile file) {
        List<String> imgExts = List.of(".jpg", ".png", ".jpeg");
        String originalFilename = file.getOriginalFilename();
        String ext = Objects.requireNonNull(originalFilename).substring(originalFilename.lastIndexOf("."));
        if (!imgExts.contains(ext)) {
            throw new BadRequestException("이미지 파일이 아닙니다.");
        }

        if (file.getSize() > maximumFileSize) {
            throw new BadRequestException("파일 용량 초과");
        }
    }

    public String tempImageToSave(String tempImageUrl) throws IOException {
        String tempFileName = tempImageUrl.replace(tempUrl, "");
        Path tempFilePath = Paths.get(tempPath + tempFileName);
        Path path = Paths.get(filePath, tempFileName);
        Files.move(tempFilePath, path);
        return fileUrl + tempFileName;
    }
}

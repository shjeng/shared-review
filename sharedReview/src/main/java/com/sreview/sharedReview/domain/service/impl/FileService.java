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
        String ext = Objects.requireNonNull(originalFileName).substring(originalFileName.lastIndexOf(".")).toLowerCase(); // 확장자를 소문자로 변환
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String saveFileName = uuid + ext;
        String savePath = tempPath + saveFileName;

        // 디렉토리 존재 확인 및 생성
        File dir = new File(tempPath);
        if (!dir.exists()) {
            dir.mkdirs(); // 디렉토리 생성
        }

        try {
            file.transferTo(new File(savePath));
            return tempUrl + saveFileName;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("파일 업로드 중 오류 발생: " + e.getMessage(), e); // 예외 메시지 포함
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
        String ext = Objects.requireNonNull(originalFilename).substring(originalFilename.lastIndexOf(".")).toLowerCase(); // 확장자를 소문자로 변환
        if (!imgExts.contains(ext)) {
            System.out.println("파일 확장자가 유효하지 않습니다: " + ext);
            throw new BadRequestException("이미지 파일이 아닙니다.");
        }

        System.out.println("파일 크기: " + file.getSize());
        if (file.getSize() > maximumFileSize) {
            System.out.println("파일 용량 초과: " + file.getSize());
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

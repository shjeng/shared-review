package com.sreview.sharedReview.domain.service.impl;

import com.sreview.sharedReview.domain.common.customexception.BadRequestException;
import com.sreview.sharedReview.domain.jpa.entity.EditorImage;
import com.sreview.sharedReview.domain.jpa.entity.enumType.FILE_STATUS;
import com.sreview.sharedReview.domain.jpa.service.EditorRepoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileService {

    private final EditorRepoService editorRepoService;
    @Value("${file.path}")
    private String filePath;

    @Value("${file.tempUrl}")
    private String tempUrl;
    @Value("${file.temp}")
    private String tempPath;
    @Value("${file.url}")
    private String fileUrl;
    @Value("${file.editor.url}")
    private String editorUrl;

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

    public Resource getImage(Long fileId){
        EditorImage editor = editorRepoService.findById(fileId);
        try {
            return new FileSystemResource(editor.filePath + editor.savedName);
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
    public Map<String, Object> uploadImage(MultipartFile file) {
        if(file.isEmpty()) return null;
        fileValidationCheck(file);
        String originalFileName = file.getOriginalFilename();
        String ext = Objects.requireNonNull(originalFileName).substring(originalFileName.lastIndexOf(".")); // 확장자
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String saveFileName = uuid + ext;
        String savePath = tempPath + saveFileName;
        EditorImage editorImage = EditorImage.builder()
                .savedName(saveFileName)
                .realName(file.getOriginalFilename())
                .ext(ext)
                .status(FILE_STATUS.TEMP)
                .fileSize(file.getSize())
                .filePath(tempPath)
                .deleteYn('N')
                .build();
        editorRepoService.save(editorImage);
        Map<String, Object> result = Map.of(
                "fileUrl", editorUrl + editorImage.getId(),
                "editorId", editorImage.getId());
        try {
            file.transferTo(new File(savePath));
            return result;

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private void fileValidationCheck(MultipartFile file) {
        List<String> imgExts = List.of(".jpg", ".png", ".jpeg");
        String originalFilename = file.getOriginalFilename();
        String ext = Objects.requireNonNull(originalFilename).substring(originalFilename.lastIndexOf(".")).toLowerCase(); // 확장자를 소문자로 변환
        if (!imgExts.contains(ext)) {
            throw new BadRequestException("이미지 파일이 아닙니다.");
        }

        System.out.println("파일 크기: " + file.getSize());
        if (file.getSize() > maximumFileSize) {
            throw new BadRequestException("파일 용량 초과");
        }
    }

    public String tempImageToSave(String tempImageUrl) throws IOException {
        String fileName = tempImageUrl.replace(tempUrl, "");
        Path tempFilePath = Paths.get(tempPath + fileName);
        Path path = Paths.get(filePath, fileName);
        Files.move(tempFilePath, path);
        return fileUrl + fileName;
    }

    public void tempImageToSave(EditorImage editorImage) throws IOException {
        Path tempFilePath = Paths.get(editorImage.getFilePath() + editorImage.getSavedName());
        Path path = Paths.get(filePath, editorImage.getSavedName());
        Files.move(tempFilePath, path);
        editorImage.updateFilePath(filePath);
    }

    public void updateImageStatusToNormal(EditorImage editorImage) throws IOException {
//        editorRepoService.findById();
//        File old = new File()
    }
}

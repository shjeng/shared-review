package com.sreview.sharedReview.domain.controller;

import com.sreview.sharedReview.domain.dto.request.board.BoardWriteRequest;
import com.sreview.sharedReview.domain.dto.request.board.CategoryWriteRequest;
import com.sreview.sharedReview.domain.dto.response.board.BoardWriteResponse;
import com.sreview.sharedReview.domain.dto.response.board.CategoryWriteResponse;
import com.sreview.sharedReview.domain.dto.response.board.GetCategorysResponse;
import com.sreview.sharedReview.domain.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
@Slf4j
public class BoardController {
    private final BoardService boardServcice;

    @GetMapping("/get-list")
    public ResponseEntity<Void> getBoardList(){
        return null;
    }
    @GetMapping("/get-list/favorite")
    public ResponseEntity<Void> getFavoriteBoardList(){
        return null;
    }

    @GetMapping("/get-categorys") // 카테고리 목록 불러오는 메서드
    public ResponseEntity<? super GetCategorysResponse> getCategorys(){
        return boardServcice.getCategorys();
    }
    @PostMapping("/category/create") // 카테고리 저장, @AuthenticationPrincipal 작성 필요
    public ResponseEntity<? super CategoryWriteResponse> createCateogry(@RequestBody CategoryWriteRequest request){
        return boardServcice.saveCategory(request);
    }
    @PostMapping("/write") // 게시글 작성
    public ResponseEntity<? super BoardWriteResponse> boardWrite(
            @RequestBody BoardWriteRequest request,
            @AuthenticationPrincipal String email){
        log.info("request = {}", request);
        return boardServcice.saveBoard(request,email);
    }
    @PostMapping("/test")
    public String test(@AuthenticationPrincipal String email){ // 스트링으로 했음.
        return "성공?";
    }
}

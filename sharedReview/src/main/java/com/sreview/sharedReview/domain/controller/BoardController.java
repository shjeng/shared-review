package com.sreview.sharedReview.domain.controller;

import com.sreview.sharedReview.domain.dto.request.board.BoardWriteRequest;
import com.sreview.sharedReview.domain.dto.request.board.CategoryWriteRequest;
import com.sreview.sharedReview.domain.dto.response.board.BoardWriteResponse;
import com.sreview.sharedReview.domain.dto.response.board.CategoryWriteResponse;
import com.sreview.sharedReview.domain.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/board")
@RequiredArgsConstructor
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

    @GetMapping("/get-category")
    public ResponseEntity<?> getCategorys(){
        return null;
    }
    @PostMapping("/category/create") // 카테고리 저장, @AuthenticationPrincipal 작성 필요
    public ResponseEntity<? super CategoryWriteResponse> createCateogry(@RequestBody CategoryWriteRequest request){
        return boardServcice.saveCategory(request);
    }
    @PostMapping("/write") // 게시글 작성, @AuthenticationPrincipal 이거 사용해야됨. 아직 회원 정보 하나도 없어서 나중에 사용.
    public ResponseEntity<? super BoardWriteResponse> boardWrite(
            @RequestBody BoardWriteRequest request,
            @AuthenticationPrincipal String email){
        return boardServcice.saveBoard(request,email);
    }
//    @GetMapping("/test")
//    public String test(Dto dto){ // 스트링으로 했음.
//        return boardServcice.save(dto);
//    }
}

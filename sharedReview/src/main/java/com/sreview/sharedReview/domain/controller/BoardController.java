package com.sreview.sharedReview.domain.controller;

import com.sreview.sharedReview.domain.dto.request.board.BoardWriteRequest;
import com.sreview.sharedReview.domain.dto.request.board.CategoryWriteRequest;
import com.sreview.sharedReview.domain.dto.request.board.CommentWriteRequest;
import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import com.sreview.sharedReview.domain.dto.response.board.*;
import com.sreview.sharedReview.domain.jpa.entity.Board;
import com.sreview.sharedReview.domain.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
@Slf4j
public class BoardController {
    private final BoardService boardServcice;

    @GetMapping("/latest")
    public ResponseEntity<? super BoardListResponse> getBoardListLatest(){
        log.info("latest = ");
        return ResponseEntity.ok(boardServcice.getBoardListLatest());
    }
    @GetMapping("/favoriteTop3")
    public ResponseEntity<? super BoardListResponse> getFavoriteBoardTop3(@RequestParam("dateCondition") String condition){
        return ResponseEntity.ok(boardServcice.getFaoviteBoardTop3(condition));
    }
    @GetMapping("/get-list/favorite")
    public ResponseEntity<Void> getFavoriteBoardList(){
        return null;
    }

    @GetMapping("/increase-view-count/{boardId}")
    public ResponseEntity<?> increaseViewCount(@PathVariable("boardId")Long boardId){
        return ResponseEntity.ok(boardServcice.increaseViewcount(boardId));
    }
    @GetMapping("/get-categorys") // 카테고리 목록 불러오는 메서드
    public ResponseEntity<? super GetCategorysResponse> getCategorys(){
        return boardServcice.getCategorys();
    }

    @GetMapping("/admin/get-categorys") // 관리자 페이지 카테고리
    public ResponseEntity<? super AdminCategotyResponse> getAdminCategorys(){
        return boardServcice.getAdminCategorys();
    }
    @GetMapping("/{boardId}")
    public ResponseEntity<? super Board> getBoardDetail(@PathVariable("boardId") Long boardId){
        log.info("boardId = {} ", boardId);
        return ResponseEntity.ok(boardServcice.getBoard(boardId));
    }

    @PostMapping("/category/create") // 카테고리 저장, @AuthenticationPrincipal 작성 필요
    public ResponseEntity<? super CategoryWriteResponse> createCateogry(@RequestBody CategoryWriteRequest request){
        return boardServcice.saveCategory(request);
    }
    @GetMapping("/test")
    public ResponseEntity<?> test(){ // 스트링으로 했음.
        log.info("test");
        return ResponseEntity.internalServerError().build();
    }

    @GetMapping("/board-list")
    public ResponseEntity<? super BoardListResponse> getAllBoards(@PageableDefault(size = 20)Pageable pageable){
        BoardListResponse allBoards = (BoardListResponse) boardServcice.getAllBoards(pageable);
        System.out.println("allBoards =" + allBoards.toString());
        return ResponseEntity.ok(allBoards);
    }
    @PostMapping("/comment")
    public ResponseEntity<?> commentWrite(@AuthenticationPrincipal String email, @RequestBody CommentWriteRequest request){
        log.info("request = {}", request);
        return ResponseEntity.ok(boardServcice.commentWrite(email, request));
    }
    @PostMapping("/write") // 게시글 작성
    public ResponseEntity<? super BoardWriteResponse> boardWrite(
            @RequestBody BoardWriteRequest request,
            @AuthenticationPrincipal String email){
        log.info("request = {}", request);
        return boardServcice.saveBoard(request,email);
    }

    @GetMapping("/admin/board-list") // 관리자 페이지 - 게시물 리스트 불러옴
    public ResponseEntity<? super AdminBoardListResponse> getAdminBoards(){
        System.out.println("ResponseEntity.ok(boardServcice.getAdminBoards()) : "+ResponseEntity.ok(boardServcice.getAdminBoards()));
        return ResponseEntity.ok(boardServcice.getAdminBoards());
    }


}

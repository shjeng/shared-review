package com.sreview.sharedReview.domain.service;

import com.sreview.sharedReview.domain.dto.request.board.BoardListParam;
import com.sreview.sharedReview.domain.dto.request.board.BoardWriteRequest;
import com.sreview.sharedReview.domain.dto.request.board.CategoryWriteRequest;
import com.sreview.sharedReview.domain.dto.request.board.CommentWriteRequest;
import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import com.sreview.sharedReview.domain.dto.response.board.*;
import com.sreview.sharedReview.domain.jpa.entity.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BoardService {
    ResponseDto getBoardListLatest();
    ResponseDto getFaoviteBoardTop3(String condition);

    ResponseDto findBoardByUserEmail(String userEmail, Pageable pageable);

    ResponseEntity<? super CategoryWriteResponse> saveCategory(CategoryWriteRequest request, String email);

    ResponseEntity<? super GetCategorysResponse> getCategorys();

    ResponseEntity<? super AdminCategotyResponse> getAdminCategorys();

    ResponseEntity<? super AdminCategotyResponse> getAdminCategorySearch(String searchValue, String inputValue);

    ResponseEntity<? super BoardWriteResponse> saveBoard(BoardWriteRequest request, String email);

    ResponseDto getBoard(Long boardId);
    List<Board> getBoard(String searchKeyword, Long categoryId);

    ResponseDto increaseViewcount(Long boardId);
    ResponseDto getAllBoards(Pageable pageable);
    ResponseDto getBoards(Pageable pageable, BoardListParam params);

    ResponseDto favorite(Long boardId, Boolean favoriteCheck ,String email);
    ResponseEntity<? super AdminBoardListResponse> getAdminBoards();
    ResponseEntity<? super AdminBoardListResponse> getAdminBoardSearch(String searchValue, String inputValue);

    ResponseDto commentWrite(String writerEmail, CommentWriteRequest request, Pageable pageable);

    ResponseDto getComments(Long boardId, Pageable pageable);
    ResponseDto deleteBoard(Long boardId, String email);

    ResponseEntity<? super CategoryDeleteResponse> deleteCategory(Long categoryId, String email);


    void deleteComment(Long commentId, String email);

}

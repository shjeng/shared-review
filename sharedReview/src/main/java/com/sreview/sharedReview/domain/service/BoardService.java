package com.sreview.sharedReview.domain.service;

import com.sreview.sharedReview.domain.dto.request.board.BoardWriteRequest;
import com.sreview.sharedReview.domain.dto.request.board.CategoryWriteRequest;
import com.sreview.sharedReview.domain.dto.request.board.CommentWriteRequest;
import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import com.sreview.sharedReview.domain.dto.response.board.*;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface BoardService {
    ResponseDto getBoardListLatest();
    ResponseDto getFaoviteBoardTop3(String condition);

    ResponseDto findBoardByUser(String userEmail);
    ResponseEntity<? super CategoryWriteResponse> saveCategory(CategoryWriteRequest request);

    ResponseEntity<? super GetCategorysResponse> getCategorys();

    ResponseEntity<? super AdminCategotyResponse> getAdminCategorys();

    ResponseDto getAdminCategorySearch(String searchValue, String inputValue);

    ResponseEntity<? super BoardWriteResponse> saveBoard(BoardWriteRequest request, String email);

    ResponseDto getBoard(Long boardId);

    ResponseDto increaseViewcount(Long boardId);
    ResponseDto getAllBoards(Pageable pageable);

    ResponseEntity<? super AdminBoardListResponse> getAdminBoards();

    ResponseDto commentWrite(String writerEmail, CommentWriteRequest request, Pageable pageable);

    ResponseDto getComments(Long boardId, Pageable pageable);
    ResponseDto deleteBoard(Long boardId, String email);

    void deleteComment(Long commentId, String email);

}

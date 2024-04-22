package com.sreview.sharedReview.domain.service;

import com.sreview.sharedReview.domain.dto.request.board.BoardWriteRequest;
import com.sreview.sharedReview.domain.dto.request.board.CategoryWriteRequest;
import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import com.sreview.sharedReview.domain.dto.response.board.BoardDetailResponse;
import com.sreview.sharedReview.domain.dto.response.board.BoardWriteResponse;
import com.sreview.sharedReview.domain.dto.response.board.CategoryWriteResponse;
import com.sreview.sharedReview.domain.dto.response.board.GetCategorysResponse;
import com.sreview.sharedReview.domain.jpa.entity.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface BoardService {
    ResponseDto getBoardListLatest();
    ResponseDto getFaoviteBoardTop3(String condition);

    ResponseDto findBoardByUser(String userEmail);
    ResponseEntity<? super CategoryWriteResponse> saveCategory(CategoryWriteRequest request);

    ResponseEntity<? super GetCategorysResponse> getCategorys();

    ResponseEntity<? super BoardWriteResponse> saveBoard(BoardWriteRequest request, String email);

    ResponseDto getBoard(Long boardId);

    ResponseDto increaseViewcount(Long boardId);
    ResponseDto getAllBoards(Pageable pageable);

}

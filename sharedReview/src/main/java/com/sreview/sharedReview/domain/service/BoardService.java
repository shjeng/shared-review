package com.sreview.sharedReview.domain.service;

import com.sreview.sharedReview.domain.dto.request.board.BoardWriteRequest;
import com.sreview.sharedReview.domain.dto.request.board.CategoryWriteRequest;
import com.sreview.sharedReview.domain.dto.response.board.BoardWriteResponse;
import com.sreview.sharedReview.domain.dto.response.board.CategoryWriteResponse;
import com.sreview.sharedReview.domain.dto.response.board.GetCategorysResponse;
import org.springframework.http.ResponseEntity;

public interface BoardService {
    ResponseEntity<? super CategoryWriteResponse> saveCategory(CategoryWriteRequest request);
    ResponseEntity<? super GetCategorysResponse> getCategorys();

    ResponseEntity<? super BoardWriteResponse> saveBoard(BoardWriteRequest request,String email);
}

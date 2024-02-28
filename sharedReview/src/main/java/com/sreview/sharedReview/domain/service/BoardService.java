package com.sreview.sharedReview.domain.service;

import com.sreview.sharedReview.domain.dto.request.board.BoardWriteRequest;
import com.sreview.sharedReview.domain.dto.request.board.CategoryWriteRequest;
import com.sreview.sharedReview.domain.dto.response.board.BoardWriteResponse;
import com.sreview.sharedReview.domain.dto.response.board.CategoryWriteResponse;
import org.springframework.http.ResponseEntity;

public interface BoardService {
    // 메서드 이름을 categorySave라든지 좀 더 명확하게 하면 좋을듯?
    ResponseEntity<? super CategoryWriteResponse> categorySave(CategoryWriteRequest request);

    ResponseEntity<? super BoardWriteResponse> saveBoard(BoardWriteRequest request);
}

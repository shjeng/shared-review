package com.sreview.sharedReview.domain.controller;

import com.sreview.sharedReview.domain.service.BoardServcice;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardServcice boardServcice;

//    @GetMapping("/test")
//    public String test(Dto dto){ // 스트링으로 했음.
//        return boardServcice.save(dto);
//    }
}

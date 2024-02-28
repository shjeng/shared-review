package com.sreview.sharedReview.domain.controller;

import com.sreview.sharedReview.domain.jpa.entity.Board;
import com.sreview.sharedReview.domain.service.PostSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/board")
@RequiredArgsConstructor
public class PostController {
    private final PostSerivce postSerivce;

    @PostMapping("/save")
    public String save(@ModelAttribute Board board) {
        postSerivce.save(board);

        // 인덱스 페이지로 이동
        return "index";
    }
}

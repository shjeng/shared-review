package com.sreview.sharedReview.domain.controller;

import com.sreview.sharedReview.domain.board.dto.BoardDto;
import com.sreview.sharedReview.domain.service.BoardServcice;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardServcice boardServcice;

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDto boardDto) {
        boardService.save(boardDto);
        return "index";
    }
}

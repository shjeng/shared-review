package com.sreview.sharedReview.domain.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerCheckController {
    @GetMapping("check")
    public ResponseEntity<String> check(){
        return ResponseEntity.ok("서버 구동 중 ");
    }
}

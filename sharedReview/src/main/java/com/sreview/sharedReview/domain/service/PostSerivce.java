package com.sreview.sharedReview.domain.service;

//import com.sreview.sharedReview.domain.board.dto.PostDTO;

import com.sreview.sharedReview.domain.jpa.entity.Board;

public interface PostSerivce {
    void save(Board board);
//    void savePost(PostDTO postDTO);
}

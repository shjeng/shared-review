package com.sreview.sharedReview.domain.jpa.service;

import com.sreview.sharedReview.domain.jpa.entity.Board;
import com.sreview.sharedReview.domain.jpa.entity.Favorite;
import com.sreview.sharedReview.domain.jpa.jpaInterface.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteRepoService {
    private final FavoriteRepository favoriteRepository;

    public void save(Favorite favorite) {
        favoriteRepository.save(favorite);
    }

    public List<Favorite> findAllByBoard(Board board) {
        return favoriteRepository.findAllByBoard(board);
    }
}

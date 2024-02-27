package com.sreview.sharedReview.domain.jpa.service;

import com.sreview.sharedReview.domain.jpa.entity.Categorie;
import com.sreview.sharedReview.domain.jpa.jpaInterface.CategorieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true) // 이건 읽기 전용
@Service
@RequiredArgsConstructor
public class CategoryService { // DB에 넣어주는 아이

    private final CategorieRepository categorieRepository;

    @Transactional // 이 때 커밋이 나감.
    public void save(Categorie categorie){
        categorieRepository.save(categorie);
    }
}

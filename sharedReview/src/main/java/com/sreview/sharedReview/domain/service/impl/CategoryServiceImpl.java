package com.sreview.sharedReview.domain.service.impl;

import com.sreview.sharedReview.domain.board.entity.Categorie;
import com.sreview.sharedReview.domain.jpa.service.CategoryService;
import com.sreview.sharedReview.domain.service.BoardServcice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements BoardServcice{
    private CategoryService categoryService;
    @Override
    public String save(Dto dto) {
        try{
            Categorie categorie = new Categorie();
            categorie.setName(dto.getName());

           categoryService.save(categorie);
        }catch(Exception e){
            e.printStackTrace();
        }

        return "true";
    }
}

package com.sreview.sharedReview.domain.jpa.service;

import com.sreview.sharedReview.domain.jpa.entity.Category;
import com.sreview.sharedReview.domain.jpa.jpaInterface.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true) // 이건 읽기 전용
@Service
@RequiredArgsConstructor
public class CategoryRepoService { // DB에 넣어주는 아이

    private final CategoryRepository categoryRepository;

    @Transactional // 이 때 커밋이 나감.
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Transactional
    public void saveAll(List<Category> categorys) {
        categoryRepository.saveAll(categorys);
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }
    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}

package com.sreview.sharedReview.domain.dto.object;

import com.sreview.sharedReview.domain.jpa.entity.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CategoryDto {
    private Long categoryId;
    private String categoryName;

    public CategoryDto of(Category category){
        this.categoryId = category.getId();
        this.categoryName = category.getName();
        return this;
    }

    // 엔티티 목록을 Dto 리스트로 변환해주는 메서드
    public static List<CategoryDto> ofList(List<Category> categories){
        List<CategoryDto> result = new ArrayList<>();
        for (Category category : categories) {
            result.add(new CategoryDto().of(category));
        }
        return result;
    }
}

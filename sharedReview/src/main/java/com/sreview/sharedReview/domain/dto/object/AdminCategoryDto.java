package com.sreview.sharedReview.domain.dto.object;

import com.sreview.sharedReview.domain.jpa.entity.Category;
import com.sreview.sharedReview.domain.jpa.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AdminCategoryDto {

    private Long categoryId;
    private String categoryName;
    private String userNickname;
    private LocalDateTime writeDateTime;


    public AdminCategoryDto of(Category category){
        this.categoryId = category.getId();
        this.categoryName = category.getName();
        this.userNickname = category.getUser().getNickname();
        this.writeDateTime = category.getCreateDate();
        return this;
    }

    // 엔티티 목록을 Dto 리스트로 변환해주는 메서드
    public static List<AdminCategoryDto> ofList(List<Category> categorys){
        List<AdminCategoryDto> result = new ArrayList<>();
        for (Category category : categorys) {
            result.add(new AdminCategoryDto().of(category));
        }
        return result;
    }
}

package com.sreview.sharedReview.domain.dto.object;

import com.sreview.sharedReview.domain.jpa.entity.Tag;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TagDto {
    private Long tagId;
    private String name;

    public TagDto ofEntity(Tag tag) {
        tagId = tag.getId();
        name = tag.getName();
        return this;
    }

}

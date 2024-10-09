package com.sreview.sharedReview.domain.dto.object;

import com.sreview.sharedReview.domain.jpa.entity.Category;
import com.sreview.sharedReview.domain.jpa.entity.EditorImage;
import com.sreview.sharedReview.domain.jpa.entity.Image;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class ImageDto {

    private Long imageId;
    private String imageUrl;

    public ImageDto of(EditorImage image, String editorUrl) {
        this.imageId = image.getId();
        this.imageUrl = editorUrl + image.getId();
        return this;
    }

}

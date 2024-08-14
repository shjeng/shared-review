package com.sreview.sharedReview.domain.dto.object;

import com.sreview.sharedReview.domain.jpa.entity.Category;
import com.sreview.sharedReview.domain.jpa.entity.Image;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class ImageDto {
    private Long imageId;
    private String imageUrl;

    public ImageDto of(Image image) {
        this.imageId = image.getId();
        this.imageUrl = image.getUrl();
        return this;
    }

    public static List<ImageDto> ofList(List<Image> images){
        List<ImageDto> result = new ArrayList<>();
        for (Image iamge : images) {
            result.add(new ImageDto().of(iamge));
        }
        return result;
    }

}

package com.sreview.sharedReview.domain.dto.object;

import com.sreview.sharedReview.domain.jpa.entity.Favorite;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class FavoriteDto {
    private Long favoriteId;
    private String userEmail;



    public static FavoriteDto of(Favorite favorite) {
        return FavoriteDto.builder()
                .favoriteId(favorite.getId())
                .userEmail(favorite.user.getEmail())
                .build();
    }

}

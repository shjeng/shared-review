package com.sreview.sharedReview.domain.dto.object;

import com.sreview.sharedReview.domain.jpa.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String email;
    private String nickname;
    private String getImageUrl;

    public UserDto(String email, String nickname, String getImageUrl) {
        this.email = email;
        this.nickname = nickname;
        this.getImageUrl = getImageUrl;
    }

    public static UserDto of(User user){
        return new UserDto(user.getEmail(), user.getNickname(), user.getImageUrl());
    }
}

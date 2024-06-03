package com.sreview.sharedReview.domain.dto.object;

import com.sreview.sharedReview.domain.jpa.entity.User;
import com.sreview.sharedReview.domain.jpa.entity.enumType.ADMIN;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String email;
    private String nickname;
    private String profileImage;
    private ADMIN admin;

    public UserDto(String email, String nickname, String profileImage, ADMIN admin) {
        this.email = email;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.admin = admin;


    }



    public static UserDto of(User user){
        return new UserDto(user.getEmail(), user.getNickname(), user.getImageUrl(), user.getAdmin());
    }
}

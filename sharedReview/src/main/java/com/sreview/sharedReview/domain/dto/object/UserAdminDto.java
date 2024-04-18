package com.sreview.sharedReview.domain.dto.object;

import com.sreview.sharedReview.domain.jpa.entity.User;
import com.sreview.sharedReview.domain.jpa.entity.enumType.ADMIN;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class UserAdminDto {
    private Long id;
    private String nickname;
    private String email;
    private LocalDateTime writeDateTime;
    private ADMIN admin;


    public UserAdminDto of(User user) {
        id = user.getId();
        nickname = user.getNickname();
        email = user.getEmail();
        writeDateTime = user.getCreateDate();
        admin = user.getAdmin();
        return this;
    }
}

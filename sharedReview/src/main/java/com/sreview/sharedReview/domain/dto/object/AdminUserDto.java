package com.sreview.sharedReview.domain.dto.object;

import com.sreview.sharedReview.domain.jpa.entity.Board;
import com.sreview.sharedReview.domain.jpa.entity.User;
import com.sreview.sharedReview.domain.jpa.entity.enumType.ADMIN;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class AdminUserDto {
    private Long id;
    private String nickname;
    private String email;
    private LocalDateTime writeDateTime;
    private ADMIN admin;


    public AdminUserDto of(User user) {
        id = user.getId();
        nickname = user.getNickname();
        email = user.getEmail();
        writeDateTime = user.getCreateDate();
        admin = user.getAdmin();
        return this;
    }

    public static List<AdminUserDto> ofList(List<User> users){
        List<AdminUserDto> result = new ArrayList<>();
        for (User user : users) {
            result.add(new AdminUserDto().of(user));
        }
        return result;
    }
}

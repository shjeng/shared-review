package com.sreview.sharedReview.domain.dto.response.user;

import com.sreview.sharedReview.domain.jpa.entity.User;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetUserListResponse {

    private List<User> userList;

    public GetUserListResponse(List<User> userList) {
        this.userList = userList;
    }

    public static ResponseEntity<GetUserListResponse> success(List<User> userList) {
        return ResponseEntity.status(HttpStatus.OK).body(new GetUserListResponse(userList));
    }

    public static ResponseEntity<GetUserListResponse> failure() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
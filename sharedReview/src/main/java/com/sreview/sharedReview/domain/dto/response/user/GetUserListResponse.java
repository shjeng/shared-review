package com.sreview.sharedReview.domain.dto.response.user;

import com.sreview.sharedReview.domain.common.ResponseCode;
import com.sreview.sharedReview.domain.common.ResponseMessage;
import com.sreview.sharedReview.domain.dto.object.AdminUserDto;
import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetUserListResponse extends ResponseDto {

    private List<AdminUserDto> userList;

    public GetUserListResponse(List<AdminUserDto> userList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userList = userList;
    }

//    public static ResponseEntity<GetUserListResponse> success(List<UserAdminDto> userList) {
//        return ResponseEntity.status(HttpStatus.OK).body(new GetUserListResponse(userList));
//
//    }

    public static GetUserListResponse success(List<AdminUserDto> userDtoList) {
        return new GetUserListResponse(userDtoList);
    }

    public static ResponseEntity<GetUserListResponse> failure() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
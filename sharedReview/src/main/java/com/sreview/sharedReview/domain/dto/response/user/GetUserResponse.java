package com.sreview.sharedReview.domain.dto.response.user;

import com.sreview.sharedReview.domain.common.ResponseCode;
import com.sreview.sharedReview.domain.common.ResponseMessage;
import com.sreview.sharedReview.domain.dto.object.UserDto;
import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import lombok.Getter;

@Getter
public class GetUserResponse extends ResponseDto {

    private UserDto userDto;
    public GetUserResponse(UserDto userDto) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userDto = userDto;

    }
    public static GetUserResponse success(UserDto userDto){
        return new GetUserResponse(userDto);
    }
}

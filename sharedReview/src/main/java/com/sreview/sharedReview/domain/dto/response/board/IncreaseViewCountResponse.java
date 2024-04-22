package com.sreview.sharedReview.domain.dto.response.board;

import com.sreview.sharedReview.domain.common.ResponseCode;
import com.sreview.sharedReview.domain.common.ResponseMessage;
import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import lombok.Getter;

@Getter
public class IncreaseViewCountResponse extends ResponseDto {

    private Integer viewCount;

    public IncreaseViewCountResponse(Integer viewCount) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.viewCount = viewCount;
    }

    public static IncreaseViewCountResponse success(Integer viewCount){
        return new IncreaseViewCountResponse(viewCount);
    }
}

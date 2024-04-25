package com.sreview.sharedReview.domain.dto.response.board;

import com.sreview.sharedReview.domain.common.ResponseCode;
import com.sreview.sharedReview.domain.common.ResponseMessage;
import com.sreview.sharedReview.domain.dto.object.CommentDto;
import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class CommentResponse extends ResponseDto {
    Page<CommentDto> comments;

    private CommentResponse(Page<CommentDto> comments) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.comments = comments;
    }
    public static CommentResponse success(Page<CommentDto> comments){
        return new CommentResponse(comments);
    }
}

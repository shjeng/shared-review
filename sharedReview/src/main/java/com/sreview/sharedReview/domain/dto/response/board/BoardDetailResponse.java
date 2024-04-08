package com.sreview.sharedReview.domain.dto.response.board;

import com.sreview.sharedReview.domain.common.ResponseCode;
import com.sreview.sharedReview.domain.common.ResponseMessage;
import com.sreview.sharedReview.domain.dto.object.BoardDetailDto;
import com.sreview.sharedReview.domain.dto.object.CommentDto;
import com.sreview.sharedReview.domain.dto.object.FavoriteDto;
import com.sreview.sharedReview.domain.dto.object.UserDto;
import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class BoardDetailResponse extends ResponseDto {
    private UserDto user;
    private BoardDetailDto boardDetail;
    private List<CommentDto> comments;
    private List<FavoriteDto> favorites;


    public BoardDetailResponse(UserDto user, BoardDetailDto boardDetail, List<CommentDto> comments, List<FavoriteDto> favorites) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.user = user;
        this.boardDetail = boardDetail;
        this.comments = comments;
        this.favorites = favorites;
    }

    //  다른 방식으로 success 처리 해볼 예정
    public static BoardDetailResponse success(UserDto user, BoardDetailDto boardDetail, List<CommentDto> comments, List<FavoriteDto> favorites) {
        return new BoardDetailResponse(user, boardDetail, comments, favorites);
    }
}

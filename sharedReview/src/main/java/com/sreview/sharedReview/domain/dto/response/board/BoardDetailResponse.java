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
    private UserDto userDto;
    private BoardDetailDto boardDetailDto;
    private List<CommentDto> commentDtos;
    private List<FavoriteDto> favoriteDto;


    public BoardDetailResponse(UserDto userDto, BoardDetailDto boardDetailDto, List<CommentDto> commentDtos, List<FavoriteDto> favoriteDto) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userDto = userDto;
        this.boardDetailDto = boardDetailDto;
        this.commentDtos = commentDtos;
        this.favoriteDto = favoriteDto;
    }

    //  다른 방식으로 success 처리 해볼 예정
    public static BoardDetailResponse success(UserDto userDto, BoardDetailDto boardDetailDto, List<CommentDto> commentDtos, List<FavoriteDto> favoriteDto) {
        return new BoardDetailResponse(userDto, boardDetailDto, commentDtos, favoriteDto);
    }
}

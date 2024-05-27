package com.sreview.sharedReview.domain.dto.response.board;

import com.sreview.sharedReview.domain.common.ResponseCode;
import com.sreview.sharedReview.domain.common.ResponseMessage;
import com.sreview.sharedReview.domain.dto.object.*;
import com.sreview.sharedReview.domain.dto.response.ResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@ToString
public class BoardDetailResponse extends ResponseDto {
    private UserDto user;
    private BoardDetailDto boardDetail;
    private Page<CommentDto> comments;
    private List<FavoriteDto> favorites;
    private List<TagDto> tags;
    private Boolean favoriteCheck;
    private Integer favoriteCount;
    @Builder
    public BoardDetailResponse(UserDto user, BoardDetailDto boardDetail, Page<CommentDto> comments, List<FavoriteDto> favorites, List<TagDto> tags, Boolean favoriteCheck, Integer favoriteCount) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.user = user;
        this.boardDetail = boardDetail;
        this.comments = comments;
        this.favorites = favorites;
        this.tags = tags;
        this.favoriteCheck = favoriteCheck;
        this.favoriteCount = favoriteCount;
    }

    public BoardDetailResponse(Boolean favoriteCheck) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.favoriteCheck = favoriteCheck;
    }
    //  다른 방식으로 success 처리 해볼 예정
    public static BoardDetailResponse success(UserDto user, BoardDetailDto boardDetail, Page<CommentDto> comments, List<FavoriteDto> favorites, List<TagDto> tags) {
        return BoardDetailResponse.builder()
                .user(user)
                .boardDetail(boardDetail)
                .comments(comments)
                .favorites(favorites)
                .tags(tags)
                .build();
    }

}

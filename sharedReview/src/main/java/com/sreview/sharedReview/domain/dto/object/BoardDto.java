package com.sreview.sharedReview.domain.dto.object;

import com.sreview.sharedReview.domain.jpa.entity.Board;
import com.sreview.sharedReview.domain.jpa.entity.BoardTag;
import com.sreview.sharedReview.domain.jpa.entity.Image;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class BoardDto {
    private Long boardId;
    private String title;
    private Integer commentCount;
    private Integer favoriteCount;
    private Integer viewCount;
    private ImageDto backImg;
    private LocalDateTime writeDateTime;
    private UserDto user;
    private List<TagDto> tags;
    private CategoryDto category;

    public BoardDto of(Board board){
        this.boardId = board.getBoardId();
        this.title = board.getTitle();
        commentCount = board.getCommentCount();
        favoriteCount = board.getFavoriteCount();
        viewCount = board.getViewsCount();
        writeDateTime = board.getLastModifiedDate();
        user = UserDto.of(board.getUser());
        List<BoardTag> boardTag = board.getBoardTag();
        tags = boardTag.stream().map(bt -> new TagDto().ofEntity(bt.getTag())).toList();

        // 이미지 처리
        if (board.getImages() != null && !board.getImages().isEmpty()) {
            List<ImageDto> imageDtos = board.getImages().stream()
                    .map(image -> new ImageDto().of(image))
                    .collect(Collectors.toList());

            if (!imageDtos.isEmpty()) {
                this.backImg = imageDtos.get(0); // 첫 번째 이미지만 사용
            }
        }

        if (board.getCategory() != null) {
            this.category = new CategoryDto().of(board.getCategory());
        }

        return this;
    }
}


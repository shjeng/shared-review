package com.sreview.sharedReview.domain.dto.object;

import com.sreview.sharedReview.domain.jpa.entity.Board;
import com.sreview.sharedReview.domain.jpa.entity.Category;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class AdminBoardDto {
    private Long boardId;
    private String title;
    private UserDto user;
    private LocalDateTime writeDateTime;

    public AdminBoardDto of(Board board){
        this.boardId = board.getBoardId();
        this.title = board.getTitle();
        writeDateTime = board.getLastModifiedDate();
        user = UserDto.of(board.getUser());
        return this;
    }

    public static List<AdminBoardDto> ofList(List<Board> boards){
        List<AdminBoardDto> result = new ArrayList<>();
        for (Board board : boards) {
            result.add(new AdminBoardDto().of(board));
        }
        return result;
    }
}

package com.sreview.sharedReview.domain.dto.request.board;

import com.sreview.sharedReview.domain.dto.object.CategoryDto;
import com.sreview.sharedReview.domain.jpa.entity.Board;
import com.sreview.sharedReview.domain.jpa.entity.Tag;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class BoardWriteRequest {
    private String title;
    private String contentHtml;
    private String contentMarkdown;
    private CategoryDto category;
    private List<String> tags;

    public static Board getBoard(BoardWriteRequest request){
        Board boardEntity = new Board();
//        boardEntity.setTitleContent(request.title, request.content);
        return boardEntity;
    }
    public List<Tag> getTagList(){
        List<Tag> tagList = new ArrayList<>();
        tags.forEach(t->tagList.add(new Tag().setTagName(t)));
        return tagList;
    }
}

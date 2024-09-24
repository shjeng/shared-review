package com.sreview.sharedReview.domain.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Comment;

@Entity
@Getter
public class EditorImage extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column
    @Comment("파일 경로")
    public String filePath;
    @Column
    @Comment("저장된 이름")
    public String savedName;
    @Column
    @Comment("실제 이름")
    public String realName;
    @Column
    @Comment("파일 크기")
    public Long fileSize;
    @Column
    @Comment("확장자")
    public String ext;
    @ManyToOne
    @JoinColumn(name="board_id")
    public Board boardId;
}

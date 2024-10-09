package com.sreview.sharedReview.domain.jpa.entity;

import com.sreview.sharedReview.domain.jpa.entity.enumType.FILE_STATUS;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @Column(nullable = false)
    @Comment("파일 상태")
    private FILE_STATUS status;
    @Column
    @Comment("실제 이름")
    public String realName;
    @Column
    @Comment("파일 크기")
    public Long fileSize;
    @Column
    @Comment("확장자")
    public String ext;
    @Column
    @Comment("삭제여부")
    @ColumnDefault("'N'")
    private Character deleteYn;

    @Setter
    @ManyToOne
    @JoinColumn(name="board_id")
    public Board boardId;

    public void updateFilePath(String filePath) {
        this.filePath = filePath;
    }

}

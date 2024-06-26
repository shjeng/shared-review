package com.sreview.sharedReview.domain.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = -1549988073L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoard board = new QBoard("board");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final ListPath<BoardTag, QBoardTag> boardTag = this.<BoardTag, QBoardTag>createList("boardTag", BoardTag.class, QBoardTag.class, PathInits.DIRECT2);

    public final QCategory category;

    public final NumberPath<Integer> commentCount = createNumber("commentCount", Integer.class);

    public final ListPath<Comment, QComment> comments = this.<Comment, QComment>createList("comments", Comment.class, QComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final EnumPath<com.sreview.sharedReview.domain.jpa.entity.enumType.DELETE> deleteYn = createEnum("deleteYn", com.sreview.sharedReview.domain.jpa.entity.enumType.DELETE.class);

    public final NumberPath<Integer> favoriteCount = createNumber("favoriteCount", Integer.class);

    public final ListPath<Image, QImage> images = this.<Image, QImage>createList("images", Image.class, QImage.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final ListPath<LikeEntity, QLikeEntity> likes = this.<LikeEntity, QLikeEntity>createList("likes", LikeEntity.class, QLikeEntity.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public final QUser user;

    public final NumberPath<Integer> viewsCount = createNumber("viewsCount", Integer.class);

    public QBoard(String variable) {
        this(Board.class, forVariable(variable), INITS);
    }

    public QBoard(Path<? extends Board> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoard(PathMetadata metadata, PathInits inits) {
        this(Board.class, metadata, inits);
    }

    public QBoard(Class<? extends Board> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category"), inits.get("category")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}


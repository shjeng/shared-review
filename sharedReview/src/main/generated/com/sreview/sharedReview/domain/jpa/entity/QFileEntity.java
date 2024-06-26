package com.sreview.sharedReview.domain.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFileEntity is a Querydsl query type for FileEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFileEntity extends EntityPathBase<FileEntity> {

    private static final long serialVersionUID = -967242002L;

    public static final QFileEntity fileEntity = new QFileEntity("fileEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Long> connectionId = createNumber("connectionId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final StringPath ext = createString("ext");

    public final NumberPath<Long> fileSize = createNumber("fileSize", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath originName = createString("originName");

    public final StringPath path = createString("path");

    public final StringPath savedName = createString("savedName");

    public final EnumPath<com.sreview.sharedReview.domain.jpa.entity.enumType.FILE_STATUS> status = createEnum("status", com.sreview.sharedReview.domain.jpa.entity.enumType.FILE_STATUS.class);

    public QFileEntity(String variable) {
        super(FileEntity.class, forVariable(variable));
    }

    public QFileEntity(Path<? extends FileEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFileEntity(PathMetadata metadata) {
        super(FileEntity.class, metadata);
    }

}


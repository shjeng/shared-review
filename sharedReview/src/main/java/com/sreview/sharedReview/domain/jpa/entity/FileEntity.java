package com.sreview.sharedReview.domain.jpa.entity;

import com.sreview.sharedReview.domain.jpa.entity.enumType.FILE_STATUS;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class FileEntity extends BaseEntity {


    @Id
    @Column(name = "file_id")
    @GeneratedValue
    private Long id;

    @Column(name = "file_origin_name")
    private String originName;

    @Column(name = "file_saved_name")
    private String savedName;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "file_ext")
    private String ext;

    @Column(name = "file_path")
    private String path;

    @Enumerated(EnumType.STRING)
    private FILE_STATUS status;

    @Column(name = "connected_id")
    private Long connectionId;
}

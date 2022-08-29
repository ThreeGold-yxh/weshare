package com.yxh.weshare.bean.pojo;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class Comment {
    private Integer wsCommentId;

    private String wsCommentUserNickname;

    private String wsCommentContent;

    private Timestamp wsCommentPublishDate;

    private Integer wsCommentParentId;

    private Integer wsCommentGoodsId;

}
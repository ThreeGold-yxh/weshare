package com.yxh.weshare.bean.bo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Xinhao Yi
 * @date 2022/8/2 3:04
 * @description:
 */
@Data
public class CommentTreeNodeBo {
    private Integer wsCommentId;

    private String wsCommentUserNickname;

    private String wsCommentContent;

    private String wsCommentPublishDate;

    private Integer wsCommentParentId;

    private String wsCommentParentUserNickname;

    List<CommentTreeNodeBo> childrenList;
}

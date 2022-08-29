package com.yxh.weshare.service;

import com.yxh.weshare.bean.pojo.Comment;

import java.util.List;

/**
 * @author Xinhao Yi
 * @date 2022/7/22 1:02
 * @description:
 */
public interface CommentService {
    /**
     * 根据goodsId 得到根据日期逆序的一组comments
     * Get a set of comments in reverse order by date based on goodsId
     * @param goodsId
     * @return
     */
    public List<Comment> commentListOrderByDateDESC(Integer goodsId);

    /**
     * 根据goodsId 得到根据日期顺序的一组comments
     * Get a set of comments in date order based on goodsId
     * @param goodsId
     * @return
     */
    public List<Comment> commentListOrderByDate(Integer goodsId);

    /**
     * add comments
     * @param comment
     * @return
     */
    Boolean addComment(Comment comment);

    /**
     * 根据commentId选择comment
     * choose comment by id
     * @param wsCommentParentId
     * @return
     */
    Comment selectByCommentId(Integer wsCommentParentId);
}

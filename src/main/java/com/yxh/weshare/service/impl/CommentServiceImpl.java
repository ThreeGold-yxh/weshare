package com.yxh.weshare.service.impl;
import com.yxh.weshare.bean.pojo.Comment;
import com.yxh.weshare.bean.pojo.CommentExample;
import com.yxh.weshare.mapper.CommentMapper;
import com.yxh.weshare.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author Xinhao Yi
 * @date 2022/7/21 1:04
 * @description:
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Override
    public List<Comment> commentListOrderByDateDESC(Integer goodsId) {
        CommentExample commentExample = new CommentExample();
        commentExample.setOrderByClause("ws_comment_publish_date DESC");

        CommentExample.Criteria criteria = commentExample.createCriteria();

        criteria.andWsCommentGoodsIdEqualTo(goodsId);

        return commentMapper.selectByExample(commentExample);
    }

    @Override
    public List<Comment> commentListOrderByDate(Integer goodsId) {
        CommentExample commentExample = new CommentExample();
        commentExample.setOrderByClause("ws_comment_publish_date");

        CommentExample.Criteria criteria = commentExample.createCriteria();

        criteria.andWsCommentGoodsIdEqualTo(goodsId);

        return commentMapper.selectByExample(commentExample);
    }

    @Override
    public Boolean addComment(Comment comment) {
        int insert = commentMapper.insert(comment);
        return insert != 0;
    }

    @Override
    public Comment selectByCommentId(Integer wsCommentParentId) {
        return commentMapper.selectByPrimaryKey(wsCommentParentId);
    }


}

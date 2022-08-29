package com.yxh.weshare.controller;

import com.yxh.weshare.annotation.WebLoginRequired;
import com.yxh.weshare.bean.bo.CommentBo;
import com.yxh.weshare.bean.bo.CommentTreeNodeBo;
import com.yxh.weshare.bean.pojo.Category;
import com.yxh.weshare.bean.pojo.Comment;
import com.yxh.weshare.bean.pojo.Goods;
import com.yxh.weshare.bean.pojo.User;
import com.yxh.weshare.service.CategoryService;
import com.yxh.weshare.service.CommentService;
import com.yxh.weshare.service.GoodsService;
import com.yxh.weshare.service.UserService;
import com.yxh.weshare.utils.commentTree.CommentTreeGenerator;
import com.yxh.weshare.utils.converter.TypeConverterUtil;
import com.yxh.weshare.utils.url.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Xinhao Yi
 * @date 2022/7/28 18:15
 * @description:
 */
@Controller
@RequestMapping("fore/goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;


    @RequestMapping(value = "goods-detail", method = RequestMethod.GET)
    @WebLoginRequired
    public String goodsDetailShow(@RequestParam("id") Integer id, Model model, HttpServletRequest request){
        List<Category> categories = categoryService.list();
        Goods goods = goodsService.selectById(id);

        User user = userService.selectByUserId(goods.getWsGoodsOwnerId());


        model.addAttribute("seller", user);
        model.addAttribute("goods", goods);
        model.addAttribute("categories", categories);

        //这里要保存这个页面的url 作为previouspath，放进session中
        // Here you have to save the url of this page as a previouspath and put it into the session
        String previouspath = UrlUtils.getCurrentComleteURL(request);
        System.out.println(previouspath);
        request.getSession().setAttribute("previouspath", previouspath);

        // 处理评论区 Handling the comments section
        // 根据goodsId 拿到一个comments  Get a comments based on goodsId
        List<Comment> comments = commentService.commentListOrderByDate(id);

        List<CommentTreeNodeBo> commentTreeNodeBoList = new ArrayList<>();
        for (Comment comment : comments) {
            CommentTreeNodeBo commentTreeNodeBo = TypeConverterUtil.commentConvertToCommentTreeNodeBo(comment);
            if (-1 != comment.getWsCommentParentId()){
                Comment parent = commentService.selectByCommentId(comment.getWsCommentParentId());
                commentTreeNodeBo.setWsCommentParentUserNickname(parent.getWsCommentUserNickname());
            }
            commentTreeNodeBoList.add(commentTreeNodeBo);
        }

        CommentTreeGenerator commentTreeGenerator = new CommentTreeGenerator(commentTreeNodeBoList);

        List<CommentTreeNodeBo> rootList = commentTreeGenerator.generateCommentForest();

        //遍历完树之后，生成一个二级的格式来输出  After traversing the tree, a secondary format is generated to output
        List<CommentTreeNodeBo> rootVoList = commentTreeGenerator.generateTwoLevelForestRootNodesForView(rootList);

        model.addAttribute("goodsId", id);
        model.addAttribute("rootVoList", rootVoList);

        return "fore/goodsDetail";
    }


    @RequestMapping(value = "comment/add/{goodsId}", method = RequestMethod.POST)
    @WebLoginRequired
    public String addComment(CommentBo commentBo, @PathVariable(value = "goodsId") Integer goodsId, Model model, HttpServletRequest request){

        /* 获取当前user */
        Object obj = request.getSession().getAttribute("user");
        if (obj == null){
            return "fore/notLoginFalse";
        }
        User user = (User) obj;

        Comment comment = new Comment();
        comment.setWsCommentContent(commentBo.getComment());
        comment.setWsCommentParentId(-1);
        comment.setWsCommentUserNickname(user.getWsUserNickname());
        comment.setWsCommentGoodsId(goodsId);

        Timestamp currentTime = new Timestamp(new Date().getTime());
        comment.setWsCommentPublishDate(currentTime);

        Boolean isAdd = commentService.addComment(comment);

        String previouspath = (String)request.getSession().getAttribute("previouspath");
        return "redirect:" + previouspath;
    }


    @RequestMapping(value = "comment/reply/{goodsId}", method = RequestMethod.POST)
    @WebLoginRequired
    public String replyComment(CommentBo commentBo, @PathVariable(value = "goodsId") Integer goodsId, @RequestParam("commentId") Integer commentId, Model model, HttpServletRequest request){

        /* 获取当前user */
        Object obj = request.getSession().getAttribute("user");
        if (obj == null){
            return "fore/notLoginFalse";
        }
        User user = (User) obj;

        Comment comment = new Comment();
        comment.setWsCommentContent(commentBo.getComment());
        comment.setWsCommentParentId(commentId);
        comment.setWsCommentUserNickname(user.getWsUserNickname());
        comment.setWsCommentGoodsId(goodsId);

        Timestamp currentTime = new Timestamp(new Date().getTime());
        comment.setWsCommentPublishDate(currentTime);

        Boolean isAdd = commentService.addComment(comment);

        String previouspath = (String)request.getSession().getAttribute("previouspath");
        return "redirect:" + previouspath;
    }
}

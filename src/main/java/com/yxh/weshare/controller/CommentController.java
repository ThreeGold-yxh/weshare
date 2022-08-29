package com.yxh.weshare.controller;

import com.yxh.weshare.service.CategoryService;
import com.yxh.weshare.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Xinhao Yi
 * @date 2022/8/2 2:57
 * @description:
 */
@Controller
@RequestMapping("fore/comment")
public class CommentController {
    @Autowired
    GoodsService goodsService;

    @Autowired
    CategoryService categoryService;





}

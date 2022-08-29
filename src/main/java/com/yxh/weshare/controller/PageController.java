package com.yxh.weshare.controller;

import com.yxh.weshare.annotation.SellerRequired;
import com.yxh.weshare.annotation.WebLoginRequired;
import com.yxh.weshare.bean.pojo.Category;
import com.yxh.weshare.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Xinhao Yi
 * @date 2022/7/25 0:55
 * @description:
 */
@Controller
@RequestMapping("fore/page")
public class PageController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String toLoginPage(Model model, HttpSession session){
        //todo 这里要检查这个用户登陆了没有，不能重复登录
        // Check here whether the user is logged in, no duplicate logins
        if (null != session.getAttribute("user")){
            return "fore/loginRepeatFail";
        }
        return "fore/login";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String toRegisterPage(Model model){
        return "fore/register";
    }



    @WebLoginRequired
    @SellerRequired
    @RequestMapping(value = "add/goods", method = RequestMethod.GET)
    public String toSellerGoodsAddPage(Model model){
        List<Category> categories = categoryService.list();
        model.addAttribute("categories", categories);
        return "fore/sellerGoodsAdd";
    }


    //fore/page/access-deny
    @RequestMapping(value = "access-deny", method = RequestMethod.GET)
    public String accessDeny(Model model){
        return "fore/accessDeny";
    }
}

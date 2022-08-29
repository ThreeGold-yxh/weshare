package com.yxh.weshare.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yxh.weshare.annotation.WebLoginRequired;
import com.yxh.weshare.bean.pojo.Category;
import com.yxh.weshare.bean.pojo.Goods;
import com.yxh.weshare.bean.pojo.Order;
import com.yxh.weshare.bean.pojo.User;
import com.yxh.weshare.bean.vo.OrderShowVo;
import com.yxh.weshare.service.CategoryService;
import com.yxh.weshare.service.GoodsService;
import com.yxh.weshare.service.OrderService;
import com.yxh.weshare.service.UserService;
import com.yxh.weshare.utils.converter.TypeConverterUtil;
import com.yxh.weshare.utils.page.PageUtil;
import com.yxh.weshare.utils.url.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
 * @date 2022/7/24 20:23
 * @description:
 */
@Controller
@RequestMapping("fore/buyer")
public class BuyerController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;


    @RequestMapping(value = "confirm-pay", method = RequestMethod.GET)
    public String buyerConfirmPay(@RequestParam(value = "id") Integer id, Model model, HttpServletRequest request) {
        Goods goods = goodsService.selectById(id);

        /* 获取当前user */
        // Get current user
        Object obj = request.getSession().getAttribute("user");
        if (obj == null) {
            return "fore/notLoginFalse";
        }
        User user = (User) obj;

        //卖完了  Sold out
        if (goods.getWsGoodsAmount() <= 0) {
            model.addAttribute("goods", goods);
            model.addAttribute("ErrMsg", "You can't buy this item, it has been sold out!");
            return "fore/goodsDetail";
        }

        //下架了 Removed from the shelves
        if (goods.getWsGoodsStatus() == 0) {
            model.addAttribute("goods", goods);
            model.addAttribute("ErrMsg", "You can't buy this item, it has been taken down!");
            return "fore/goodsDetail";
        }

        // 当前登录的用户就是这件商品的拥有者  The currently logged in user is the owner of this item
        if (user.getWsUserId() == null){
            model.addAttribute("goods", goods);
            model.addAttribute("ErrMsg", "Please Login First");
            return "fore/goodsDetail";
        }

        if (goods.getWsGoodsOwnerId().equals(user.getWsUserId())){
            model.addAttribute("goods", goods);
            model.addAttribute("ErrMsg", "You can't buy your own goods");
            return "fore/goodsDetail";
        }


        model.addAttribute("goods", goods);
        return "fore/confirmPay";
    }

    @RequestMapping(value = "order/add", method = RequestMethod.GET)
    @WebLoginRequired
    public String buyerOrderAdd(@RequestParam(value = "id") Integer id, Model model, HttpServletRequest request) {
        Order order = new Order();
        Goods goods = goodsService.selectById(id);


        /* 获取当前user */
        // Get current user
        Object obj = request.getSession().getAttribute("user");
        if (obj == null) {
            return "fore/notLoginFalse";
        }
        User user = (User) obj;

        // 当前登录的用户就是这件商品的拥有者
        // The currently logged in user is the owner of this item
        if (user.getWsUserId() == null){
            model.addAttribute("goods", goods);
            model.addAttribute("ErrMsg", "Please Login First");
            return "fore/goodsDetail";
        }

        if (goods.getWsGoodsOwnerId().equals(user.getWsUserId())){
            model.addAttribute("goods", goods);
            model.addAttribute("ErrMsg", "You can't buy your own goods");
            return "fore/goodsDetail";
        }

        // 获得当前时间 set进 pojo中
        // Get the current time set into the pojo
        Timestamp currentTime = new Timestamp(new Date().getTime());

        //wait for shipment
        order.setWsOrderStatus(0);
        order.setWsOrderGoodsId(id);
        order.setWsOrderBuyerId(user.getWsUserId());
        order.setWsOrderSellerId(goods.getWsGoodsOwnerId());
        order.setWsOrderAddress(user.getWsUserAddress());
        order.setWsOrderPrice(goods.getWsGoodsPrice());
        order.setWsOrderCreateDate(currentTime);


        Integer isAdd = orderService.addOrderAndMinusAmount(order, goods);


        //没有库存了
        // No more stock
        if (404 == isAdd) {
            model.addAttribute("goods", goods);
            model.addAttribute("ErrMsg", "You can't buy this item, it has been sold out!");
            return "fore/goodsDetail";
//            return "redirect:/fore/goods/goods-detail?id=" + id;
        }

        if (405 == isAdd){
            model.addAttribute("goods", goods);
            model.addAttribute("ErrMsg", "You can't buy this item, it has been taken down!");
            return "fore/goodsDetail";
        }

        return "fore/paySuccess";
    }


    @RequestMapping(value = "order/list", method = RequestMethod.GET)
    @WebLoginRequired
    public String buyerOrderList(@RequestParam(value = "page", defaultValue = "1")Integer page, Model model, HttpServletRequest request) {

        //这里要保存这个页面的url 作为previouspath，放进session中
        // Here you have to save the url of this page as a previouspath and put it into the session
        String previouspath = UrlUtils.getCurrentComleteURL(request);
        System.out.println(previouspath);
        request.getSession().setAttribute("previouspath", previouspath);

        model.addAttribute("title", "Buyer Order List");

        List<Category> categories = categoryService.list();

        /* 获取当前user */
        // Get current user
        Object obj = request.getSession().getAttribute("user");
        if (obj == null) {
            return "fore/notLoginFalse";
        }
        User user = (User) obj;

        // 20
        int pageSize = 5;
        // 开启分页
        // Turn on paging
        PageHelper.startPage(page, pageSize);

        List<Order> originalOrderList = orderService.selectBuyerOrdersByUser(user);

        //拿到当前页面所需的list,保存在pageInfo内部
        // Get the list you need for the current page and save it inside pageInfo
        PageInfo<Order> pageInfo = new PageInfo<>(originalOrderList);

        List<Order> orderList = new PageUtil<Order>().setPageHelperDataToModelAndReturnTheList(model, page, pageInfo, request);

        List<OrderShowVo> orderVoList = new ArrayList<>();

        for (Order order : orderList) {
            Goods goods = goodsService.selectById(order.getWsOrderGoodsId());
            User buyer = userService.selectByUserId(order.getWsOrderBuyerId());
            User seller = userService.selectByUserId(order.getWsOrderSellerId());
            OrderShowVo orderShowVo = TypeConverterUtil.OrderConvertToOrderShowVo(order, goods, buyer, seller);
            orderVoList.add(orderShowVo);
        }


        model.addAttribute("orderVoList", orderVoList);
        model.addAttribute("categories", categories);


        return "fore/buyerOderList";
    }

    // confirm-receive
    @RequestMapping(value = "order/confirm-receive", method = RequestMethod.GET)
    @WebLoginRequired
    public String orderConfirmReceive(@RequestParam("id") Integer id, Model model, HttpServletRequest request) {
        Boolean isConfirm = orderService.confirmReceive(id);
        String previouspath = (String)request.getSession().getAttribute("previouspath");
        if (isConfirm){
            return "redirect:" + previouspath;
        }else{
            return "fore/somethingError";
        }
    }


    //score-for-trade
    @RequestMapping(value = "order/score-for-trade", method = RequestMethod.GET)
    @WebLoginRequired
    public String orderScoreForTrade(@RequestParam("id") Integer id, Model model, HttpServletRequest request) {
        model.addAttribute("title", "Score For The Trade");
        model.addAttribute("id", id);
        return "fore/rateForTradeFromBuyer";
    }


    // deal-with-score
    @RequestMapping(value = "order/deal-with-score", method = RequestMethod.GET)
    @WebLoginRequired
    public String orderDealWithScore(@RequestParam("id") Integer id, @RequestParam("scoreLevel") Integer scoreLevel, Model model, HttpServletRequest request) {

        // 更新order的status，然后更新用户的评分
        // Update the order's status and then update the user's rating
        Boolean isOk = orderService.dealWithScoreFromBuyer(id, scoreLevel);
        if (isOk){
            return "fore/dealWithScoreSuccess";
        }else {
            return "fore/somethingError";
        }
    }


}

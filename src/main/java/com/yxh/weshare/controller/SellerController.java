package com.yxh.weshare.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yxh.weshare.annotation.SellerRequired;
import com.yxh.weshare.annotation.WebLoginRequired;
import com.yxh.weshare.bean.bo.AddGoodsBo;
import com.yxh.weshare.bean.bo.EditGoodsBo;
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
import com.yxh.weshare.utils.fileUpload.FileUploadUtil;
import com.yxh.weshare.utils.page.PageUtil;
import com.yxh.weshare.utils.url.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Xinhao Yi
 * @date 2022/7/24 20:23
 * @description:
 */
@Controller
@RequestMapping("fore/seller")
public class SellerController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "goods/add", method = RequestMethod.POST)
    public String sellerGoodsAdd(@Validated AddGoodsBo goodsBO, Errors errors, Model model, HttpServletRequest request){

        List<Category> categories = categoryService.list();

        // hibernate validator校验器检查到有报错
        // If the calibrator checks that there is an error reported
        if (errors.hasErrors()){
//            AlertUtil.alert(response, request, "register", AlertMessageUtil.REGISTER_Error_input_ALERT_MSG);
            //todo 还没有检测是否能这样报错  --> 这种方法不行，报错信息太多了，很难看
            // No test yet to see if errors can be reported this way --> this method doesn't work, too many error messages and it's hard to read
//            model.addAttribute("ErrMsg", errors.getFieldErrors().get(0));
//            ObjectError objectError = errors.getAllErrors().get(0);
//            String errName = objectError.toString();
//            model.addAttribute("ErrMsg", errName);
            model.addAttribute("ErrMsg", "Please fulfill the form!");
            model.addAttribute("goodsBO", goodsBO);
            model.addAttribute("categories", categories);
            return "fore/sellerGoodsAdd";
        }

        //在后端额外校验这个prize。不允许超过两位小数
        // No more than two decimal places are allowed.
        Double prize = goodsBO.getWsGoodsPrice();

        String upToTwoDigits = "(^-?[1-9](\\d+)?(\\.\\d{1,2})?$)|(^-?0$)|(^-?\\d\\.\\d{1,2}$)";
        Pattern pattern = Pattern.compile(upToTwoDigits);
        Matcher matcher = pattern.matcher(String.valueOf(prize));

        //如果不匹配，就要报错  If it does not match, an error will be reported
        if (!matcher.matches()){
            model.addAttribute("ErrMsg", "illegal price input");
            model.addAttribute("goodsBO", goodsBO);
            model.addAttribute("categories", categories);
            return "fore/sellerGoodsAdd";
        }


        MultipartFile originFile = goodsBO.getWsGoodsImage();

        //上传文件，并拿到这个文件保存的地址
        // Upload the file and get the address where this file is saved
        String relativePath = FileUploadUtil.upload(originFile, request);

        //bo -> pojo
        Goods goods = TypeConverterUtil.AddGoodsBOConvertToGoods(goodsBO);

        //设置pojo图片路径
        // Set pojo image path
        goods.setWsGoodsImage(relativePath);

        // 获得当前时间 set进 pojo中
        // Get the current time set into the pojo
        Timestamp currentTime = new Timestamp(new Date().getTime());
        goods.setWsGoodsPublishDate(currentTime);

        //设置 pojo状态  1代表正常  0代表下架
        // Set pojo status 1 for normal 0 for down
        goods.setWsGoodsStatus(1);


        /* 获取当前user */
        // Get current user
        // todo 下面这句注释掉的话有大问题，就是这个拿user才导致了重定向到登录页面报错！！！！！要改写法
        // User user = (User)request.getSession().getAttribute("user");

        Object obj = request.getSession().getAttribute("user");
        if (obj == null){
            return "fore/notLoginFalse";
        }
        User user = (User) obj;
        /* 获取当前user */


        //设置该商品主人的user id
        // Set the user id of the owner of the item
        goods.setWsGoodsOwnerId(user.getWsUserId());

        Boolean isOK = goodsService.add(goods);

        if (isOK){
            return "fore/sellerGoodsAddSuccess";
        }else {
            model.addAttribute("goodsBO", goodsBO);
            model.addAttribute("ErrMsg", "Sorry, can't upload your item");
            model.addAttribute("categories", categories);
            return "fore/sellerGoodsAdd";
        }
    }

    @RequestMapping(value = "order/list", method = RequestMethod.GET)
    @WebLoginRequired
    public String sellerOrderList(@RequestParam(value = "page", defaultValue = "1")Integer page, Model model, HttpServletRequest request){

        //这里要保存这个页面的url 作为previouspath，放进session中
        // Here you have to save the url of this page as a previouspath and put it into the session
        String previouspath = UrlUtils.getCurrentComleteURL(request);
        System.out.println(previouspath);
        request.getSession().setAttribute("previouspath", previouspath);


        model.addAttribute("title", "Seller Order List");

        List<Category> categories = categoryService.list();

        /* 获取当前user */
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

        List<Order> originalOrderList = orderService.selectSellerOrdersByUser(user);

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


        return "fore/sellerOrderList";
    }


    //confirm-delivery
    @RequestMapping(value = "order/confirm-delivery", method = RequestMethod.GET)
    @WebLoginRequired
    public String orderConfirmDelivery(@RequestParam("id") Integer id, Model model, HttpServletRequest request) {
        Boolean isConfirm = orderService.confirmDelivery(id);
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
        return "fore/rateForTradeFromSeller";
    }


    // deal-with-score
    @RequestMapping(value = "order/deal-with-score", method = RequestMethod.GET)
    @WebLoginRequired
    public String orderDealWithScore(@RequestParam("id") Integer id, @RequestParam("scoreLevel") Integer scoreLevel, Model model, HttpServletRequest request) {

        // 更新order的status，然后更新用户的评分
        // Update the order's status and then update the user's rating
        //id is orderId
        Boolean isOk = orderService.dealWithScoreFromSeller(id, scoreLevel);
        if (isOk){
            return "fore/dealWithScoreSuccess";
        }else {
            return "fore/somethingError";
        }
    }

    @RequestMapping(value = "goods/list", method = RequestMethod.GET)
    @WebLoginRequired
    public String sellerGoodsList(@RequestParam(value = "page", defaultValue = "1")Integer page, Model model, HttpServletRequest request){

        //这里要保存这个页面的url 作为previouspath，放进session中
        // Here you have to save the url of this page as a previouspath and put it into the session
        String previouspath = UrlUtils.getCurrentComleteURL(request);
        System.out.println(previouspath);
        request.getSession().setAttribute("previouspath", previouspath);


        model.addAttribute("title", "My Goods For Sale");


        /* 获取当前user */
        Object obj = request.getSession().getAttribute("user");
        if (obj == null) {
            return "fore/notLoginFalse";
        }
        User user = (User) obj;

        // 20
        int pageSize = 10;
        // 开启分页
        // Turn on paging
        PageHelper.startPage(page, pageSize);

        List<Goods> originalGoodsList = goodsService.selectGoodsListBasedOnOwner(user);

        //拿到当前页面所需的list,保存在pageInfo内部
        // Get the list you need for the current page and save it inside pageInfo
        PageInfo<Goods> pageInfo = new PageInfo<>(originalGoodsList);

        List<Goods> goodsList = new PageUtil<Goods>().setPageHelperDataToModelAndReturnTheList(model, page, pageInfo, request);

        model.addAttribute("goodsList", goodsList);

        return "fore/sellerGoodsList";
    }


    @RequestMapping(value = "goods/edit", method = RequestMethod.GET)
    @WebLoginRequired
    public String sellerGoodsEditGet(@RequestParam("id") Integer id, Model model, HttpServletRequest request){
        List<Category> categories = categoryService.list();
        model.addAttribute("categories", categories);

        Goods goods = goodsService.selectById(id);

        Integer wsGoodsCategoryId = goods.getWsGoodsCategoryId();

        Category selectedCategory = categoryService.selectByCategoryID(wsGoodsCategoryId);

        model.addAttribute("goods", goods);

        model.addAttribute("selectedCategory", selectedCategory);

        return "fore/sellerGoodsEdit";


    }

    @RequestMapping(value = "goods/deal-with-edit/{id}", method = RequestMethod.POST)
    @WebLoginRequired
    public String sellerGoodsEditPost(@Validated EditGoodsBo goodsBO, Errors errors, @PathVariable("id") Integer id, Model model, HttpServletRequest request){
        // hibernate validator校验器检查到有报错
        // todo 注意Errors errors 必须要紧跟在@Validated AddGoodsBo goodsBO后面
        // take care that Errors errors must be directly after @Validated AddGoodsBo goodsBO
        if (errors.hasErrors()){
//            AlertUtil.alert(response, request, "register", AlertMessageUtil.REGISTER_Error_input_ALERT_MSG);
            //todo 还没有检测是否能这样报错  --> 这种方法不行，报错信息太多了，很难看
//            model.addAttribute("ErrMsg", errors.getFieldErrors().get(0));
//            ObjectError objectError = errors.getAllErrors().get(0);
//            String errName = objectError.getObjectName();
            model.addAttribute("ErrMsg", "please fulfill the form!");
//            model.addAttribute("ErrMsg", "error input !");
            return "fore/sellerGoodsEdit";
        }

        //在后端额外校验这个prize。不允许超过两位小数  No more than two decimal places are allowed.
        Double prize = goodsBO.getWsGoodsPrice();

        String upToTwoDigits = "(^-?[1-9](\\d+)?(\\.\\d{1,2})?$)|(^-?0$)|(^-?\\d\\.\\d{1,2}$)";
        Pattern pattern = Pattern.compile(upToTwoDigits);
        Matcher matcher = pattern.matcher(String.valueOf(prize));

        //如果不匹配，就要报错  If it does not match, an error will be reported
        if (!matcher.matches()){
            model.addAttribute("ErrMsg", "illegal price input");
            return "fore/sellerGoodsEdit";
        }


        MultipartFile originFile = goodsBO.getWsGoodsImage();

        //拿到文件名字
        String originalFilename = originFile.getOriginalFilename();


        //bo -> pojo
        Goods goods = TypeConverterUtil.editGoodsBOConvertToGoods(goodsBO);

        //注意设置这个goods的id  Note the id to set this goods
        goods.setWsGoodsId(id);

        if (null != originalFilename && !"".equals(originalFilename)){
            //上传文件，并拿到这个文件保存的地址  Upload the file and get the address where this file is saved
            String relativePath = FileUploadUtil.upload(originFile, request);
            //设置pojo图片路径  Set pojo image path
            goods.setWsGoodsImage(relativePath);
        }


        // 获得当前时间 set进 pojo中  Get the current time set into the pojo
//        Timestamp currentTime = new Timestamp(new Date().getTime());
//        goods.setWsGoodsPublishDate(currentTime);

        //设置 pojo状态  1代表正常  0代表下架  Set pojo status 1 for normal 0 for down
//        goods.setWsGoodsStatus(1);


        /* 获取当前user */
        // todo 下面这句注释掉的话有大问题，就是这个拿user才导致了重定向到登录页面报错！！！！！要改写法
        // There is a big problem with the following commented out sentence,
        // it is this take user that causes the redirect to the login page to report an error ！！！！！ To change the way you write
        // User user = (User)request.getSession().getAttribute("user");


        Boolean isOK = goodsService.updateByGoods(goods);


        if (isOK){
            return "fore/sellerGoodsEditSuccess";
        }else {
            model.addAttribute("ErrMsg", "Sorry, can't edit your item");
            return "fore/sellerGoodsEdit";
        }
    }



    @RequestMapping(value = "goods/delete", method = RequestMethod.GET)
    @WebLoginRequired
    public String sellerGoodsDelete(@RequestParam("id") Integer id, Model model, HttpServletRequest request){
        String previouspath = (String)request.getSession().getAttribute("previouspath");
        Boolean isDelete = orderService.removeGoodsBasedOnGoodsId(id);
        if (isDelete){
            return "redirect:" + previouspath;
        }else {
            return "fore/goodsDeleteFail";
        }
    }
}

package com.yxh.weshare.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.regexp.internal.RE;
import com.yxh.weshare.annotation.AdminOrSupporterRequired;
import com.yxh.weshare.annotation.AdminRequired;
import com.yxh.weshare.bean.bo.SearchBo;
import com.yxh.weshare.bean.pojo.*;
import com.yxh.weshare.bean.vo.AdminUserVo;
import com.yxh.weshare.bean.vo.OrderShowVo;
import com.yxh.weshare.service.*;
import com.yxh.weshare.utils.converter.TypeConverterUtil;
import com.yxh.weshare.utils.freechart.MyServletUtilities;
import com.yxh.weshare.utils.page.PageUtil;
import com.yxh.weshare.utils.recommend.RecommendCalculateUtil;
import com.yxh.weshare.utils.url.UrlUtils;
import org.aspectj.weaver.ast.Or;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Xinhao Yi
 * @date 2022/7/21 1:50
 * @description:
 */
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    AuthorityService authorityService;

    @Autowired
    OrderService orderService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    SearchService searchService;

    @Autowired
    RecommendService recommendService;

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String search(SearchBo searchBo, Model model, HttpSession session) {
        //search 要区分进入search前的 url
        // search to distinguish the url before entering search
        String previouspath = (String) session.getAttribute("previouspath");

        String keyword = searchBo.getKeyword();


        //尝试把输入内容转化为数字
        // Attempt to convert input into numbers
        Integer id = null;
        try {
            id = Integer.parseInt(keyword);
        } catch (NumberFormatException e) {
            System.out.println("Your input " + keyword + " is not a number");
        }

        //标题栏
        // Title bar
        model.addAttribute("title", "Search Result");

        if (previouspath.contains("user/list") || previouspath.contains("home/show")) {

            List<User> userList = new ArrayList<>();

            if (null != id) {
                User user = userService.selectByUserId(id);
                if (null != user) {
                    userList.add(user);
                }
            }

            List<User> searchedUsers = searchService.getListOfUsersFromKeyWord(keyword);

            userList.addAll(searchedUsers);

            List<AdminUserVo> userVoList = new ArrayList<>();
            for (User user : userList) {
                Authority authority = authorityService.getAuthorityByAuthorityId(user.getAuthority());
                AdminUserVo userVo = TypeConverterUtil.userToAdminUserVo(user, authority);
                userVoList.add(userVo);
            }
            model.addAttribute("userVoList", userVoList);

            return "admin/userSearch";


        } else if (previouspath.contains("order/list")) {
            List<Order> orderList = new ArrayList<>();
            if (null != id) {
                Order order = orderService.selectOrderByOrderId(id);
                if (null != order) {
                    orderList.add(order);
                }
            }

            List<Order> searchedOrders = searchService.getListOfOrdersFromKeyWord(keyword);

            orderList.addAll(searchedOrders);

            List<OrderShowVo> orderVoList = new ArrayList<>();
            for (Order order : orderList) {
                Goods goods = goodsService.selectById(order.getWsOrderGoodsId());
                User buyer = userService.selectByUserId(order.getWsOrderBuyerId());
                User seller = userService.selectByUserId(order.getWsOrderSellerId());
                OrderShowVo orderShowVo = TypeConverterUtil.OrderConvertToOrderShowVo(order, goods, buyer, seller);
                orderVoList.add(orderShowVo);
            }

            model.addAttribute("orderVoList", orderVoList);
            return "admin/orderSearch";
        }


        return "redirect:/admin/home/show";
    }


    @RequestMapping(value = "access-deny", method = RequestMethod.GET)
    public String adminAccessDeny(Model model) {
        return "admin/adminAccessDeny";
    }

    @RequestMapping(value = "user/logout", method = RequestMethod.GET)
    public String adminLogOut(Model model, HttpSession session) {
        if (null == session.getAttribute("user")) {
            //not login
            return "admin/logoutFail";
        }
        session.removeAttribute("user");
        return "admin/logout";
    }


    //
    @RequestMapping(value = "home/show", method = RequestMethod.GET)
    @AdminOrSupporterRequired
    public String adminHomeShow(Model model, HttpServletRequest request) {
        //这里要保存这个页面的url 作为previouspath，放进session中
        // Here you have to save the url of this page as a previouspath and put it into the session
        String previouspath = UrlUtils.getCurrentComleteURL(request);
        System.out.println(previouspath);
        request.getSession().setAttribute("previouspath", previouspath);

        return "admin/adminHome";
    }


    @RequestMapping(value = "user/list", method = RequestMethod.GET)
    @AdminOrSupporterRequired
    public String adminUserList(@RequestParam(value = "page", defaultValue = "1") Integer page, Model model, HttpServletRequest request) {
        //这里要保存这个页面的url 作为previouspath，放进session中
        // Here you have to save the url of this page as a previouspath and put it into the session
        String previouspath = UrlUtils.getCurrentComleteURL(request);
        System.out.println(previouspath);
        request.getSession().setAttribute("previouspath", previouspath);


        //标题栏
        // Title bar
        model.addAttribute("title", "User List");

        //20
        int pageSize = 10;
        PageHelper.startPage(page, pageSize);
        List<User> originalUsers = userService.listAllOrderByUserId();

        //拿到当前页面所需的list,保存在pageInfo内部
        // Get the list you need for the current page and save it inside pageInfo
        PageInfo<User> pageInfo = new PageInfo<>(originalUsers);

        //这句话 已经把分页需要的当前 url 给放进去了
        // This sentence already puts in the current url needed for paging
        List<User> userList = new PageUtil<User>().setPageHelperDataToModelAndReturnTheList(model, page, pageInfo, request);

        // 把这个userList转换成 userVoList
        // Convert this userList to userVoList
        List<AdminUserVo> userVoList = new ArrayList<>();
        for (User user : userList) {
            Authority authority = authorityService.getAuthorityByAuthorityId(user.getAuthority());
            AdminUserVo userVo = TypeConverterUtil.userToAdminUserVo(user, authority);
            userVoList.add(userVo);
        }
        model.addAttribute("userVoList", userVoList);
        return "admin/userList";
    }

    ///user/detail
    @RequestMapping(value = "user/detail", method = RequestMethod.GET)
    @AdminOrSupporterRequired
    public String adminUserDetail(@RequestParam("id") Integer id, Model model, HttpServletRequest request) {
        //这里要保存这个页面的url 作为previouspath，放进session中
        // Here you have to save the url of this page as a previouspath and put it into the session
        String previouspath = UrlUtils.getCurrentComleteURL(request);
        System.out.println(previouspath);
        request.getSession().setAttribute("previouspath", previouspath);

        // 根据id取得user
        // Get user by id
        User user = userService.selectByUserId(id);

        // 根据authorityId 取得authority
        // get authority by id
        Authority authority = authorityService.getAuthorityByAuthorityId(user.getAuthority());
        AdminUserVo userVo = TypeConverterUtil.userToAdminUserVo(user, authority);
        model.addAttribute("userVo", userVo);

        //取得所有authority
        // Get all authorities
        List<Authority> authorities = authorityService.listAll();

        //删除和当前authority相同的那一个
        // Delete the one with the same authority as the current one
        Iterator<Authority> iterator = authorities.iterator();
        while (iterator.hasNext()) {
            Authority next = iterator.next();
            Integer authorityId = next.getWsUserAuthority();
            if (authorityId.equals(user.getAuthority())) {
                iterator.remove();
            }
        }
        model.addAttribute("authorities", authorities);
        return "admin/adminUserDetail";
    }

    //user/edit-authority
    @RequestMapping(value = "user/edit-authority", method = RequestMethod.GET)
    @AdminRequired
    public String editUserAuthority(Integer userId, Integer selectedAuthority, Model model, HttpServletRequest request) {
        String previouspath = (String) request.getSession().getAttribute("previouspath");

        User user = userService.selectByUserId(userId);

        //检查是不是root admin， 如果是的话，不允许修改
        // Check if you are the root admin, if so, no modifications are allowed
        if (1 == user.getAuthority() && "root".equals(user.getWsUserNickname()) && "root".equals(user.getWsUserAccount())) {
            //先根据userId拿到这个user  First get the user based on the userId
            // 根据authorityId 取得authority  get authority by id
            Authority authority = authorityService.getAuthorityByAuthorityId(user.getAuthority());
            AdminUserVo userVo = TypeConverterUtil.userToAdminUserVo(user, authority);
            model.addAttribute("userVo", userVo);

            //取得所有authority  Get all authorities
            List<Authority> authorities = authorityService.listAll();

            //删除和当前authority相同的那一个  Delete the one with the same authority as the current one
            Iterator<Authority> iterator = authorities.iterator();
            while (iterator.hasNext()) {
                Authority next = iterator.next();
                Integer authorityId = next.getWsUserAuthority();
                if (authorityId.equals(user.getAuthority())) {
                    iterator.remove();
                }
            }
            model.addAttribute("authorities", authorities);

            model.addAttribute("ErrMsg", "You can't edit the root admin");
            return "admin/adminUserDetail";
        }

        //拿当前登录的user  Take the currently logged in user
        /* 获取当前user */
        Object obj = request.getSession().getAttribute("user");
        if (obj == null) {
            return "admin/adminAccessDeny";
        }
        User currentUser = (User) obj;

        //检查是不是自己， 如果是的话，不允许修改  Check if it is yourself, if so, no modification is allowed
        if (currentUser.getWsUserId().equals(user.getWsUserId())) {
            //先根据userId拿到这个user First get the user based on the userId
            // 根据authorityId 取得authority
            Authority authority = authorityService.getAuthorityByAuthorityId(user.getAuthority());
            AdminUserVo userVo = TypeConverterUtil.userToAdminUserVo(user, authority);
            model.addAttribute("userVo", userVo);

            //取得所有authority  Get all authorities
            List<Authority> authorities = authorityService.listAll();

            //删除和当前authority相同的那一个  Delete the one with the same authority as the current one
            Iterator<Authority> iterator = authorities.iterator();
            while (iterator.hasNext()) {
                Authority next = iterator.next();
                Integer authorityId = next.getWsUserAuthority();
                if (authorityId.equals(user.getAuthority())) {
                    iterator.remove();
                }
            }
            model.addAttribute("authorities", authorities);

            model.addAttribute("ErrMsg", "You can't edit yourself");
            return "admin/adminUserDetail";
        }

        //否则可以修改  Otherwise it can be modified
        user.setAuthority(selectedAuthority);

        Boolean isUpdate = userService.updateUser(user);

        Authority authority = authorityService.getAuthorityByAuthorityId(user.getAuthority());
        AdminUserVo userVo = TypeConverterUtil.userToAdminUserVo(user, authority);
        model.addAttribute("userVo", userVo);

        //取得所有authority  Get all authorities
        List<Authority> authorities = authorityService.listAll();

        //删除和当前authority相同的那一个  Delete the one with the same authority as the current one
        Iterator<Authority> iterator = authorities.iterator();
        while (iterator.hasNext()) {
            Authority next = iterator.next();
            Integer authorityId = next.getWsUserAuthority();
            if (authorityId.equals(user.getAuthority())) {
                iterator.remove();
            }
        }
        model.addAttribute("authorities", authorities);

//        System.out.println(selectedAuthority);

//        System.out.println(userId);
        model.addAttribute("ErrMsg", "Successful!");
        return "admin/adminUserDetail";
    }


    @RequestMapping(value = "order/list", method = RequestMethod.GET)
    @AdminOrSupporterRequired
    public String adminOrderList(@RequestParam(value = "page", defaultValue = "1") Integer page, Model model, HttpServletRequest request) {
        //这里要保存这个页面的url 作为previouspath，放进session中
        // Here you have to save the url of this page as a previouspath and put it into the session
        String previouspath = UrlUtils.getCurrentComleteURL(request);
        System.out.println(previouspath);
        request.getSession().setAttribute("previouspath", previouspath);

        //标题栏 Title bar
        model.addAttribute("title", "Order List");

        //20
        int pageSize = 5;
        PageHelper.startPage(page, pageSize);
        List<Order> originalOrders = orderService.listAll();

        //拿到当前页面所需的list,保存在pageInfo内部
        // Get the list you need for the current page and save it inside pageInfo
        PageInfo<Order> pageInfo = new PageInfo<>(originalOrders);

        //这句话 已经把分页需要的当前 url 给放进去了
        // This sentence already puts in the current url needed for paging
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

        return "admin/adminOrderList";
    }

    @RequestMapping(value = "recommend-system/show", method = RequestMethod.GET)
    @AdminOrSupporterRequired
    public String recommendSystem(Model model, HttpServletRequest request) {
        //标题栏 Title bar
        model.addAttribute("title", "Recommend System");

        return "admin/recommendSystem";
    }

    @RequestMapping(value = "recommend-system/execute", method = RequestMethod.GET)
    @AdminOrSupporterRequired
    public String recommendSystemExecute(Model model, HttpServletRequest request) {

        List<UserGoodsScore> userGoodsScoreList = recommendService.getAllUserGoodsScoreList();
        List<UserFactor> userFactorList = recommendService.getAllUserFactorList();
        List<GoodsFactor> goodsFactorList = recommendService.getAllGoodsFactorList();

        RecommendCalculateUtil.modelExecute(userGoodsScoreList, userFactorList, goodsFactorList);

        List<UserFactor> userFactorListRes = RecommendCalculateUtil.getUserFactorList();

        List<GoodsFactor> goodsFactorListRes = RecommendCalculateUtil.getGoodsFactorList();

        List<UserGoodsScorePredict> userGoodsScorePredictList = RecommendCalculateUtil.getUserGoodsScorePredictList();

        //draw the picture - ScatterPlot
        // get loss list
        List<Double> lossList = RecommendCalculateUtil.getLossList();

        XYSeries data = new XYSeries("data");

        for (int i = 0; i < lossList.size(); i++) {
            data.add(i, lossList.get(i));
        }

        // 添加到数据集
        // add to dataSet
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(data);

        JFreeChart freeChart = ChartFactory.createScatterPlot(
                "loss function", // title
                "iteration", //x label
                "loss",//y label
                dataset, //数据集合  Data sets
                PlotOrientation.VERTICAL,
                true, // 是否显示子标题  Whether to display subheadings
                true, // 是否生成提示的标签  Whether to generate labels for prompts
                true // 是否生成URL链接  Whether to generate URL links
        );

        // convert chart to image and transfer to the front end
        try {
            String fileName = MyServletUtilities.saveChartAsJPEG(freeChart, 700, 400, request.getSession());
//            String chartURL = request.getContextPath() + "/chart?filename=" + fileName;
//            model.addAttribute("chartURLLine", chartURL);
            model.addAttribute("fileName", fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Boolean isUserFactorListAdd = recommendService.addOrUpdateUserFactorList(userFactorListRes);
        Boolean isGoodsFactorListAdd = recommendService.addOrUpdateGoodsFactorList(goodsFactorListRes);
        Boolean isUserGoodsScorePredictAdd = recommendService.addOrUpdateUserGoodsScorePredictList(userGoodsScorePredictList);

        //record we have already executed the algorithms
        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute("isRecommendAlgorithmsExecute",true);

        return "admin/recommendSystem";
    }

}

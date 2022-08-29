package com.yxh.weshare.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.yxh.weshare.annotation.WebLoginRequired;
import com.yxh.weshare.bean.bo.EdditUserBo;
import com.yxh.weshare.bean.bo.LoginUserBo;
import com.yxh.weshare.bean.bo.RegisterUserBo;
import com.yxh.weshare.bean.pojo.*;
import com.yxh.weshare.bean.vo.GoodsHistoryVo;
import com.yxh.weshare.service.GoodsService;
import com.yxh.weshare.service.UserService;
import com.yxh.weshare.utils.alert.AlertMessageUtil;
import com.yxh.weshare.utils.converter.TypeConverterUtil;
import com.yxh.weshare.utils.page.PageUtil;
import com.yxh.weshare.utils.url.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author Xinhao Yi
 * @date 2022/7/24 19:40
 * @description:
 */
@Controller
@RequestMapping("fore/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    GoodsService goodsService;

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(@Validated RegisterUserBo userBO, Errors errors, Model model) throws IOException {

        model.addAttribute("userBO", userBO);
        // hibernate validator校验器检查到有报错
        // The calibrator checks that there is an error reported
        if (errors.hasErrors()) {
//            AlertUtil.alert(response, request, "register", AlertMessageUtil.REGISTER_Error_input_ALERT_MSG);
            model.addAttribute("ErrMsg", AlertMessageUtil.REGISTER_ERROR_INPUT);
            return "fore/register";
        }

        //两次密码不一致
        // Two inconsistent passwords
        if (!userBO.getWsUserPassword().equals(userBO.getWsUserRepeatPassword())) {
            model.addAttribute("ErrMsg", AlertMessageUtil.REGISTER_PASSWORD_INCONSISTENCY);
            return "fore/register";
        }

        User user = TypeConverterUtil.RegisterUserBOConvertToUser(userBO);


        //account或邮箱已经被占用
        // Account or email address already taken
        Boolean isExist = userService.isExist(user);
        if (isExist) {
            model.addAttribute("ErrMsg", AlertMessageUtil.REGISTER_MULTI_USER);
            return "fore/register";
        }

        //添加该用户
        // Add this user
        userService.add(user);

        model.addAttribute("SuccessMsg", AlertMessageUtil.REGISTER_SUCCEESS);
        return "fore/registerSuccess";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@Validated LoginUserBo userBO, Errors errors, Model model, HttpSession session) {

        // hibernate validator校验器检查到有报错
        if (errors.hasErrors()) {
//            AlertUtil.alert(response, request, "register", AlertMessageUtil.REGISTER_Error_input_ALERT_MSG);
            model.addAttribute("ErrMsg", AlertMessageUtil.LOGIN_ERROR_INPUT);
            return "fore/login";
        }

        User user = TypeConverterUtil.LoginUserBOConvertToUser(userBO);
        Boolean isLogin = userService.login(user);

        //refresh the user (fulfill it)
        user = userService.selectByAccount(user);


        String previouspath = (String) session.getAttribute("previouspath");

        if (isLogin) {
            session.setAttribute("user", user);
            //这里要重定向
            // Here to redirect
            if (1 == user.getAuthority() || 2 == user.getAuthority()){
                return "redirect:/admin/home/show";
            }else {
                return "fore/loginSuccess";
            }
        } else {
            model.addAttribute("ErrMsg", "Your account or password is incorrect, please try again!");
            return "fore/login";
        }
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(Model model, HttpSession session) {
        //check whether login
        if (null == session.getAttribute("user")) {
            //not login
            return "fore/logoutFail";
        }

        session.removeAttribute("previouspath");
        session.removeAttribute("user");
        return "fore/logout";
    }


    @RequestMapping(value = "history/list", method = RequestMethod.GET)
    @WebLoginRequired
    public String historyList(@RequestParam(value = "page", defaultValue = "1") Integer page, Model model, HttpServletRequest request) {
        //标题栏  Title bar
        model.addAttribute("title", "My FootPrint");

        /* 获取当前user */
        Object obj = request.getSession().getAttribute("user");
        if (obj == null) {
            return "fore/notLoginFalse";
        }
        User user = (User) obj;

        int pageSize = 10;
        PageHelper.startPage(page, pageSize);

        List<History> originalHistories = userService.userHistoryList(user);

        //拿到当前页面所需的list,保存在pageInfo内部
        // Get the list you need for the current page and save it inside pageInfo
        PageInfo<History> pageInfo = new PageInfo<>(originalHistories);

        //currentpage, totalpages, currentUrl
        List<History> histories = new PageUtil<History>().setPageHelperDataToModelAndReturnTheList(model, page, pageInfo, request);

        //然后再根据这个histories去拿到一个 list of goodsVo
        // This histories is then used to get a list of goodsVo
        List<GoodsHistoryVo> goodsList = goodsService.selectGoodsListBasedOnHistories(histories);

        model.addAttribute("goodsList", goodsList);

        //这里要保存这个页面的url 作为previouspath，放进session中
        // Here you have to save the url of this page as a previouspath and put it into the session
        String previouspath = UrlUtils.getCurrentComleteURL(request);
        System.out.println(previouspath);
        request.getSession().setAttribute("previouspath", previouspath);

        return "fore/history";
    }

    @RequestMapping(value = "tracker/list", method = RequestMethod.GET)
    @WebLoginRequired
    public String trackerList(@RequestParam(value = "page", defaultValue = "1") Integer page, Model model, HttpServletRequest request) {

        //标题栏  Title bar
        model.addAttribute("title", "My Favourite");

        /* 获取当前user */
        // todo 下面这句注释掉的话有大问题，就是这个拿user才导致了重定向到登录页面报错！！！！！
        // User user = (User)request.getSession().getAttribute("user");

        Object obj = request.getSession().getAttribute("user");
        if (obj == null) {
            return "fore/notLoginFalse";
        }
        User user = (User) obj;
        /* 获取当前user */


        // 当我们调用PageHelper.startPage（）方法后下一条语句必须是你要调用的查询语句；
        // 我们的PageInfo传入的结果集，必须是我们调用查询语句返回的结果集；

        // the next statement after we call the PageHelper.startPage() method must be the query statement you want to call.
        // the result set passed in by our PageInfo, which must be the result set returned by our call to the query statement.

        //20
        int pageSize = 10;
        PageHelper.startPage(page, pageSize);

        // 获取当前user的tracker list
        //todo 这里不能分页的原因找到了，是因为page helper 只能强化PageHelper.startPage下面紧邻着的一个查询方法
        // 你的service里面userService.userTrackerList(user)，其实进行了两次查询，所以破坏了page helper的结构
        // 也就无法分页了
        // 解决办法 方法1.自己写mapper方法去数据库进行一次连表查询，仍然使用page helper
        // 方法2.自己实现一个page helper，去传给service层，当前页，每页条目数，service层调用自己实现的mapper查询来进行分页
        // 方法3.将service层的两个mapper查询拆开来，分别处理，对其中一个使用 page helper分页
        // 下面这个方法是没有办法分页的
        //List<Goods> originalGoodsList = userService.userTrackerListUseless(user);

        // todo The reason why you can't paginate here is that the page helper only reinforces a query method immediately below PageHelper.startPage
        // your service userService.userTrackerList(user) is actually doing two queries, so it breaks the structure of the page helper
        // And it's impossible to paginate
        // Solution 1. Write your own mapper method to do a concatenated query to the database, still using the page helper
        // Method 2. Implement a page helper, pass it to the service layer, the current page, the number of entries per page, and the service layer calls its own mapper query to do the paging
        // Method 3. split the two mapper queries from the service layer and process them separately, paging one of them with the page helper
        // There is no way to paginate in this method

        //page helper 作用于trackers ，将他进行分页
        //page helper works on trackers to paginate them
        List<Tracker> originalTrackers = userService.userTrackerList(user);

        //拿到当前页面所需的list,保存在pageInfo内部
        //Get the list needed for the current page and store it inside pageInfo
        PageInfo<Tracker> pageInfo = new PageInfo<>(originalTrackers);

        //currentpage, totalpages, currentUrl
        List<Tracker> trackers = new PageUtil<Tracker>().setPageHelperDataToModelAndReturnTheList(model, page, pageInfo, request);

        //然后我们再去处理trackers，进行下一次查询，找到list of goods
        // Then we go back to trackers and do the next query to find the list of goods
        List<Goods> goodsList = goodsService.selectGoodsListBaseOnTracker(trackers);

        model.addAttribute("goodsList", goodsList);

        //这里要保存这个页面的url 作为previouspath，放进session中
        // Here you have to save the url of this page as a previouspath and put it into the session
        String previouspath = UrlUtils.getCurrentComleteURL(request);
        System.out.println(previouspath);
        request.getSession().setAttribute("previouspath", previouspath);

        return "fore/tracker";
    }

    @RequestMapping(value = "tracker/delete", method = RequestMethod.GET)
    @WebLoginRequired
    public String trackerDelete(@RequestParam(value = "id") Integer id, Model model, HttpServletRequest request) {
        /* 获取当前user */
        Object obj = request.getSession().getAttribute("user");
        if (obj == null) {
            return "fore/notLoginFalse";
        }
        User user = (User) obj;
        /* 获取当前user */

        Tracker tracker = new Tracker();
        tracker.setWsUserId(user.getWsUserId());
        tracker.setWsGoodsId(id);

        Boolean isDelete = userService.userDeleteTrackerItem(tracker);

        String previouspath = (String) request.getSession().getAttribute("previouspath");

        return "redirect:" + previouspath;

    }


    @RequestMapping(value = "tracker/add", method = RequestMethod.GET)
    @WebLoginRequired
    public String addGoodsToTracker(@RequestParam(value = "id") Integer id, Model model, HttpServletRequest request) {
        Goods goods = goodsService.selectById(id);

        /*获取当前user*/
        Object obj = request.getSession().getAttribute("user");
        if (obj == null) {
            return "fore/notLoginFalse";
        }
        User user = (User) obj;
        /* 获取当前user */

        Boolean isAdd = userService.addGoodsToTracker(goods, user);

        model.addAttribute("goods", goods);

        //如果没添加成功，说明之前已经收藏过了
        // If not added successfully, it means it has been previously bookmarked
        if (!isAdd) {
            model.addAttribute("ErrMsg", "You can't track the item twice");
            return "fore/goodsDetail";
//            return "redirect:/fore/goods/goods-detail?id=" + id;
        }

        model.addAttribute("ErrMsg", "successful track it!");
        return "fore/goodsDetail";
//        return "redirect:/fore/goods/goods-detail?id=" + id;
    }


    @RequestMapping(value = "show/about-me", method = RequestMethod.GET)
    public String aboutMe(Model model, HttpServletRequest request) {

        /*获取当前user*/
        Object obj = request.getSession().getAttribute("user");
        if (obj == null) {
            return "fore/notLoginFalse";
        }
        User user = (User) obj;
        //标题栏
        // Title bar
        model.addAttribute("title", "Hello, " + user.getWsUserNickname());
        model.addAttribute("user",user);
        return "fore/aboutMe";
    }

    @RequestMapping(value = "edit/account", method = RequestMethod.GET)
    public String editAccount(Model model, HttpServletRequest request) {
        /*获取当前user*/
        /*Get the current user*/
        Object obj = request.getSession().getAttribute("user");
        if (obj == null) {
            return "fore/notLoginFalse";
        }
        User user = (User) obj;

        EdditUserBo userBO = new EdditUserBo();

        userBO.setWsUserNickname(user.getWsUserNickname());
        userBO.setWsUserEmail(user.getWsUserEmail());
        userBO.setWsUserAddress(user.getWsUserAddress());

        model.addAttribute("userBO", userBO);

        return "fore/editAccount";
    }

    @RequestMapping(value = "edit/account", method = RequestMethod.POST)
    public String dealWithEditAccount(@Validated EdditUserBo userBo, Errors errors, Model model, HttpServletRequest request) {

        model.addAttribute("userBO", userBo);

        /*获取当前user*/
        /*Get the current user*/
        Object obj = request.getSession().getAttribute("user");
        if (obj == null) {
            return "fore/notLoginFalse";
        }
        User user = (User) obj;

        if (errors.hasErrors()) {
//            AlertUtil.alert(response, request, "register", AlertMessageUtil.REGISTER_Error_input_ALERT_MSG);
            model.addAttribute("ErrMsg", "Please fill the form");
            return "fore/editAccount";
        }

        if (userBo != null && userBo.getWsUserNickname() != null && !"".equals(userBo.getWsUserNickname())){
            user.setWsUserNickname(userBo.getWsUserNickname());
        }

        if (userBo != null && userBo.getWsUserEmail() != null && !"".equals(userBo.getWsUserEmail())){
            user.setWsUserEmail(userBo.getWsUserEmail());
        }

        if (userBo != null && userBo.getWsUserAddress() != null && !"".equals(userBo.getWsUserAddress())){
            user.setWsUserAddress(userBo.getWsUserAddress());
        }

        Boolean isUpdate = userService.updateUser(user);
        model.addAttribute("user", user);
        model.addAttribute("title", "Hello, " + user.getWsUserNickname());

        return "fore/aboutMe";
    }



    // fore/user/user-detail
    @RequestMapping(value = "user-detail", method = RequestMethod.GET)
    public String userDetail(@RequestParam("id") Integer id, Model model, HttpServletRequest request) {
        User user = userService.selectByUserId(id);

        //标题栏 title bar
        model.addAttribute("title", "User Detail");
        model.addAttribute(user);

        Object previouspathObj = request.getSession().getAttribute("previouspath");
        String previouspath = null;
        if (null != previouspathObj){
            previouspath = (String) previouspathObj;
            model.addAttribute("previouspath", previouspath);
        }
        return "fore/userDetail";
    }

    @RequestMapping(value = "edit/nickname", method = RequestMethod.GET)
    public String editNickName(Model model) {
        return "fore/editNickName";
    }

    @RequestMapping(value = "edit/password", method = RequestMethod.GET)
    public String editPassword(Model model) {
        return "fore/editPassword";
    }

    @RequestMapping(value = "edit/address", method = RequestMethod.GET)
    public String editAddress(Model model) {
        return "fore/editAddress";
    }


    @RequestMapping(value = "edit/email", method = RequestMethod.GET)
    public String editEmail(Model model) {

        return "fore/editEmail";
    }



}

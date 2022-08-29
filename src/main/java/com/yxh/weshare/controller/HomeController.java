package com.yxh.weshare.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yxh.weshare.annotation.WebLoginRequired;
import com.yxh.weshare.bean.bo.CommentBo;
import com.yxh.weshare.bean.bo.FilterBo;
import com.yxh.weshare.bean.bo.SearchBo;
import com.yxh.weshare.bean.pojo.*;
import com.yxh.weshare.service.CategoryService;
import com.yxh.weshare.service.GoodsService;
import com.yxh.weshare.service.RecommendService;
import com.yxh.weshare.service.SearchService;
import com.yxh.weshare.utils.page.PageUtil;
import com.yxh.weshare.utils.recommend.RecommendCalculateUtil;
import com.yxh.weshare.utils.sort.SortUtil;
import com.yxh.weshare.utils.url.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Xinhao Yi
 * @date 2022/7/21 0:23
 * @description:
 */
@Controller
@RequestMapping("fore/home")
public class HomeController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    SearchService searchService;

    @Autowired
    RecommendService recommendService;

    @RequestMapping(value = "recommend", method = RequestMethod.GET)
    @WebLoginRequired
    public String recommend(Model model, HttpServletRequest request) {
        //这里要保存这个页面的url 作为previouspath，放进session中
        // Here you have to save the url of this page as a previouspath and put it into the session
        String previouspath = UrlUtils.getCurrentComleteURL(request);
        System.out.println(previouspath);
        request.getSession().setAttribute("previouspath", previouspath);




        List<Category> categories = categoryService.list();

        /* 获取当前user */
        Object obj = request.getSession().getAttribute("user");
        if (obj == null) {
            return "fore/notLoginFalse";
        }
        User user = (User) obj;

        int numOfUserGoodsScore = recommendService.countUserGoodsScoreByUser(user);

        List<Goods> goodsList = new ArrayList<>();

        Boolean isExecute = false;
        ServletContext servletContext = request.getServletContext();
        Object isExecuteObj = servletContext.getAttribute("isRecommendAlgorithmsExecute");
        if (isExecuteObj != null){
            isExecute = (Boolean)isExecuteObj;
        }

        //when the number is 5 or Integer multiple of 5, execute the algorithms
        if (numOfUserGoodsScore >= 5){

            Object isFirstTime = request.getSession().getAttribute("isAutoExecuteFirstTime");
            if (recommendService.isNewUser(user) || numOfUserGoodsScore % 5 == 0){

                request.getSession().setAttribute("isAutoExecuteFirstTime", "no");

                List<UserGoodsScore> userGoodsScoreList = recommendService.getAllUserGoodsScoreList();
                List<UserFactor> userFactorList = recommendService.getAllUserFactorList();
                List<GoodsFactor> goodsFactorList = recommendService.getAllGoodsFactorList();

                RecommendCalculateUtil.modelExecute(userGoodsScoreList, userFactorList, goodsFactorList);

                List<UserFactor> userFactorListRes = RecommendCalculateUtil.getUserFactorList();

                List<GoodsFactor> goodsFactorListRes = RecommendCalculateUtil.getGoodsFactorList();

                List<UserGoodsScorePredict> userGoodsScorePredictList = RecommendCalculateUtil.getUserGoodsScorePredictList();

                Boolean isUserFactorListAdd = recommendService.addOrUpdateUserFactorList(userFactorListRes);
                Boolean isGoodsFactorListAdd = recommendService.addOrUpdateGoodsFactorList(goodsFactorListRes);
                Boolean isUserGoodsScorePredictAdd = recommendService.addOrUpdateUserGoodsScorePredictList(userGoodsScorePredictList);
            }

            List<UserGoodsScorePredict> userGoodsScorePredictList = recommendService.getUserGoodsScorePredictListByUser(user);
            List<Integer> userRecommendList = RecommendCalculateUtil.getUserRecommendList(userGoodsScorePredictList, 40);

            //shuffle the recommend List
            Collections.shuffle(userRecommendList);

            if (userRecommendList.size() > 10){
                userRecommendList = userRecommendList.subList(0,10);
            }

            for (Integer goodsId : userRecommendList) {
                Goods goods = goodsService.selectById(goodsId);
                goodsList.add(goods);
            }

            model.addAttribute("title", "Recommend For You");
        }else {
            // if less than 5, random search
            goodsList = goodsService.randomList(10);
            model.addAttribute("title", "Random now, please click at least 5 items");
        }

        model.addAttribute("goodsList", goodsList);
        model.addAttribute("categories", categories);
        return "fore/recommend";
    }


    @RequestMapping(value = "show", method = RequestMethod.GET)
    public String show(@RequestParam(value = "page", defaultValue = "1")Integer page, Model model, HttpServletRequest request) {
        //标题栏 title bar
        model.addAttribute("title", "Home");

        String previouspath = UrlUtils.getCurrentComleteURL(request);
        System.out.println("HomePage: " + previouspath);
        request.getSession().setAttribute("previouspath", previouspath);

        //把过滤时间和价格的过滤器需要的参数传进去  Pass in the parameters needed for the filter for filter time and price
        FilterBo filterBo = (FilterBo) request.getSession().getAttribute("filterBo");
        model.addAttribute("filterBo", filterBo);


        //传入全部种类需要的参数  Pass in all the required parameters for each category
        List<Category> categories = categoryService.list();

        //20
        int pageSize = 10;

        //利用pageHelper 进行分页  Paging with pageHelper
        // refer to: https://blog.csdn.net/qq_45958469/article/details/120424642
        PageHelper.startPage(page, pageSize);


        // 一定要注意位置，List<Goods> goodsList = goodsService.list(); 必须在 PageHelper.startPage(page, pageSize);下面
        //其工作原理是拦截此方法后第一个查询，对其进行分页，并自动解析sql ，拼接出一个查询数量的sql并执行
        // 最后将两个查询（一个分页一个总数）的结果封装为一个 Page<E> 对象，
        // Page<E> 是 List<E>的子类，total信息是其中的一个字段。

        // must pay attention to the location, List<Goods> goodsList = goodsService.list(); must be in PageHelper.startPage(page, pageSize); below
        // It works by intercepting the first query after this method, paging it, and automatically parsing the sql, stitching together a query number of sql and executing it
        // Finally, the result of the two queries (one paged and one total) is encapsulated into a Page<E> object.
        // Page<E> is a subclass of List<E>, of which the total information is a field.


        List<Goods> originalGoodsList = goodsService.list();
        //拿到当前页面所需的list,保存在pageInfo内部
        //Get the list needed for the current page and store it inside pageInfo
        PageInfo<Goods> pageInfo = new PageInfo<>(originalGoodsList);


        // This sentence already puts in the current url needed for paging
        List<Goods> goodsList = new PageUtil<Goods>().setPageHelperDataToModelAndReturnTheList(model, page, pageInfo, request);

        /* original way without PageUtil */
        // 总页数  Total number of pages
        // Integer totalpages = pageInfo.getPages();

        // 总记录数  Total number of records
        // Long count = pageInfo.getTotal();

        // 获取要 加载页 的 第一条数据索引(数据库数据索引从0开始)
        // Get the first data index of the page to be loaded (database data index starts from 0)
        // Integer pageNum = (page-1) * pageSize;


        //拿到当前的url，包含?后面的param
        // Get the current url, containing the ? followed by the param
        // String currentUrl = UrlUtils.getCurrentComleteURL(request);
        /* original way without PageUtil */


        // the last step, sort the goodsList based on the filter condition
        SortUtil.sortGoodsListBasedOnFilterCondition(goodsList, request);


        model.addAttribute("goodsList", goodsList);
        model.addAttribute("categories", categories);
        return "fore/home";
    }

    //    /fore/home/show/select-category
    @RequestMapping(value = "show/select-category/{id}", method = RequestMethod.GET)
    public String showAfterSelectCategory(@PathVariable("id") Integer id, @RequestParam(value = "page", defaultValue = "1")Integer page, Model model, HttpServletRequest request) {
        List<Category> categories = categoryService.list();

        Category category = categoryService.selectByCategoryID(id);

        //标题栏
        // Title bar
        model.addAttribute("title", category.getWsCategoryName());

        String previouspath = UrlUtils.getCurrentComleteURL(request);
//        System.out.println(previouspath);
        request.getSession().setAttribute("previouspath", previouspath);

        //把过滤时间和价格的过滤器需要的参数传进去
        // Pass in the parameters needed for the filter for filter time and price
        FilterBo filterBo = (FilterBo) request.getSession().getAttribute("filterBo");
        model.addAttribute("filterBo", filterBo);

        // "#"

        //previous

        //20
        int pageSize = 10;
        PageHelper.startPage(page, pageSize);

        List<Goods> originalGoodsList = goodsService.getListOfGoodsForCategoryBasedOnFilterCondition(category, filterBo);

        //拿到当前页面所需的list,保存在pageInfo内部
        // Get the list you need for the current page and save it inside pageInfo
        PageInfo<Goods> pageInfo = new PageInfo<>(originalGoodsList);

        // 这句话 已经把分页需要的当前 url 给放进去了
        // This sentence already puts in the current url needed for paging
        List<Goods> goodsList = new PageUtil<Goods>().setPageHelperDataToModelAndReturnTheList(model, page, pageInfo, request);


        // 拿到当前的url，包含?后面的param   -> Get the current url, containing the '?' followed by the param
        // String currentUrl = UrlUtils.getCurrentComleteURL(request);
        // model.addAttribute("currentUrl", currentUrl);

        // the last step, sort the goodsList based on the filter condition
        // Methods that require paging cannot use this tool class below, but must use the modified service method : goodsService.getListOfGoodsForCategoryBasedOnFilterCondition(category, filterBo);
        // SortUtil.sortGoodsListBasedOnFilterCondition(goodsList, request);


        model.addAttribute("goodsList", goodsList);
        model.addAttribute("categories", categories);
        return "fore/home";
    }


    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String search(SearchBo searchBo, @RequestParam(value = "page", defaultValue = "1")Integer page, Model model, HttpServletRequest request) {
        // todo 后续可以用倒排索引来实现，我们这里先只用简单的搜索
        //  This can be done later with a reverse index, we will start here with just a simple search

        List<Category> categories = categoryService.list();


        String keyword = searchBo.getKeyword();
        System.out.println(keyword);

        //标题栏  Title bar
        model.addAttribute("title", "Search Result");

        String previouspath = UrlUtils.getCurrentComleteURL(request);
        System.out.println(previouspath);
        request.getSession().setAttribute("previouspath", previouspath);

        //把过滤时间和价格的过滤器需要的参数传进去
        // Pass in the parameters needed for the filter for filter time and price
        FilterBo filterBo = (FilterBo) request.getSession().getAttribute("filterBo");
        model.addAttribute("filterBo", filterBo);

        // 20
        int pageSize = 10;

        // 开启分页
        // PageHelper.startPage(page, pageSize);


        //查询结果集  Search result set
        List<Goods> originalGoodsList = searchService.getListOfGoodsFromKeyWord(keyword);

        // the last step, sort the goodsList based on the filter condition
        // 不需要分页的方法可以直接使用我们的工具类，用外部的比较器来实现排序
        // Methods that do not require paging can be sorted directly using our tool class, using an external comparator
        SortUtil.sortGoodsListBasedOnFilterCondition(originalGoodsList, request);



        model.addAttribute("goodsList", originalGoodsList);
        model.addAttribute("categories", categories);
        return "fore/search";

    }

    //edit-filter
    @RequestMapping(value = "edit-filter", method = RequestMethod.POST)
    public String replyComment(FilterBo filterBo, Model model, HttpSession session){
        String previouspath = (String) session.getAttribute("previouspath");

        //如果价格顺序，逆序都选中，什么都不做
        // If both price order and price reverse order are selected, just do nothing
        if (
                ("selected".equals(filterBo.getOrderByPrize()) && "selected".equals(filterBo.getOrderByPrizeDESC()))
                || ("selected".equals(filterBo.getOrderByTime()) && "selected".equals(filterBo.getOrderByTimeDESC())))
        {
            return "fore/filterErrorSelect";
        }

        session.setAttribute("filterBo", filterBo);
        return "redirect:" + previouspath;
    }
}

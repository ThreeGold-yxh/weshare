package com.yxh.weshare.utils.page;

import com.github.pagehelper.PageInfo;
import com.yxh.weshare.utils.url.UrlUtils;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Xinhao Yi
 * @date 2022/7/28 22:45
 * @description:
 */
public class PageUtil<T> {
    public List<T> setPageHelperDataToModelAndReturnTheList(Model model, Integer currentpage, PageInfo<T> pageInfo, HttpServletRequest request){
        //拿到总页数
//        Take the total number of pages
        Integer totalpages = pageInfo.getPages();

        String currentUrl = UrlUtils.getCurrentComleteURL(request);


        model.addAttribute("currentUrl", currentUrl);
        model.addAttribute("totalpages", totalpages);
        model.addAttribute("currentpage", currentpage);
        return pageInfo.getList();
    }
}

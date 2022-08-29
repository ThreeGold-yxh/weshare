package com.yxh.weshare.service;


import com.yxh.weshare.bean.pojo.Category;

import java.util.List;

/**
 * @author Xinhao Yi
 * @date 2022/7/22 0:35
 * @description:
 */
public interface CategoryService {

    /**
     * select all the categories
     * @return categories
     */
    List<Category> list();

    /**
     * choose the category by id
     * @param id
     * @return
     */
    Category selectByCategoryID(Integer id);
}

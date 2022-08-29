package com.yxh.weshare.service.impl;


import com.yxh.weshare.bean.pojo.Category;
import com.yxh.weshare.bean.pojo.CategoryExample;
import com.yxh.weshare.mapper.CategoryMapper;
import com.yxh.weshare.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xinhao Yi
 * @date 2022/7/21 1:04
 * @description:
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<Category> list() {
        CategoryExample categoryExample = new CategoryExample();
//        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        return categoryMapper.selectByExample(categoryExample);
    }

    @Override
    public Category selectByCategoryID(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }
}

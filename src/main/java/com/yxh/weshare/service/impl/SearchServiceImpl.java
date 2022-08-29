package com.yxh.weshare.service.impl;

import com.yxh.weshare.bean.pojo.*;
import com.yxh.weshare.mapper.CategoryMapper;
import com.yxh.weshare.mapper.GoodsMapper;
import com.yxh.weshare.mapper.OrderMapper;
import com.yxh.weshare.mapper.UserMapper;
import com.yxh.weshare.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Xinhao Yi
 * @date 2022/7/31 15:47
 * @description:
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    OrderMapper orderMapper;

    @Override
    public List<Goods> getListOfGoodsFromKeyWord(String keyword) {

        if (keyword == null || keyword.length() == 0){
            return new ArrayList<>();
        }

        Set<Goods> goodsTreeSet = new TreeSet<Goods>(new Comparator<Goods>() {
            @Override
            public int compare(Goods o1, Goods o2) {
                //让日期新的时间排在前面
                return (int)(o2.getWsGoodsPublishDate().getTime() - o1.getWsGoodsPublishDate().getTime());
            }
        });


        Set<Category> categorySet = new HashSet<>();


        // 按照空格分解keyword， 得到一系列可能的目录关键字
        //分割一个或者多个空格
        String[] splits = keyword.split("\\s+");

        for (String split : splits) {
            split = "%" + split + "%";
            GoodsExample goodsExample = new GoodsExample();

            // 先按照名字和描述去查询
            GoodsExample.Criteria goodsNameCriteria = goodsExample.createCriteria();
            GoodsExample.Criteria descriptionCriteria = goodsExample.createCriteria();

            goodsNameCriteria.andWsGoodsStatusEqualTo(1)
                    .andWsGoodsAmountGreaterThanOrEqualTo(1)
                    .andWsGoodsNameLike(split);

            descriptionCriteria.andWsGoodsStatusEqualTo(1)
                    .andWsGoodsAmountGreaterThanOrEqualTo(1)
                    .andWsGoodsDescriptionLike(split);

            goodsExample.or(goodsNameCriteria);
            goodsExample.or(descriptionCriteria);

            List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);

            // 去重
            if (goodsList != null && goodsList.size() != 0){
                goodsTreeSet.addAll(goodsList);
            }
        }

        // 再按照目录去查询 所有可能的目录
        for (String split : splits) {
            CategoryExample categoryExample = new CategoryExample();
            CategoryExample.Criteria categoryCriteria = categoryExample.createCriteria();

            categoryCriteria.andWsCategoryNameLike(split);

            List<Category> categories = categoryMapper.selectByExample(categoryExample);

            //去重
            if (categories != null && categories.size() != 0){
                categorySet.addAll(categories);
            }
        }

        //循环结束后，我们拿到了一个categorySet
        for (Category category : categorySet) {
            GoodsExample goodsExample = new GoodsExample();
            GoodsExample.Criteria goodsCriteria = goodsExample.createCriteria();

            goodsCriteria.andWsGoodsStatusEqualTo(1)
                    .andWsGoodsAmountGreaterThanOrEqualTo(1)
                    .andWsGoodsCategoryIdEqualTo(category.getWsCategoryId());

            List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);

            //去重，添加到最终结果集
            if (goodsList != null && goodsList.size() != 0){
                goodsTreeSet.addAll(goodsList);
            }
        }

        return new ArrayList<>(goodsTreeSet);
    }

    @Override
    public List<User> getListOfUsersFromKeyWord(String keyword) {
        if (keyword == null || keyword.length() == 0){
            return new ArrayList<>();
        }

        Set<User> userTreeSet = new TreeSet<User>(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                //让userId 小的在前面
                // Let the smaller userId be in front
                return o1.getWsUserId() - o2.getWsUserId();
            }
        });

        // 按照空格分解keyword， 得到一系列可能的目录关键字
        //分割一个或者多个空格
        String[] splits = keyword.split("\\s+");

        for (String split : splits) {
            split = "%" + split + "%";
            UserExample userExample = new UserExample();

            // 先按照名字，账号，邮箱，地址去查询
            UserExample.Criteria userNickNameCriteria = userExample.createCriteria();
            UserExample.Criteria userAccountCriteria = userExample.createCriteria();
            UserExample.Criteria userEmailCriteria = userExample.createCriteria();
            UserExample.Criteria userAddressCriteria = userExample.createCriteria();


            userNickNameCriteria.andWsUserNicknameLike(split);

            userAccountCriteria.andWsUserAccountLike(split);

            userEmailCriteria.andWsUserEmailLike(split);

            userAddressCriteria.andWsUserAddressLike(split);

            userExample.or(userNickNameCriteria);
            userExample.or(userAccountCriteria);
            userExample.or(userEmailCriteria);
            userExample.or(userAddressCriteria);

            List<User> userList = userMapper.selectByExample(userExample);

            // 去重
            if (userList != null && userList.size() != 0){
                userTreeSet.addAll(userList);
            }
        }

        return new ArrayList<>(userTreeSet);
    }

    @Override
    public List<Order> getListOfOrdersFromKeyWord(String keyword) {
        if (keyword == null || keyword.length() == 0){
            return new ArrayList<>();
        }

        Set<Order> orderTreeSet = new TreeSet<Order>(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                //让userId 小的在前面
                // Let the smaller userId be in front
                return o1.getWsOrderId() - o2.getWsOrderId();
            }
        });

        // 按照空格分解keyword， 得到一系列可能的目录关键字
        //分割一个或者多个空格
        String[] splits = keyword.split("\\s+");

        for (String split : splits) {
            split = "%" + split + "%";
            OrderExample orderExample = new OrderExample();

            // 先按照地址去查询
            OrderExample.Criteria orderAddressCriteria = orderExample.createCriteria();
            orderAddressCriteria.andWsOrderAddressLike(split);


            List<Order> orderList = orderMapper.selectByExample(orderExample);

            // 去重
            if (orderList != null && orderList.size() != 0){
                orderTreeSet.addAll(orderList);
            }
        }
        return new ArrayList<>(orderTreeSet);
    }
}

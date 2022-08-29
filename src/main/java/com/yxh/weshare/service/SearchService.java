package com.yxh.weshare.service;

import com.yxh.weshare.bean.pojo.Goods;
import com.yxh.weshare.bean.pojo.Order;
import com.yxh.weshare.bean.pojo.User;

import java.util.List;

/**
 * @author Xinhao Yi
 * @date 2022/7/31 15:47
 * @description:
 */
public interface SearchService {

    List<Goods> getListOfGoodsFromKeyWord(String keyword);

    List<User> getListOfUsersFromKeyWord(String keyword);

    List<Order> getListOfOrdersFromKeyWord(String keyword);
}

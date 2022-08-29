package com.yxh.weshare.service;

import com.yxh.weshare.bean.pojo.Goods;
import com.yxh.weshare.bean.pojo.History;
import com.yxh.weshare.bean.pojo.Tracker;
import com.yxh.weshare.bean.pojo.User;

import java.util.List;

/**
 * @author Xinhao Yi
 * @date 2022/7/22 1:02
 * @description:
 */
public interface UserService {

    /**
     * 检查某个用户是否已经在数据库中存在
     * @return
     */
    Boolean isExist(User user);


    /**
     * 增加一个新用户，并主动给他授权
     * @param user
     * @return
     */
    Boolean add(User user);

    Boolean login(User user);

    User selectByAccount(User user);


    /**
     * 把goods添加到该user的tracker中
     * @param goods
     * @param user
     * @return
     */
    Boolean addGoodsToTracker(Goods goods, User user);

    /**
     * 获取当前user的tracker list
     * @param user
     * @return
     */
    List<Goods> userTrackerListUseless(User user);


    List<Tracker> userTrackerList(User user);

    Boolean userDeleteTrackerItem(Tracker tracker);

    Boolean addGoodsToHistory(User user, Goods goods);

    List<History> userHistoryList(User user);


    User selectByUserId(Integer wsOrderBuyerId);

    Boolean updateUser(User user);

    List<User> listAllOrderByUserId();

}

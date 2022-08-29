package com.yxh.weshare.service.impl;

import com.yxh.weshare.bean.pojo.*;
import com.yxh.weshare.mapper.*;
import com.yxh.weshare.service.UserService;
import com.yxh.weshare.utils.authority.AuthorityDescriptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Xinhao Yi
 * @date 2022/7/21 1:05
 * @description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    AuthorityMapper authorityMapper;

    @Autowired
    TrackerMapper trackerMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    HistoryMapper historyMapper;

    @Override
    public Boolean isExist(User user) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteriaAccount = userExample.createCriteria();
        UserExample.Criteria criteriaEmail = userExample.or();

        criteriaAccount.andWsUserAccountEqualTo(user.getWsUserAccount());
        criteriaEmail.andWsUserEmailEqualTo(user.getWsUserEmail());
        List<User> users = userMapper.selectByExample(userExample);

        return users.size() != 0;
    }

    @Override
    public Boolean add(User user) {
        AuthorityExample authorityExample = new AuthorityExample();
        AuthorityExample.Criteria criteria = authorityExample.createCriteria();
        criteria.andWsAuthorityDescriptionEqualTo(AuthorityDescriptionUtil.SELLER_AND_BUYER);

        List<Authority> authorities = authorityMapper.selectByExample(authorityExample);
        Integer authority;
        if (authorities.size()!=0){
            authority = authorities.get(0).getWsUserAuthority();
        }else{
            System.out.println("can't find authority");
            authority = 3;   //这里写的不是很好
        }

        if (null == user.getAuthority()){
            user.setAuthority(authority);
        }

        userMapper.insert(user);
        return true;
    }

    @Override
    public Boolean login(User user) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();

        String account = user.getWsUserAccount();
        String password = user.getWsUserPassword();

        criteria.andWsUserAccountEqualTo(account).andWsUserPasswordEqualTo(password);

        List<User> users = userMapper.selectByExample(userExample);

        if (users.size() == 1){
            return true;
        }else if (users.size() == 0){
            return false;
        }else {
            System.err.println("数据库中有相同账号的用户");
            return false;
        }
    }

    @Override
    public User selectByAccount(User user) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();

        criteria.andWsUserAccountEqualTo(user.getWsUserAccount());

        List<User> users = userMapper.selectByExample(userExample);

        if (users.size() > 1){
            System.err.println("数据库中有相同账号");
        }

        if (users.size() == 0){
            return null;
        }
        return users.get(0);
    }

    @Override
    public Boolean addGoodsToTracker(Goods goods, User user) {
        //先检查这个user的tracker里面有没有这个goods，如果有的话，返回false
        TrackerExample trackerExample = new TrackerExample();
        TrackerExample.Criteria criteria = trackerExample.createCriteria();

        criteria.andWsUserIdEqualTo(user.getWsUserId()).andWsGoodsIdEqualTo(goods.getWsGoodsId());

        //找到该用户和商品goods对应的tracker，如果能找到, 说明已经被收藏了，就要返回false
        //否则，才加进该user的trackerList中
        List<Tracker> trackers = trackerMapper.selectByExample(trackerExample);

        if (trackers.size() > 0){
            return false;
        }

        Tracker tracker = new Tracker();
        tracker.setWsUserId(user.getWsUserId());
        tracker.setWsGoodsId(goods.getWsGoodsId());

        trackerMapper.insert(tracker);

        return true;
    }

    @Override
    public List<Goods> userTrackerListUseless(User user) {

        TrackerExample trackerExample = new TrackerExample();
        TrackerExample.Criteria criteria = trackerExample.createCriteria();

        criteria.andWsUserIdEqualTo(user.getWsUserId());

        List<Tracker> trackers = trackerMapper.selectByExample(trackerExample);

        List<Goods> res = new ArrayList<>();

        for (Tracker tracker : trackers) {
            Goods goods = goodsMapper.selectByPrimaryKey(tracker.getWsGoodsId());
            res.add(goods);
        }

        return res;

    }

    @Override
    public List<Tracker> userTrackerList(User user) {
        TrackerExample trackerExample = new TrackerExample();
        TrackerExample.Criteria criteria = trackerExample.createCriteria();

        criteria.andWsUserIdEqualTo(user.getWsUserId());
        return trackerMapper.selectByExample(trackerExample);

    }

    @Override
    public Boolean userDeleteTrackerItem(Tracker tracker) {
        TrackerExample trackerExample = new TrackerExample();
        TrackerExample.Criteria criteria = trackerExample.createCriteria();

        criteria.andWsUserIdEqualTo(tracker.getWsUserId()).andWsGoodsIdEqualTo(tracker.getWsGoodsId());

        int res = trackerMapper.deleteByExample(trackerExample);

        if (res == 0){
            return false;
        }

        if (res == 1){
            return true;
        }

        if (res> 1){
            System.out.println("You delete more than one tracker in your database, you might store some same items");
            return true;
        }

        return false;

    }

    @Override
    public Boolean addGoodsToHistory(User user, Goods goods) {
        if (user != null && goods != null && user.getWsUserId() != null && goods.getWsGoodsId() != null){
            History history = new History();
            Timestamp currentTime = new Timestamp(new Date().getTime());
            history.setWsUserId(user.getWsUserId());
            history.setWsGoodsId(goods.getWsGoodsId());
            history.setWsHistoryCreateDate(currentTime);
            historyMapper.insert(history);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<History> userHistoryList(User user) {
        HistoryExample historyExample = new HistoryExample();
        historyExample.setOrderByClause("ws_history_create_date DESC");
        HistoryExample.Criteria criteria = historyExample.createCriteria();

        criteria.andWsUserIdEqualTo(user.getWsUserId());

        return historyMapper.selectByExample(historyExample);
    }

    @Override
    public User selectByUserId(Integer wsOrderBuyerId) {
        return userMapper.selectByPrimaryKey(wsOrderBuyerId);
    }

    @Override
    public Boolean updateUser(User user) {
        int i = userMapper.updateByPrimaryKeySelective(user);
        return true;
    }

    @Override
    public List<User> listAllOrderByUserId() {
        UserExample userExample = new UserExample();
        userExample.setOrderByClause("ws_user_id");
        return userMapper.selectByExample(userExample);
    }

}

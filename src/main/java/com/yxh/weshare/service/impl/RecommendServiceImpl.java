package com.yxh.weshare.service.impl;

import com.sun.org.apache.regexp.internal.RE;
import com.yxh.weshare.bean.pojo.*;
import com.yxh.weshare.mapper.*;
import com.yxh.weshare.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author Xinhao Yi
 * @date 2022/8/8 23:48
 * @description:
 */
@Service
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    UserGoodsScoreMapper userGoodsScoreMapper;

    @Autowired
    UserFactorMapper userFactorMapper;

    @Autowired
    GoodsFactorMapper goodsFactorMapper;

    @Autowired
    UserGoodsScorePredictMapper userGoodsScorePredictMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public UserGoodsScore selectUserGoodsScoreByPrimaryKey(UserGoodsScore userGoodsScore) {
        if (userGoodsScore == null || userGoodsScore.getWsUserId() == null || userGoodsScore.getWsGoodsId() == null){
            return null;
        }
        UserGoodsScoreKey userGoodsScoreKey = new UserGoodsScoreKey();
        userGoodsScoreKey.setWsGoodsId(userGoodsScore.getWsGoodsId());
        userGoodsScoreKey.setWsUserId(userGoodsScore.getWsUserId());
        return userGoodsScoreMapper.selectByPrimaryKey(userGoodsScoreKey);
    }

    @Override
    public Boolean addUserGoodsScoreToTable(UserGoodsScore userGoodsScoreSelected) {
        if (userGoodsScoreSelected != null
                && userGoodsScoreSelected.getWsGoodsId() != null
                && userGoodsScoreSelected.getWsUserId() != null
                && userGoodsScoreSelected.getWsScore() != null){

            UserGoodsScoreKey userGoodsScoreKey = new UserGoodsScoreKey();
            userGoodsScoreKey.setWsUserId(userGoodsScoreSelected.getWsUserId());
            userGoodsScoreKey.setWsGoodsId(userGoodsScoreSelected.getWsGoodsId());
            UserGoodsScore userGoodsScoreFind = userGoodsScoreMapper.selectByPrimaryKey(userGoodsScoreKey);

            int insert = 0;

            if (null != userGoodsScoreFind){
                insert = userGoodsScoreMapper.updateByPrimaryKeySelective(userGoodsScoreSelected);
            }else {
                insert = userGoodsScoreMapper.insert(userGoodsScoreSelected);
            }
            return insert != 0;
        }

        return false;
    }

    @Override
    public List<UserGoodsScore> getAllUserGoodsScoreList() {
        UserGoodsScoreExample userGoodsScoreExample = new UserGoodsScoreExample();
        return userGoodsScoreMapper.selectByExample(userGoodsScoreExample);
    }

    @Override
    public List<UserFactor> getAllUserFactorList() {
        UserFactorExample userFactorExample = new UserFactorExample();
        return userFactorMapper.selectByExample(userFactorExample);
    }

    @Override
    public List<GoodsFactor> getAllGoodsFactorList() {
        GoodsFactorExample goodsFactorExample = new GoodsFactorExample();
        return goodsFactorMapper.selectByExample(goodsFactorExample);
    }

    @Override
    public Boolean addOrUpdateUserFactorList(List<UserFactor> userFactorList) {
        for (UserFactor userFactor : userFactorList) {
            UserFactor userFactorFind = userFactorMapper.selectByPrimaryKey(userFactor.getWsUserId());
            int insert = 0;
            if (userFactorFind != null){
                insert = userFactorMapper.updateByPrimaryKeySelective(userFactor);
            }else {
                insert = userFactorMapper.insert(userFactor);
            }

            if (0 == insert){
                return false;
            }
        }
        return true;
    }

    @Override
    public Boolean addOrUpdateGoodsFactorList(List<GoodsFactor> goodsFactorList) {
        for (GoodsFactor goodsFactor : goodsFactorList) {
            GoodsFactor goodsFactorFind = goodsFactorMapper.selectByPrimaryKey(goodsFactor.getWsGoodsId());
            int insert = 0;
            if (goodsFactorFind != null){
                insert = goodsFactorMapper.updateByPrimaryKeySelective(goodsFactor);
            }else {
                insert = goodsFactorMapper.insert(goodsFactor);
            }

            if (0 == insert){
                return false;
            }
        }
        return true;
    }

    @Override
    public Boolean addOrUpdateUserGoodsScorePredictList(List<UserGoodsScorePredict> userGoodsScorePredictList) {
        for (UserGoodsScorePredict userGoodsScorePredict : userGoodsScorePredictList) {
            UserGoodsScorePredict userGoodsScorePredictFind = userGoodsScorePredictMapper.selectByPrimaryKey(userGoodsScorePredict);
            int insert = 0;
            if (null != userGoodsScorePredictFind){
                insert = userGoodsScorePredictMapper.updateByPrimaryKeySelective(userGoodsScorePredict);
            }else {
                insert = userGoodsScorePredictMapper.insert(userGoodsScorePredict);
            }

            if (0 == insert){
                return false;
            }
        }
        return true;
    }

    @Override
    public int countUserGoodsScoreByUser(User user) {
        UserGoodsScoreExample userGoodsScoreExample = new UserGoodsScoreExample();
        UserGoodsScoreExample.Criteria criteria = userGoodsScoreExample.createCriteria();

        criteria.andWsUserIdEqualTo(user.getWsUserId());

        return (int) userGoodsScoreMapper.countByExample(userGoodsScoreExample);
    }

    @Override
    public List<UserGoodsScorePredict> getUserGoodsScorePredictListByUser(User user) {
        UserGoodsScorePredictExample userGoodsScorePredictExample = new UserGoodsScorePredictExample();
        UserGoodsScorePredictExample.Criteria criteria = userGoodsScorePredictExample.createCriteria();

        criteria.andWsUserIdEqualTo(user.getWsUserId());

        List<UserGoodsScorePredict> userGoodsScorePredictList = userGoodsScorePredictMapper.selectByExample(userGoodsScorePredictExample);

        ListIterator<UserGoodsScorePredict> iterator = userGoodsScorePredictList.listIterator();

        while (iterator.hasNext()){
            UserGoodsScorePredict next = iterator.next();
            Goods goods = goodsMapper.selectByPrimaryKey(next.getWsGoodsId());
            if (goods.getWsGoodsStatus() == 0 || goods.getWsGoodsAmount() <= 0){
                iterator.remove();
            }
        }
        return userGoodsScorePredictList;

    }

    @Override
    public boolean isNewUser(User user) {
        if (user == null){
            return false;
        }
        UserGoodsScorePredictExample userGoodsScorePredictExample = new UserGoodsScorePredictExample();
        UserGoodsScorePredictExample.Criteria criteria = userGoodsScorePredictExample.createCriteria();

        criteria.andWsUserIdEqualTo(user.getWsUserId());

        long l = userGoodsScorePredictMapper.countByExample(userGoodsScorePredictExample);
        return l == 0;
    }
}

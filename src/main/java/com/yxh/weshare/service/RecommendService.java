package com.yxh.weshare.service;

import com.yxh.weshare.bean.pojo.*;
import com.yxh.weshare.mapper.UserGoodsScoreMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xinhao Yi
 * @date 2022/8/8 23:48
 * @description:
 */

public interface RecommendService {

    public UserGoodsScore selectUserGoodsScoreByPrimaryKey(UserGoodsScore userGoodsScore);

    public Boolean addUserGoodsScoreToTable(UserGoodsScore userGoodsScoreSelected);

    public List<UserGoodsScore> getAllUserGoodsScoreList();

    public List<UserFactor> getAllUserFactorList();

    public List<GoodsFactor> getAllGoodsFactorList();

    public Boolean addOrUpdateUserFactorList(List<UserFactor> userFactorList);

    public Boolean addOrUpdateGoodsFactorList(List<GoodsFactor> goodsFactorList);

    public Boolean addOrUpdateUserGoodsScorePredictList(List<UserGoodsScorePredict> userGoodsScorePredictList);

    public int countUserGoodsScoreByUser(User user);

    public List<UserGoodsScorePredict> getUserGoodsScorePredictListByUser(User user);

    public boolean isNewUser(User user);
}

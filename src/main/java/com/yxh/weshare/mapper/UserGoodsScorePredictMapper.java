package com.yxh.weshare.mapper;

import com.yxh.weshare.bean.pojo.UserGoodsScorePredict;
import com.yxh.weshare.bean.pojo.UserGoodsScorePredictExample;
import com.yxh.weshare.bean.pojo.UserGoodsScorePredictKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserGoodsScorePredictMapper {
    long countByExample(UserGoodsScorePredictExample example);

    int deleteByExample(UserGoodsScorePredictExample example);

    int deleteByPrimaryKey(UserGoodsScorePredictKey key);

    int insert(UserGoodsScorePredict record);

    int insertSelective(UserGoodsScorePredict record);

    List<UserGoodsScorePredict> selectByExample(UserGoodsScorePredictExample example);

    UserGoodsScorePredict selectByPrimaryKey(UserGoodsScorePredictKey key);

    int updateByExampleSelective(@Param("record") UserGoodsScorePredict record, @Param("example") UserGoodsScorePredictExample example);

    int updateByExample(@Param("record") UserGoodsScorePredict record, @Param("example") UserGoodsScorePredictExample example);

    int updateByPrimaryKeySelective(UserGoodsScorePredict record);

    int updateByPrimaryKey(UserGoodsScorePredict record);
}
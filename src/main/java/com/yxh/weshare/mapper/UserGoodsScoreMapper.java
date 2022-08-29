package com.yxh.weshare.mapper;

import com.yxh.weshare.bean.pojo.UserGoodsScore;
import com.yxh.weshare.bean.pojo.UserGoodsScoreExample;
import com.yxh.weshare.bean.pojo.UserGoodsScoreKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserGoodsScoreMapper {
    long countByExample(UserGoodsScoreExample example);

    int deleteByExample(UserGoodsScoreExample example);

    int deleteByPrimaryKey(UserGoodsScoreKey key);

    int insert(UserGoodsScore record);

    int insertSelective(UserGoodsScore record);

    List<UserGoodsScore> selectByExample(UserGoodsScoreExample example);

    UserGoodsScore selectByPrimaryKey(UserGoodsScoreKey key);

    int updateByExampleSelective(@Param("record") UserGoodsScore record, @Param("example") UserGoodsScoreExample example);

    int updateByExample(@Param("record") UserGoodsScore record, @Param("example") UserGoodsScoreExample example);

    int updateByPrimaryKeySelective(UserGoodsScore record);

    int updateByPrimaryKey(UserGoodsScore record);
}
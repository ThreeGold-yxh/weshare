package com.yxh.weshare.mapper;

import com.yxh.weshare.bean.pojo.GoodsFactor;
import com.yxh.weshare.bean.pojo.GoodsFactorExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsFactorMapper {
    long countByExample(GoodsFactorExample example);

    int deleteByExample(GoodsFactorExample example);

    int deleteByPrimaryKey(Integer wsGoodsId);

    int insert(GoodsFactor record);

    int insertSelective(GoodsFactor record);

    List<GoodsFactor> selectByExample(GoodsFactorExample example);

    GoodsFactor selectByPrimaryKey(Integer wsGoodsId);

    int updateByExampleSelective(@Param("record") GoodsFactor record, @Param("example") GoodsFactorExample example);

    int updateByExample(@Param("record") GoodsFactor record, @Param("example") GoodsFactorExample example);

    int updateByPrimaryKeySelective(GoodsFactor record);

    int updateByPrimaryKey(GoodsFactor record);
}
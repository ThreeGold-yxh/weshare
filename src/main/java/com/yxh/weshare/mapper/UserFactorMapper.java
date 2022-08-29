package com.yxh.weshare.mapper;

import com.yxh.weshare.bean.pojo.UserFactor;
import com.yxh.weshare.bean.pojo.UserFactorExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserFactorMapper {
    long countByExample(UserFactorExample example);

    int deleteByExample(UserFactorExample example);

    int deleteByPrimaryKey(Integer wsUserId);

    int insert(UserFactor record);

    int insertSelective(UserFactor record);

    List<UserFactor> selectByExample(UserFactorExample example);

    UserFactor selectByPrimaryKey(Integer wsUserId);

    int updateByExampleSelective(@Param("record") UserFactor record, @Param("example") UserFactorExample example);

    int updateByExample(@Param("record") UserFactor record, @Param("example") UserFactorExample example);

    int updateByPrimaryKeySelective(UserFactor record);

    int updateByPrimaryKey(UserFactor record);
}
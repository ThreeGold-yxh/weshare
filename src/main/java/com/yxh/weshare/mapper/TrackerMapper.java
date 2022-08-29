package com.yxh.weshare.mapper;

import com.yxh.weshare.bean.pojo.Tracker;
import com.yxh.weshare.bean.pojo.TrackerExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TrackerMapper {
    long countByExample(TrackerExample example);

    int deleteByExample(TrackerExample example);

    int deleteByPrimaryKey(Integer wsTrackerId);

    int insert(Tracker record);

    int insertSelective(Tracker record);

    List<Tracker> selectByExample(TrackerExample example);

    Tracker selectByPrimaryKey(Integer wsTrackerId);

    int updateByExampleSelective(@Param("record") Tracker record, @Param("example") TrackerExample example);

    int updateByExample(@Param("record") Tracker record, @Param("example") TrackerExample example);

    int updateByPrimaryKeySelective(Tracker record);

    int updateByPrimaryKey(Tracker record);
}
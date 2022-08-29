package com.yxh.weshare.bean.pojo;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class History {
    private Integer wsHistoryId;

    private Integer wsUserId;

    private Integer wsGoodsId;

    private Timestamp wsHistoryCreateDate;

}
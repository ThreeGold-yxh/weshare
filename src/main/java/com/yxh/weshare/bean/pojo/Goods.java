package com.yxh.weshare.bean.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Goods {
    private Integer wsGoodsId;

    private Integer wsGoodsOwnerId;

    private String wsGoodsName;

    private String wsGoodsImage;

    private String wsGoodsDescription;

    private Double wsGoodsPrice;

    private Integer wsGoodsAmount;

    private Timestamp wsGoodsPublishDate;

    //1代表正常 0代表被下架了
    private Integer wsGoodsStatus;

    private Integer wsGoodsCategoryId;


}
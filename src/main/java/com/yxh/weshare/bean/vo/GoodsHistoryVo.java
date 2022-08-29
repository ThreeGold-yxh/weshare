package com.yxh.weshare.bean.vo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Xinhao Yi
 * @date 2022/7/30 17:57
 * @description:
 */
@Data
public class GoodsHistoryVo {
    private Integer wsGoodsId;

    private String wsGoodsName;

    private String wsGoodsImage;

    private Double wsGoodsPrice;

    private String wsHistoryCreateDate;

    private Integer wsGoodsAmount;

    private Integer wsGoodsStatus;
}

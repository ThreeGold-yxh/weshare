package com.yxh.weshare.bean.vo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Xinhao Yi
 * @date 2022/8/1 1:23
 * @description:
 */
@Data
public class OrderShowVo {


    private Integer wsOrderId;
    /**
     * 0 means has already paid, but wait for shipping : prepare for the shipment
     * 1 means has already been shipped, wait for buyer to confirm receive
     * 2 means the buyer has already confirm to receive, the order need to be rated
     * 3 means the buyer has rated the trade
     * 4 means the seller has rated the trade
     * 5 means both of them have rated the trade
     * 我们希望5在最后面，其他0-4按照时间排序，单论5内部也是按时间排序
     */
    private Integer wsOrderStatus;

    private Integer wsOrderGoodsId;

    private Integer wsOrderBuyerId;

    private String wsOrderBuyerName;

    private Integer wsOrderSellerId;

    private String wsOrderSellerName;

    private String wsOrderAddress;

    private Double wsOrderPrice;

    private String wsOrderCreateDate;

    private String wsGoodsImage;

    private String wsGoodsName;

}

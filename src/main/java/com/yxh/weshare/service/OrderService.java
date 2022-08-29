package com.yxh.weshare.service;

import com.yxh.weshare.bean.pojo.Goods;
import com.yxh.weshare.bean.pojo.Order;
import com.yxh.weshare.bean.pojo.User;

import java.util.List;

/**
 * @author Xinhao Yi
 * @date 2022/7/30 20:59
 * @description:
 */
public interface OrderService {

    /**
     * 生成新order，并减少对应商品的数量
     * Generate a new order and reduce the number of corresponding items
     * @param order
     * @param goods
     * @return
     */
    Integer addOrderAndMinusAmount(Order order, Goods goods);

    /**
     * 根据user得到一系列BuyerOrders
     * Get a series of BuyerOrders based on user
     * @param user
     * @return
     */
    List<Order> selectBuyerOrdersByUser(User user);

    List<Order> selectSellerOrdersByUser(User user);

    Boolean confirmDelivery(Integer id);

    Boolean confirmReceive(Integer id);

    Boolean dealWithScoreFromSeller(Integer orderId, Integer scoreLevel);

    Boolean dealWithScoreFromBuyer(Integer id, Integer scoreLevel);

    Boolean removeGoodsBasedOnGoodsId(Integer id);

    List<Order> listAll();

    Order selectOrderByOrderId(Integer id);
}

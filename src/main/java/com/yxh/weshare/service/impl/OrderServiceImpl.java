package com.yxh.weshare.service.impl;

import com.yxh.weshare.bean.pojo.*;
import com.yxh.weshare.mapper.GoodsMapper;
import com.yxh.weshare.mapper.OrderMapper;
import com.yxh.weshare.mapper.UserMapper;
import com.yxh.weshare.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Xinhao Yi
 * @date 2022/7/30 20:59
 * @description:
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public Integer addOrderAndMinusAmount(Order order, Goods goods) {

        //has been sold out
        if (goods.getWsGoodsAmount() <= 0){
            return 404;
        }

        //if has been taken down, return 405
        if (goods.getWsGoodsStatus() == 0){
            return 405;
        }

        //update the amount of the goods
        goods.setWsGoodsAmount(goods.getWsGoodsAmount() - 1);
        goodsMapper.updateByPrimaryKey(goods);

        //insert the goods
        int insert = orderMapper.insert(order);
        System.out.println(insert);
        return 0;

    }

    @Override
    public List<Order> selectBuyerOrdersByUser(User user) {
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andWsOrderBuyerIdEqualTo(user.getWsUserId());

        List<Order> orders = orderMapper.selectByExample(orderExample);

        //排序的逻辑是，结束的order一定要在未结束的order后面，除此以外，均用时间来排序
        orders.sort(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                if (o1.getWsOrderStatus() == 5 && o2.getWsOrderStatus() != 5){
                    return 1;
                }else if (o1.getWsOrderStatus() != 5 && o2.getWsOrderStatus() == 5){
                    return -1;
                }else if (o1.getWsOrderStatus() != (o2.getWsOrderStatus())){
                    //数字大的在前面
                    return o2.getWsOrderStatus() - o1.getWsOrderStatus();
                } else{
                    return (int) (o2.getWsOrderCreateDate().getTime() - o1.getWsOrderCreateDate().getTime());
                }
            }
        });
        return orders;
    }

    @Override
    public List<Order> selectSellerOrdersByUser(User user) {
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andWsOrderSellerIdEqualTo(user.getWsUserId());

        List<Order> orders = orderMapper.selectByExample(orderExample);

        //排序的逻辑是，结束的order一定要在未结束的order后面，wait for shipment 仅仅排在 finished的前面
        // The logic of sorting is that the finished order must come after the unfinished order and the wait for shipment only comes before the finished one
        orders.sort(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                if (o1.getWsOrderStatus() == 5 && o2.getWsOrderStatus() != 5){
                    return 1;
                }else if (o1.getWsOrderStatus() != 5 && o2.getWsOrderStatus() == 5){
                    return -1;
                }
                else if (o1.getWsOrderStatus() != (o2.getWsOrderStatus())){
                    //数字大的在前面
                    return o2.getWsOrderStatus() - o1.getWsOrderStatus();
                }
                else{
                    return (int) (o2.getWsOrderCreateDate().getTime() - o1.getWsOrderCreateDate().getTime());
                }
            }
        });
        return orders;
    }

    @Override
    public Boolean confirmDelivery(Integer id) {
        Order order = orderMapper.selectByPrimaryKey(id);
        order.setWsOrderStatus(1);
        int i = orderMapper.updateByPrimaryKeySelective(order);
        return i != 0;
    }

    @Override
    public Boolean confirmReceive(Integer id) {
        Order order = orderMapper.selectByPrimaryKey(id);
        order.setWsOrderStatus(2);
        int i = orderMapper.updateByPrimaryKeySelective(order);
        return i != 0;
    }

    @Override
    public Boolean dealWithScoreFromSeller(Integer orderId, Integer scoreLevel) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        Integer wsOrderBuyerId = order.getWsOrderBuyerId();
        User buyer = userMapper.selectByPrimaryKey(wsOrderBuyerId);

        //基于scoreLevel 更新user的分数
        // 评分原则
        // 如果是1 perfect 用户+2分，满分100分
        // 如果是2 good 用户 分数不变
        // 如果是3 Not Bad 用户 分数不变
        // 如果是4 Bad 用户 - 2分 ，最低60分

        if (1 == scoreLevel){
            Integer wsUserCredit = buyer.getWsUserCredit();
            Integer addScore = 100 - wsUserCredit < 2 ? 100 - wsUserCredit : 2;
            buyer.setWsUserCredit(wsUserCredit + addScore);

        }else if (4 == scoreLevel){
            Integer wsUserCredit = buyer.getWsUserCredit();
            Integer minusScore = wsUserCredit - 60 < 2 ? wsUserCredit - 60 : 2;
            buyer.setWsUserCredit(wsUserCredit - minusScore);
        }

        int isOkForCredit = userMapper.updateByPrimaryKey(buyer);

        if (0 == isOkForCredit){
            return false;
        }

        // 接下来再去更新order的状态
        // 如果buyer还没评价， 之前seller也没评价，但现在seller是已经评价了
        // 所以目前的状态是seller已评价，buyer未评价，为状态4
        if (2 == order.getWsOrderStatus()){
            order.setWsOrderStatus(4);
        }
        //如果buyer已经评价了，此时两人都已评价，为状态5
        else if (3 == order.getWsOrderStatus()){
            order.setWsOrderStatus(5);
        }

        //塞进数据库
        int isOkForOrder = orderMapper.updateByPrimaryKey(order);

        return isOkForOrder != 0;

    }

    @Override
    public Boolean dealWithScoreFromBuyer(Integer id, Integer scoreLevel) {
        Order order = orderMapper.selectByPrimaryKey(id);
        Integer wsOrderSellerId = order.getWsOrderSellerId();

        User seller = userMapper.selectByPrimaryKey(wsOrderSellerId);

        //基于scoreLevel 更新user的分数
        // 评分原则
        // 如果是1 perfect 用户+2分，满分100分
        // 如果是2 good 用户 分数不变
        // 如果是3 Not Bad 用户 分数不变
        // 如果是4 Bad 用户 - 2分 ，最低60分

        if (1 == scoreLevel){
            Integer wsUserCredit = seller.getWsUserCredit();
            Integer addScore = 100 - wsUserCredit < 2 ? 100 - wsUserCredit : 2;
            seller.setWsUserCredit(wsUserCredit + addScore);

        }else if (4 == scoreLevel){
            Integer wsUserCredit = seller.getWsUserCredit();
            Integer minusScore = wsUserCredit - 60 < 2 ? wsUserCredit - 60 : 2;
            seller.setWsUserCredit(wsUserCredit - minusScore);
        }

        int isOkForCredit = userMapper.updateByPrimaryKey(seller);

        if (0 == isOkForCredit){
            return false;
        }

        // 接下来再去更新order的状态
        //如果都还没评价
        // 所以目前的状态是buyer已评价，seller未评价，为状态3
        if (2 == order.getWsOrderStatus()){
            order.setWsOrderStatus(3);
        }
        //如果seller已经评价了，就是两人都评价了，为状态5
        else if (4 == order.getWsOrderStatus()){
            order.setWsOrderStatus(5);
        }

        //塞进数据库
        int isOkForOrder = orderMapper.updateByPrimaryKey(order);


        return isOkForOrder != 0;

    }

    @Override
    public Boolean removeGoodsBasedOnGoodsId(Integer id) {
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        goods.setWsGoodsStatus(0);
        int i = goodsMapper.updateByPrimaryKeySelective(goods);
        return i != 0;
    }

    @Override
    public List<Order> listAll() {
        OrderExample orderExample = new OrderExample();
        return orderMapper.selectByExample(orderExample);
    }

    @Override
    public Order selectOrderByOrderId(Integer id) {
        return orderMapper.selectByPrimaryKey(id);
    }
}

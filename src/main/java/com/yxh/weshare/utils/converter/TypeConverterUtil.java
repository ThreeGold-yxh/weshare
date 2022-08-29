package com.yxh.weshare.utils.converter;

import com.yxh.weshare.bean.bo.*;
import com.yxh.weshare.bean.pojo.*;
import com.yxh.weshare.bean.vo.AdminUserVo;
import com.yxh.weshare.bean.vo.OrderShowVo;
import com.yxh.weshare.utils.type.TypeConvertUtil;

import java.util.ArrayList;

/**
 * @author Xinhao Yi
 * @date 2022/7/25 8:30
 * @description:
 */
public class TypeConverterUtil {

    /**
     * 把注册接收到的RegisterUserBO userBO 转换为 User user
     * 对应UserController 中 register 方法
     * @param userBO
     * @return
     */
    public static User RegisterUserBOConvertToUser(RegisterUserBo userBO){
        User user = new User();
        user.setWsUserAccount(userBO.getWsUserAccount());
        user.setWsUserAddress(userBO.getWsUserAddress());
        user.setWsUserEmail(userBO.getWsUserEmail());
        user.setWsUserId(userBO.getWsUserId());
        user.setWsUserNickname(userBO.getWsUserNickname());
        user.setWsUserPassword(userBO.getWsUserPassword());
        //手动设置credit分数
        // Set credit score manually
        user.setWsUserCredit(100);
        return user;
    }

    //LoginUserBo
    public static User LoginUserBOConvertToUser(LoginUserBo userBO){
        User user = new User();
        user.setWsUserAccount(userBO.getWsUserAccount());
        user.setWsUserPassword(userBO.getWsUserPassword());
        return user;
    }

    public static Goods AddGoodsBOConvertToGoods(AddGoodsBo goodsBO) {
        Goods goods = new Goods();
        goods.setWsGoodsAmount(goodsBO.getWsGoodsAmount());
        goods.setWsGoodsCategoryId(goodsBO.getWsGoodsCategoryId());
        goods.setWsGoodsDescription(goodsBO.getWsGoodsDescription());
        goods.setWsGoodsName(goodsBO.getWsGoodsName());
        goods.setWsGoodsPrice(goodsBO.getWsGoodsPrice());
        return goods;
    }

    public static OrderShowVo OrderConvertToOrderShowVo(Order order, Goods goods, User buyer, User seller){
        OrderShowVo orderShowVo = new OrderShowVo();
        orderShowVo.setWsOrderId(order.getWsOrderId());
        orderShowVo.setWsOrderStatus(order.getWsOrderStatus());
        orderShowVo.setWsOrderGoodsId(order.getWsOrderGoodsId());
        orderShowVo.setWsOrderBuyerId(order.getWsOrderBuyerId());
        orderShowVo.setWsOrderBuyerName(buyer.getWsUserNickname());
        orderShowVo.setWsOrderSellerId(order.getWsOrderSellerId());
        orderShowVo.setWsOrderSellerName(seller.getWsUserNickname());
        orderShowVo.setWsOrderAddress(order.getWsOrderAddress());
        orderShowVo.setWsOrderPrice(order.getWsOrderPrice());
        orderShowVo.setWsOrderCreateDate(TypeConvertUtil.convertTimeStampToString(order.getWsOrderCreateDate()));
        orderShowVo.setWsGoodsImage(goods.getWsGoodsImage());
        orderShowVo.setWsGoodsName(goods.getWsGoodsName());
        return orderShowVo;
    }

    public static Goods editGoodsBOConvertToGoods(EditGoodsBo goodsBO) {
        Goods goods = new Goods();
        goods.setWsGoodsAmount(goodsBO.getWsGoodsAmount());
        goods.setWsGoodsCategoryId(goodsBO.getWsGoodsCategoryId());
        goods.setWsGoodsDescription(goodsBO.getWsGoodsDescription());
        goods.setWsGoodsName(goodsBO.getWsGoodsName());
        goods.setWsGoodsPrice(goodsBO.getWsGoodsPrice());
        return goods;
    }

    public static CommentTreeNodeBo commentConvertToCommentTreeNodeBo(Comment comment) {
        CommentTreeNodeBo commentTreeNodeBo = new CommentTreeNodeBo();
        commentTreeNodeBo.setWsCommentId(comment.getWsCommentId());
        commentTreeNodeBo.setWsCommentUserNickname(comment.getWsCommentUserNickname());
        commentTreeNodeBo.setWsCommentPublishDate(TypeConvertUtil.convertTimeStampToString(comment.getWsCommentPublishDate()));
        commentTreeNodeBo.setWsCommentContent(comment.getWsCommentContent());
        commentTreeNodeBo.setWsCommentParentId(comment.getWsCommentParentId());
        commentTreeNodeBo.setChildrenList(new ArrayList<>());
        return commentTreeNodeBo;
    }

    public static AdminUserVo userToAdminUserVo(User user, Authority authority) {
        AdminUserVo userVo = new AdminUserVo();
        userVo.setAuthority(user.getAuthority());
        userVo.setAuthorityName(authority.getWsAuthorityDescription());
        userVo.setWsUserAccount(user.getWsUserAccount());
        userVo.setWsUserAddress(user.getWsUserAddress());
        userVo.setWsUserCredit(user.getWsUserCredit());
        userVo.setWsUserEmail(user.getWsUserEmail());
        userVo.setWsUserId(user.getWsUserId());
        userVo.setWsUserNickname(user.getWsUserNickname());
        userVo.setWsUserPassword(user.getWsUserPassword());
        return userVo;
    }
}

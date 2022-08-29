package com.yxh.weshare.service.impl;


import com.sun.tools.internal.xjc.reader.TypeUtil;
import com.yxh.weshare.bean.bo.FilterBo;
import com.yxh.weshare.bean.pojo.*;
import com.yxh.weshare.bean.vo.GoodsHistoryVo;
import com.yxh.weshare.mapper.GoodsMapper;
import com.yxh.weshare.service.GoodsService;
import com.yxh.weshare.utils.converter.TypeConverterUtil;
import com.yxh.weshare.utils.type.TypeConvertUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.CRC32;

/**
 * @author Xinhao Yi
 * @date 2022/7/21 1:05
 * @description:
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public List<Goods> getListOfGoodsBasedOnFilterCondition(FilterBo filterBo) {
        //mysql 出现多个order语句时，先出现的order优先级更高
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andWsGoodsAmountGreaterThan(0).andWsGoodsStatusEqualTo(1);

        if (filterBo == null){
            return goodsMapper.selectByExample(goodsExample);
        }else {
            if (null == filterBo.getOrderByTime()
                    && null == filterBo.getOrderByTimeDESC()
                    && null == filterBo.getOrderByPrize()
                    && null == filterBo.getOrderByPrizeDESC()){
                return goodsMapper.selectByExample(goodsExample);
            }

            if ("selected".equals(filterBo.getOrderByPrize())){
                goodsExample.setOrderByClause("ws_goods_price");
            }

            if ("selected".equals(filterBo.getOrderByPrizeDESC())){
                goodsExample.setOrderByClause("ws_goods_price DESC");
            }

            if ("selected".equals(filterBo.getOrderByTime())){
                goodsExample.setOrderByClause("ws_goods_publish_date");
            }

            if ("selected".equals(filterBo.getOrderByTimeDESC())){
                goodsExample.setOrderByClause("ws_goods_publish_date DESC");
            }

        }
        return goodsMapper.selectByExample(goodsExample);
    }


    @Override
    public List<Goods> getListOfGoodsForCategoryBasedOnFilterCondition(Category category, FilterBo filterBo) {
        //mysql 出现多个order语句时，先出现的order优先级更高
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andWsGoodsAmountGreaterThan(0).andWsGoodsStatusEqualTo(1).andWsGoodsCategoryIdEqualTo(category.getWsCategoryId());

        if (filterBo == null){
            return goodsMapper.selectByExample(goodsExample);
        }else {
            if (null == filterBo.getOrderByTime()
                    && null == filterBo.getOrderByTimeDESC()
                    && null == filterBo.getOrderByPrize()
                    && null == filterBo.getOrderByPrizeDESC()){
                return goodsMapper.selectByExample(goodsExample);
            }

            if ("selected".equals(filterBo.getOrderByPrize())){
                goodsExample.setOrderByClause("ws_goods_price");
            }

            if ("selected".equals(filterBo.getOrderByPrizeDESC())){
                goodsExample.setOrderByClause("ws_goods_price DESC");
            }

            if ("selected".equals(filterBo.getOrderByTime())){
                goodsExample.setOrderByClause("ws_goods_publish_date");
            }

            if ("selected".equals(filterBo.getOrderByTimeDESC())){
                goodsExample.setOrderByClause("ws_goods_publish_date DESC");
            }

        }
        return goodsMapper.selectByExample(goodsExample);
    }

    @Override
    public List<Goods> list() {
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();

        criteria.andWsGoodsAmountGreaterThan(0).andWsGoodsStatusEqualTo(1);

        return goodsMapper.selectByExample(goodsExample);
    }

    @Override
    public List<Goods> list(Goods goods) {
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        //todo
        return null;
    }

    @Override
    public List<Goods> list(Category category) {
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();

        criteria.andWsGoodsCategoryIdEqualTo(category.getWsCategoryId()).andWsGoodsAmountGreaterThan(0).andWsGoodsStatusEqualTo(1);

        return goodsMapper.selectByExample(goodsExample);

    }

    @Override
    public Boolean add(Goods goods) {
        int insert = goodsMapper.insert(goods);
        return insert > 0;
    }

    @Override
    public Goods selectById(Integer id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Goods> selectGoodsListBaseOnTracker(List<Tracker> trackers) {
        List<Goods> res = new ArrayList<>();
        for (Tracker tracker : trackers) {
            Goods goods = goodsMapper.selectByPrimaryKey(tracker.getWsGoodsId());
            res.add(goods);
        }
        return res;
    }

    @Override
    public List<GoodsHistoryVo> selectGoodsListBasedOnHistories(List<History> histories) {
        List<GoodsHistoryVo> res = new ArrayList<>();
        for (History history : histories) {
            Goods goods = goodsMapper.selectByPrimaryKey(history.getWsGoodsId());
            GoodsHistoryVo goodsHistoryVo = new GoodsHistoryVo();
            goodsHistoryVo.setWsGoodsId(goods.getWsGoodsId());
            goodsHistoryVo.setWsGoodsImage(goods.getWsGoodsImage());
            goodsHistoryVo.setWsGoodsName(goods.getWsGoodsName());
            goodsHistoryVo.setWsGoodsPrice(goods.getWsGoodsPrice());
            goodsHistoryVo.setWsGoodsStatus(goods.getWsGoodsStatus());
            goodsHistoryVo.setWsGoodsAmount(goods.getWsGoodsAmount());
            goodsHistoryVo.setWsHistoryCreateDate(TypeConvertUtil.convertTimeStampToString(history.getWsHistoryCreateDate()));
            res.add(goodsHistoryVo);
        }
        return res;
    }

    @Override
    public List<Goods> selectGoodsListBasedOnOwner(User user) {

        if (user == null || user.getWsUserId() == null){
            return new ArrayList<>();
        }

        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();

        criteria.andWsGoodsOwnerIdEqualTo(user.getWsUserId()).andWsGoodsStatusEqualTo(1);

        return goodsMapper.selectByExample(goodsExample);

    }

    @Override
    public Boolean updateByGoods(Goods goods) {
        int i = goodsMapper.updateByPrimaryKeySelective(goods);
        return i != 0;
    }

    @Override
    public List<Goods> randomList(int number) {
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andWsGoodsAmountGreaterThan(0).andWsGoodsStatusEqualTo(1);

        long totalNumLong = goodsMapper.countByExample(goodsExample);
        int totalNum = (int) totalNumLong;
        int start = (int) (Math.random() * totalNum);


        // 保证能展示10条数据
        // Guarantee to display 10 pieces of data
        if (start > totalNum - number) {
            start = 0;
        }

        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);

        //这是因为我们是按同类型数据插入的数据库。所以这里再打散一下
        // This is because we are inserting the database by the same type of data. So here's another break up
        Collections.shuffle(goodsList);

        if (start + number < totalNum){
            goodsList = goodsList.subList(start, start + number);
        }else {
            goodsList = goodsList.subList(start, totalNum);
        }


        return goodsList;

    }


}

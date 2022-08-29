package com.yxh.weshare.service;



import com.yxh.weshare.bean.bo.FilterBo;
import com.yxh.weshare.bean.pojo.*;
import com.yxh.weshare.bean.vo.GoodsHistoryVo;

import java.util.List;

/**
 * @author Xinhao Yi
 * @date 2022/7/22 0:31
 * @description:
 */
public interface GoodsService {

    /**
     *
     * @param filterBo includes the condition  parameters of filter
     * @return all the goods in selected order
     */
    List<Goods> getListOfGoodsBasedOnFilterCondition(FilterBo filterBo);

    /**
     *
     * @param filterBo includes the condition  parameters of filter
     * @return all the goods in selected order
     */
    List<Goods> getListOfGoodsForCategoryBasedOnFilterCondition(Category category, FilterBo filterBo);

    /**
     * return all the goods
     * @return all the goods
     */
    List<Goods> list();

    /**
     * select a list of goods by a goods with some specific parameters
     * @param goods select by this goods
     * @return a list of goods
     */
    List<Goods> list(Goods goods);


    /**
     * select a specific category and then return a list of goods
     * @param category selected category
     * @return a list of goods
     */
    List<Goods> list(Category category);

    /**
     * add the new goods
     * @return isOK
     */
    Boolean add(Goods goods);

    /**
     * select a goods by id
     * @param id
     * @return
     */
    Goods selectById(Integer id);

    /**
     * 基于tracker中的goods id去找到对应的goods list
     * Find the corresponding goods list based on the goods id in the tracker
     * @return
     */
    List<Goods> selectGoodsListBaseOnTracker(List<Tracker> trackers);

    /**
     * 根据histories中的goods id去找到对应的goods list
     * Find the corresponding goods list based on the goods id in the histories
     * @param histories
     * @return
     */
    List<GoodsHistoryVo> selectGoodsListBasedOnHistories(List<History> histories);

    /**
     * 根据ownerId选择商品
     * Select products by ownerId
     * @param user
     * @return
     */
    List<Goods> selectGoodsListBasedOnOwner(User user);

    /**
     * 更新商品信息
     * Update product information
     * @param goods
     * @return
     */
    Boolean updateByGoods(Goods goods);

    /**
     * 随机商品序列
     * Random product sequences
     * @param number
     * @return
     */
    public List<Goods> randomList(int number);
}

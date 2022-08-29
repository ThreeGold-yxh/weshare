package com.yxh.weshare.utils.sort;

import com.yxh.weshare.bean.bo.FilterBo;
import com.yxh.weshare.bean.pojo.Goods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

/**
 * @author Xinhao Yi
 * @date 2022/8/4 16:26
 * @description:
 */
public class SortUtil {


    private static Comparator<Goods> goodsPrizeComparator;

    private static Comparator<Goods> goodsPrizeDescComparator;

    private static Comparator<Goods> goodsTimeComparator;

    private static Comparator<Goods> goodsTimeDescComparator;


    static {
        //from cheap to expensive
        goodsPrizeComparator = new Comparator<Goods>() {
            @Override
            public int compare(Goods o1, Goods o2) {
                return new BigDecimal(o1.getWsGoodsPrice()).compareTo(new BigDecimal(o2.getWsGoodsPrice()));
            }
        };

        //from expensive to cheap
        goodsPrizeDescComparator = new Comparator<Goods>() {
            @Override
            public int compare(Goods o1, Goods o2) {
                return new BigDecimal(o2.getWsGoodsPrice()).compareTo(new BigDecimal(o1.getWsGoodsPrice()));
            }
        };

        // from old to new
        goodsTimeComparator = new Comparator<Goods>() {
            @Override
            public int compare(Goods o1, Goods o2) {
                return (int) (o1.getWsGoodsPublishDate().getTime() - o2.getWsGoodsPublishDate().getTime());
            }
        };

        //from new to old
        goodsTimeDescComparator = new Comparator<Goods>() {
            @Override
            public int compare(Goods o1, Goods o2) {
                return (int) (o2.getWsGoodsPublishDate().getTime() - o1.getWsGoodsPublishDate().getTime());
            }
        };
    }


    public static void sortGoodsListBasedOnFilterCondition(List<Goods> goodsList, HttpServletRequest request){
        Object filterBoObj = request.getSession().getAttribute("filterBo");
        //还没有点击过filter
        //if haven't clicked on filter
        if (null == filterBoObj){
            return;
        }

        //get filterBo
        FilterBo filterBo = (FilterBo)filterBoObj;

        if (null == filterBo.getOrderByTime()
                && null == filterBo.getOrderByTimeDESC()
                && null == filterBo.getOrderByPrize()
                && null == filterBo.getOrderByPrizeDESC()){
            return;
        }

        //如果同时选中了价格过滤器和时间过滤器，我们以价格为决定因素
        // If both a price filter and a time filter are selected, we use price as the deciding factor

        if ("selected".equals(filterBo.getOrderByTime())){
            goodsList.sort(goodsTimeComparator);
        }else if ("selected".equals(filterBo.getOrderByTimeDESC())){
            goodsList.sort(goodsTimeDescComparator);
        }


        if ("selected".equals(filterBo.getOrderByPrize())){
            goodsList.sort(goodsPrizeComparator);
        }else if ("selected".equals(filterBo.getOrderByPrizeDESC())){
            goodsList.sort(goodsPrizeDescComparator);
        }
    }
}

package com.yxh.weshare.bean.bo;

import lombok.Data;

/**
 * @author Xinhao Yi
 * @date 2022/8/4 15:37
 * @description:
 */
@Data
public class FilterBo {
    private String orderByPrize;
    private String orderByPrizeDESC;
    private String orderByTime;
    private String orderByTimeDESC;
}

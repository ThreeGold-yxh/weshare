package com.yxh.weshare.utils.type;

import java.math.BigDecimal;

/**
 * @author Xinhao Yi
 * @date 2022/7/15 2:57
 * @description:
 */

/**
 * retain the N decimal places
 */
public class NumberUtil {
    public static Double retain_N_Decimals(Double data, Integer N){
        if (N > 10) N = 10;
        return new BigDecimal(data).setScale(N, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}

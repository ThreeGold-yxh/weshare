package com.yxh.weshare.bean.bo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

/**
 * @author Xinhao Yi
 * @date 2022/8/1 23:30
 * @description:
 */
@Data
public class EditGoodsBo {
    @NotNull(message = "error input, please try again")
    @NotBlank(message = "error input, please try again")
    private String wsGoodsName;

    private MultipartFile wsGoodsImage;

    @NotNull(message = "error input, please try again")
    @NotBlank(message = "error input, please try again")
    private String wsGoodsDescription;

    @NotNull(message = "error input, please try again")
    @DecimalMin(value = "0.01",message = "The prize must be greater than or equal to 0.01")
    @DecimalMax(value = "10000" ,message = "The prize must be less than or equal to 10,000")
    private Double wsGoodsPrice;

    @NotNull(message = "error input, please try again")
    @Max(value = 10000, message = "The amount should be less than or equal to 10000")
    @Min(value = 1, message = "The amount should be greater than or equal to 1")
    private Integer wsGoodsAmount;

    @NotNull(message = "error input, please try again")
    @Max(value = 10000, message = "The categoryID should be less than or equal to 10000")
    @Min(value = 0, message = "The categoryID should be greater than or equal to 0")
    private Integer wsGoodsCategoryId;



}

package com.yxh.weshare.bean.bo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Xinhao Yi
 * @date 2022/7/26 0:41
 * @description:
 */
@Data
public class LoginUserBo {
    @NotNull
    @NotBlank
    private String wsUserAccount;

    @NotNull
    @NotBlank
    private String wsUserPassword;
}

package com.yxh.weshare.bean.bo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Xinhao Yi
 * @date 2022/7/25 8:02
 * @description:
 */
@Data
public class RegisterUserBo {

    private Integer wsUserId;

    @NotNull
    @NotBlank
    private String wsUserNickname;

    @NotNull
    @NotBlank
    private String wsUserAccount;

    @NotNull
    @NotBlank
    private String wsUserPassword;

    @NotNull
    @NotBlank
    private String wsUserRepeatPassword;

    @NotNull
    @NotBlank
    @Email
    private String wsUserEmail;

    @NotNull
    @NotBlank
    private String wsUserAddress;

}

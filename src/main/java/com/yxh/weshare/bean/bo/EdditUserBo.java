package com.yxh.weshare.bean.bo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Xinhao Yi
 * @date 2022/8/3 3:11
 * @description:
 */
@Data
public class EdditUserBo {

    @NotNull
    @NotBlank
    private String wsUserNickname;
    
    @NotNull
    @NotBlank
    @Email
    private String wsUserEmail;

    @NotNull
    @NotBlank
    private String wsUserAddress;
}

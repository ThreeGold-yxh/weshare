package com.yxh.weshare.bean.pojo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class User {
    private Integer wsUserId;

    private String wsUserNickname;

    private String wsUserAccount;

    private String wsUserPassword;

    private String wsUserEmail;

    private String wsUserAddress;

    private Integer wsUserCredit;

    private Integer authority;

}
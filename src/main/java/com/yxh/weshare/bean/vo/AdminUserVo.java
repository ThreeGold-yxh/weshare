package com.yxh.weshare.bean.vo;

import lombok.Data;

/**
 * @author Xinhao Yi
 * @date 2022/8/3 19:17
 * @description:
 */
@Data
public class AdminUserVo {
    private Integer wsUserId;

    private String wsUserNickname;

    private String wsUserAccount;

    private String wsUserPassword;

    private String wsUserEmail;

    private String wsUserAddress;

    private Integer wsUserCredit;

    private Integer authority;

    private String authorityName;
}

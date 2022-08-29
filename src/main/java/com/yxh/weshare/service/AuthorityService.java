package com.yxh.weshare.service;

import com.yxh.weshare.bean.pojo.Authority;

import java.util.List;

/**
 * @author Xinhao Yi
 * @date 2022/8/3 21:12
 * @description:
 */
public interface AuthorityService {

    // 根据authorityId拿到authority
    Authority getAuthorityByAuthorityId(Integer authority);

    //列出所有的authority
    // list all authorities
    List<Authority> listAll();

}

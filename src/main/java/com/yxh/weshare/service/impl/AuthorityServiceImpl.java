package com.yxh.weshare.service.impl;

import com.yxh.weshare.bean.pojo.Authority;
import com.yxh.weshare.bean.pojo.AuthorityExample;
import com.yxh.weshare.mapper.AuthorityMapper;
import com.yxh.weshare.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xinhao Yi
 * @date 2022/8/3 21:13
 * @description:
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    AuthorityMapper authorityMapper;


    @Override
    public Authority getAuthorityByAuthorityId(Integer authorityId) {
        return authorityMapper.selectByPrimaryKey(authorityId);
    }

    @Override
    public List<Authority> listAll() {
        AuthorityExample authorityExample = new AuthorityExample();
        return authorityMapper.selectByExample(authorityExample);
    }
}

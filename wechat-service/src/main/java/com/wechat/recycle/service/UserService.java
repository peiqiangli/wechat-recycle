package com.wechat.recycle.service;

import com.wechat.recycle.entity.User;

public interface UserService {

    /**
     * 查询一条用户信息
     * @param id
     * @return
     */
    User selectOne(Integer id);

}
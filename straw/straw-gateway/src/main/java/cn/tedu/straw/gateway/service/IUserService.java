package cn.tedu.straw.gateway.service;

import cn.tedu.straw.commons.model.User;

public interface IUserService {
    /**
     * 获取用户的详细信息
     */
    User getUserInfo(String username);
}

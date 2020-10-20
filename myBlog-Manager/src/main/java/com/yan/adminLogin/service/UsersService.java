package com.yan.adminLogin.service;

import com.yan.adminLogin.entity.Users;

/**
 * @author Sam
 * @date 08/07/2020 - 1:47 pm
 */
public interface UsersService {
    /**
     * 判断账号是否存在
     * @param username
     * @return
     */
    public boolean queryUsersNameIsExist(String username);

    /**
     * 登陆账号
     * @param username
     * @param password
     * @return
     */
    public Users loginAccount(String username, String password);

    public void addAccount(Users users);
}

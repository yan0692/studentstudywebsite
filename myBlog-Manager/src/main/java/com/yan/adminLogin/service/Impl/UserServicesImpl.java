package com.yan.adminLogin.service.Impl;

import com.yan.adminLogin.entity.Users;
import com.yan.adminLogin.mapper.UsersMapper;
import com.yan.adminLogin.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Sam
 * @date 08/07/2020 - 1:47 pm
 */
@Service
public class UserServicesImpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public boolean queryUsersNameIsExist(String username) {
        Users users = usersMapper.selUserByUserName(username);
        return users == null ? true : false;
    }

    @Override
    public Users loginAccount(String username, String password) {
        Users users = usersMapper.LoginUserUsers(username);
        return users;
    }

    @Override
    public void addAccount(Users users) {
        usersMapper.addUsers(users);
    }
}

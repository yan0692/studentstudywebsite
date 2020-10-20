package com.yan.adminLogin.mapper;

import com.yan.adminLogin.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Sam
 * @date 08/07/2020 - 1:30 pm
 */
@Mapper
@Repository
public interface UsersMapper {
    Users selUserByUserName(String username);
    Users LoginUserUsers(String username);
    void addUsers(Users users);
}

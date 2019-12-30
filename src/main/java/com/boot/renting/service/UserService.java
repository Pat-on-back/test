package com.boot.renting.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.renting.dto.SystemUserLoginDto;
import com.boot.renting.dto.UserLoginDto;
import com.boot.renting.dto.UserRegisterDto;
import com.boot.renting.entity.User;
import com.boot.renting.query.UserListQuery;
import com.github.pagehelper.PageInfo;


public interface UserService extends IService<User> {

    PageInfo<User> userList(UserListQuery query);

    User queryByUserCode(String userCode);

    User queryByLoginName(String loginName);

    User queryByPhone(String phone, Integer type);

    String loginSendCode(String phone, Integer type);
    String registerSendCode(String phone, Integer type);

    String login(UserLoginDto loginDto);

    String register(UserRegisterDto dto);

    String systemUserLogin(SystemUserLoginDto loginDto);


}

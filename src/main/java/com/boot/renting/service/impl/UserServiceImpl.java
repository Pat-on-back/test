package com.boot.renting.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.renting.dto.SystemUserLoginDto;
import com.boot.renting.dto.UserLoginDto;
import com.boot.renting.dto.UserRegisterDto;
import com.boot.renting.entity.User;
import com.boot.renting.mapper.UserMapper;
import com.boot.renting.query.UserListQuery;
import com.boot.renting.service.UserService;
import com.boot.renting.utils.CookieUtil;
import com.boot.renting.utils.HttpContextUtils;
import com.boot.renting.utils.create.NoUtil;
import com.boot.renting.utils.exception.BaseException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;

    private static Map<String, String> redis = new HashMap<>(10);

    @Override
    public PageInfo<User> userList(UserListQuery query) {
        PageHelper.startPage(query.getPageNo(), query.getPageSize());
        List<User> users = userMapper.selectList(null);
        return new PageInfo<>(users);
    }

    @Override
    public User queryByUserCode(String userCode) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_code", userCode);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public User queryByLoginName(String loginName) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("login_name", loginName);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public User queryByPhone(String phone, Integer type) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", phone);
        wrapper.eq("type", type);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public String loginSendCode(String phone, Integer type) {
        User user = queryByPhone(phone, type);
        if (user == null) {
            throw new BaseException("账号不存在");
        }

        String code = NoUtil.getRandom(4);
        redis.put(phone, code);
        return code;
    }

    @Override
    public String registerSendCode(String phone, Integer type) {
        User user = queryByPhone(phone, type);
        if (user != null) {
            throw new BaseException("账号已存在");
        }
        String code = NoUtil.getRandom(4);
        redis.put(phone, code);
        return code;
    }

    @Override
    public String login(UserLoginDto dto) {
        codeYz(dto.getPhone(), dto.getCode());
        if (dto.getType() == 1) {
            CookieUtil.set(HttpContextUtils.getHttpServletResponse(), "userPhone", dto.getPhone(), true);
            return "/user/userIndex";
        } else if (dto.getType() == 2) {
            CookieUtil.set(HttpContextUtils.getHttpServletResponse(), "landlordPhone", dto.getPhone(), true);
            return "/landlord/landlordIndex";
        }
        return "/login";
    }

    @Override
    public String register(UserRegisterDto dto) {
        codeYz(dto.getPhone(), dto.getCode());
        User dbUser = queryByPhone(dto.getPhone(), dto.getType());
        if (dbUser != null) {
            throw new BaseException("账号已存在");
        }
        User user = new User();
        user.setName(dto.getName());
        user.setUserCode(NoUtil.getUserCode());
        user.setPhone(dto.getPhone());
        user.setType(dto.getType());
        user.setImg(NoUtil.getUserImg());
        userMapper.insert(user);
        return "/login";
    }

    @Override
    public String systemUserLogin(SystemUserLoginDto loginDto) {
        User user = queryByLoginName(loginDto.getLoginName());
        if (user == null) {
            throw new BaseException("账号不存在");
        }
        if (!Objects.equals(user.getLoginPassword(), loginDto.getLoginPassword())) {
            throw new BaseException("密码错误");
        }
        CookieUtil.set(HttpContextUtils.getHttpServletResponse(), "systemUserName", user.getLoginName(), true);
        return "/admin/index";
    }

    private void codeYz(String phone, String code) {
        String redisCode = redis.get(phone);
        if (redisCode == null || !Objects.equals(redisCode, code)) {
            throw new BaseException("验证码过期或验证码错误");
        }
    }

}

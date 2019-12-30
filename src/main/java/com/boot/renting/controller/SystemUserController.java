package com.boot.renting.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boot.renting.dto.SystemUserLoginDto;
import com.boot.renting.dto.UserRegisterDto;
import com.boot.renting.entity.User;
import com.boot.renting.query.UserListQuery;
import com.boot.renting.service.UserService;
import com.boot.renting.utils.CookieUtil;
import com.boot.renting.utils.HttpContextUtils;
import com.boot.renting.utils.ResponseMessage;
import com.boot.renting.utils.create.NoUtil;
import com.boot.renting.utils.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "系统用户")
@RestController
@RequestMapping("systemUser")
public class SystemUserController {

    @Resource
    private UserService userService;


    @ApiOperation("获取用户信息")
    @GetMapping("getInfo")
    public ResponseMessage<User> getInfo() {
        String systemUserName = CookieUtil.getValue(HttpContextUtils.getHttpServletRequest(), "systemUserName");
        return new ResponseMessage<>(userService.queryByLoginName(systemUserName));
    }

    @ApiOperation("分页列表")
    @PostMapping("systemUserPageList")
    public ResponseMessage<IPage<User>> systemUserPageList(@RequestBody UserListQuery query) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("type", 0);
        Page<User> page = new Page<>(query.getPageNo(), query.getPageSize());
        page.setDesc("id");
        return new ResponseMessage<>(userService.page(page, wrapper));
    }

    @ApiOperation("保存")
    @PostMapping("save")
    public ResponseMessage<Boolean> save(@RequestBody User user) {
        yzSystemUser(user.getLoginName());
        user.setUserCode(NoUtil.getUserCode());
        user.setType(0);
        return new ResponseMessage<>(userService.save(user));
    }

    @ApiOperation("通过id更新")
    @PostMapping("updateById")
    public ResponseMessage<Boolean> updateById(@RequestBody User user) {
        return new ResponseMessage<>(userService.updateById(user));
    }

    private void yzSystemUser(String loginName) {
        User user1 = userService.queryByLoginName(loginName);
        if (user1 != null) {
            throw new BaseException("账号已存在");
        }
    }

    @ApiOperation("通过id删除")
    @GetMapping("removeById/{id}")
    public ResponseMessage<Boolean> removeById(@PathVariable("id") Integer id) {
        return new ResponseMessage<>(userService.removeById(id));
    }

    @ApiOperation("登录")
    @PostMapping("login")
    public ResponseMessage<String> systemUserLogin(@RequestBody SystemUserLoginDto loginDto) {
        return new ResponseMessage<>(userService.systemUserLogin(loginDto));
    }

    @ApiOperation("注册")
    @PostMapping("register")
    public ResponseMessage<String> register(@RequestBody UserRegisterDto dto) {
        return new ResponseMessage<>(userService.register(dto));
    }
}

package com.boot.renting.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boot.renting.dto.UserLoginDto;
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
import java.util.List;

@Api(tags = "用户")
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation("列表")
    @PostMapping("list")
    public ResponseMessage<List> list() {
        return new ResponseMessage<>(userService.list());
    }

    @ApiOperation("分页列表")
    @PostMapping("userPageList")
    public ResponseMessage<IPage<User>> userPageList(@RequestBody UserListQuery query) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("type", query.getType());
        Page<User> page = new Page<>(query.getPageNo(), query.getPageSize());
        page.setDesc("id");
        return new ResponseMessage<>(userService.page(page, wrapper));
    }

    @ApiOperation("保存")
    @PostMapping("save")
    public ResponseMessage<Boolean> save(@RequestBody User user) {
        User user1 = userService.queryByPhone(user.getPhone(), user.getType());
        if (user1 != null) {
            throw new BaseException("账号已存在");
        }
        user.setUserCode(NoUtil.getUserCode());
        user.setImg(NoUtil.getUserImg());
        return new ResponseMessage<>(userService.save(user));
    }

    @ApiOperation("通过id更新")
    @PostMapping("updateById")
    public ResponseMessage<Boolean> updateById(@RequestBody User user) {
        return new ResponseMessage<>(userService.updateById(user));
    }


    @ApiOperation("通过id删除")
    @GetMapping("removeById/{id}")
    public ResponseMessage<Boolean> removeById(@PathVariable("id") Integer id) {
        return new ResponseMessage<>(userService.removeById(id));
    }

    @ApiOperation("登录发送验证码")
    @GetMapping("loginSendCode")
    public ResponseMessage<String> loginSendCode(@RequestParam("phone") String phone, @RequestParam("type") Integer type) {
        return new ResponseMessage<>(userService.loginSendCode(phone, type));
    }

    @ApiOperation("注册发送验证码")
    @GetMapping("registerSendCode")
    public ResponseMessage<String> registerSendCode(@RequestParam("phone") String phone, @RequestParam("type") Integer type) {
        return new ResponseMessage<>(userService.registerSendCode(phone, type));
    }

    @ApiOperation("登录")
    @PostMapping("login")
    public ResponseMessage<String> login(@RequestBody UserLoginDto loginDto) {
        return new ResponseMessage<>(userService.login(loginDto));
    }

    @ApiOperation("注册")
    @PostMapping("register")
    public ResponseMessage<String> register(@RequestBody UserRegisterDto dto) {
        return new ResponseMessage<>(userService.register(dto));
    }

    @ApiOperation("查询所有User")
    @GetMapping("queryAllUser/{type}")
    public ResponseMessage<List<User>> queryAllUser(@PathVariable("type") Integer type) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("type", type);
        return new ResponseMessage<>(userService.list(wrapper));
    }

    @ApiOperation("获取用户信息")
    @GetMapping("getInfo/{type}")
    public ResponseMessage<User> getInfo(@PathVariable("type") Integer type) {
        String key = "";
        if (type == 1) {
            key = "userPhone";
        } else if (type == 2) {
            key = "landlordPhone";
        }
        String phone = CookieUtil.getValue(HttpContextUtils.getHttpServletRequest(), key);
        return new ResponseMessage<>(userService.queryByPhone(phone, type));
    }
}

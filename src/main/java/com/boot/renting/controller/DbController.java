package com.boot.renting.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.renting.entity.House;
import com.boot.renting.entity.Order;
import com.boot.renting.entity.User;
import com.boot.renting.service.HouseService;
import com.boot.renting.service.OrderService;
import com.boot.renting.service.UserService;
import com.boot.renting.utils.ResponseMessage;
import com.boot.renting.utils.create.NoUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


@Api(tags = "批量添加数据")
@RestController
@RequestMapping("db")
public class DbController {

    @Resource
    private UserService userService;
    @Resource
    private HouseService houseService;
    @Resource
    private OrderService orderService;

    @ApiOperation("添加user数据")
    @GetMapping("addUser")
    public ResponseMessage addUser(@ApiParam("添加多少") @RequestParam Integer num, @ApiParam("0:管理员 1:租客 2:房东") @RequestParam Integer type) {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            User user = new User();
            user.setUserCode(NoUtil.getUserCode());
            if (type == 0) {
                user.setName("管理员" + NoUtil.getRandom(7));
                user.setLoginName("admin" + NoUtil.getRandom(5));
                user.setLoginPassword(NoUtil.getRandom(8));
            } else if (type == 1) {
                user.setName("租客" + NoUtil.getRandom(7));
                user.setPhone(NoUtil.getRandom(11));
            } else if (type == 2) {
                user.setName("房东" + NoUtil.getRandom(7));
                user.setPhone(NoUtil.getRandom(11));
            }
            user.setSex(new Random().nextInt(2));
            user.setType(type);
            user.setImg(NoUtil.getUserImg());
            list.add(user);
        }
        return new ResponseMessage<>(userService.saveBatch(list));
    }

    @ApiOperation("添加house数据")
    @GetMapping("addHouse")
    public ResponseMessage addHouse(@ApiParam("添加多少") @RequestParam Integer num) {
        List<User> users = getUsers(2);
        List<House> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            House house = new House();
            house.setUserCode(users.get(new Random().nextInt(users.size())).getUserCode());
            house.setSubject("房源" + NoUtil.getRandom(6));
            house.setHouseCode(NoUtil.getHouseCode());
            house.setHouseAddress("成都市/二环路/" + NoUtil.getRandom(6) + "号");
            house.setPrice(new Random().nextInt(100000000));
            house.setRemark("环境好" + NoUtil.getRandom(6));
            house.setHouseImg(NoUtil.getHouseImg());
            list.add(house);
        }
        return new ResponseMessage<>(houseService.saveBatch(list));
    }

    @ApiOperation("添加order数据")
    @GetMapping("addOrder")
    public ResponseMessage addOrder(@ApiParam("添加多少") @RequestParam Integer num) {
        List<User> users = getUsers(1);
        List<House> houses = houseService.list();
        List<Order> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Order order = new Order();
            order.setOrderCode(NoUtil.getOrderCode());
            order.setUserCode(users.get(new Random().nextInt(10)).getUserCode());
            House house = houses.get(new Random().nextInt(10));
            order.setHouseCode(house.getHouseCode());
            order.setPrice(house.getPrice());
            list.add(order);
        }
        return new ResponseMessage<>(orderService.saveBatch(list));
    }

    private List<User> getUsers(Integer type) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("type", type);
        return userService.list(wrapper);
    }

}

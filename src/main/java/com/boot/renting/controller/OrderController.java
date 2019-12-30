package com.boot.renting.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boot.renting.entity.Order;
import com.boot.renting.query.UserListQuery;
import com.boot.renting.service.OrderService;
import com.boot.renting.utils.ResponseMessage;
import com.boot.renting.utils.create.NoUtil;
import com.boot.renting.vo.OrderInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@Api(tags = "交易")
@RestController
@RequestMapping("order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @ApiOperation("分页列表")
    @PostMapping("orderPageList")
    public ResponseMessage<IPage<Order>> orderPageList(@RequestBody UserListQuery query) {
        Page<Order> page = new Page<>(query.getPageNo(), query.getPageSize());
        page.setDesc("id");
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        if (query.getUserCode() != null) {
            wrapper.eq("user_code", query.getUserCode());
        }
        return new ResponseMessage<>(orderService.page(page, wrapper));
    }

    @ApiOperation("保存")
    @PostMapping("save")
    public ResponseMessage<Boolean> save(@RequestBody Order order) {
        order.setOrderCode(NoUtil.getOrderCode());
        return new ResponseMessage<>(orderService.save(order));
    }

    @ApiOperation("通过id更新")
    @PostMapping("updateById")
    public ResponseMessage<Boolean> updateById(@RequestBody Order order) {
        return new ResponseMessage<>(orderService.updateById(order));
    }

    @ApiOperation("通过id删除")
    @GetMapping("removeById/{id}")
    public ResponseMessage<Boolean> removeById(@PathVariable("id") Integer id) {
        return new ResponseMessage<>(orderService.removeById(id));
    }

    @ApiOperation("获取订单详情")
    @GetMapping("getInfoById/{id}")
    public ResponseMessage<OrderInfoVo> getInfoById(@PathVariable("id") Integer id) {
        return new ResponseMessage<>(orderService.getInfoById(id));
    }


}

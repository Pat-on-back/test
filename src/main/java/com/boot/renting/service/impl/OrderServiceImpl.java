package com.boot.renting.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.renting.entity.Order;
import com.boot.renting.mapper.OrderMapper;
import com.boot.renting.service.OrderService;
import com.boot.renting.vo.OrderInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Override
    public OrderInfoVo getInfoById(Integer id) {
        return orderMapper.getInfoById(id);
    }

}

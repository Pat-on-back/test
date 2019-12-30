package com.boot.renting.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.renting.entity.Order;
import com.boot.renting.vo.OrderInfoVo;


public interface OrderService extends IService<Order> {


    OrderInfoVo getInfoById(Integer id);

}

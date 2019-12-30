package com.boot.renting.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.renting.entity.Order;
import com.boot.renting.vo.OrderInfoVo;


public interface OrderMapper extends BaseMapper<Order> {

    OrderInfoVo getInfoById(Integer id);

}

package com.boot.renting.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.renting.entity.House;
import com.boot.renting.vo.HouseInfoVo;


public interface HouseService extends IService<House> {

    HouseInfoVo getInfoById(Integer id);

}

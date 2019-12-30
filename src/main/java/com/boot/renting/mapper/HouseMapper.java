package com.boot.renting.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.renting.entity.House;
import com.boot.renting.vo.HouseInfoVo;


public interface HouseMapper extends BaseMapper<House> {

    HouseInfoVo getInfoById(Integer id);
}

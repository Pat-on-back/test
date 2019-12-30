package com.boot.renting.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.renting.entity.House;
import com.boot.renting.mapper.HouseMapper;
import com.boot.renting.service.HouseService;
import com.boot.renting.vo.HouseInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HouseServiceImpl extends ServiceImpl<HouseMapper, House> implements HouseService {

    @Resource
    private HouseMapper houseMapper;

    @Override
    public HouseInfoVo getInfoById(Integer id) {
        return houseMapper.getInfoById(id);
    }
}

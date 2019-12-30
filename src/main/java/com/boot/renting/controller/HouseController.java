package com.boot.renting.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boot.renting.entity.House;
import com.boot.renting.query.UserListQuery;
import com.boot.renting.service.HouseService;
import com.boot.renting.utils.ResponseMessage;
import com.boot.renting.utils.create.NoUtil;
import com.boot.renting.vo.HouseInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "房子")
@RestController
@RequestMapping("house")
public class HouseController {
    @Resource
    private HouseService houseService;

    @ApiOperation("分页列表")
    @PostMapping("housePageList")
    public ResponseMessage<IPage<House>> housePageList(@RequestBody UserListQuery query) {
        Page<House> page = new Page<>(query.getPageNo(), query.getPageSize());
        page.setDesc("id");
        return new ResponseMessage<>(houseService.page(page));
    }

    @ApiOperation("列表")
    @PostMapping("houseList")
    public ResponseMessage<List<House>> houseList(@RequestBody UserListQuery query) {
        QueryWrapper<House> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");

        if (StringUtils.isNoneBlank(query.getKey())) {
            wrapper.like("subject", query.getKey());
        }
        if (StringUtils.isNoneBlank(query.getUserCode())) {
            wrapper.eq("user_code", query.getUserCode());
        }
        return new ResponseMessage<>(houseService.list(wrapper));
    }

    @ApiOperation("保存")
    @PostMapping("save")
    public ResponseMessage<Boolean> save(@RequestBody House house) {
        house.setHouseCode(NoUtil.getHouseCode());
        if (StringUtils.isBlank(house.getHouseImg())) {
            house.setHouseImg(NoUtil.getHouseImg());
        }
        return new ResponseMessage<>(houseService.save(house));
    }

    @ApiOperation("通过id更新")
    @PostMapping("updateById")
    public ResponseMessage<Boolean> updateById(@RequestBody House house) {
        return new ResponseMessage<>(houseService.updateById(house));
    }

    @ApiOperation("通过id删除")
    @GetMapping("removeById/{id}")
    public ResponseMessage<Boolean> removeById(@PathVariable("id") Integer id) {
        return new ResponseMessage<>(houseService.removeById(id));
    }

    @ApiOperation("通过HouseCode删除")
    @GetMapping("deleteByHouseCode/{houseCode}")
    public ResponseMessage<Boolean> deleteByHouseCode(@PathVariable("houseCode") String houseCode) {
        UpdateWrapper<House> wrapper = new UpdateWrapper<>();
        wrapper.eq("house_code", houseCode);
        return new ResponseMessage<>(houseService.remove(wrapper));
    }

    @ApiOperation("通过id获取房子详情")
    @GetMapping("getInfoById/{id}")
    public ResponseMessage<HouseInfoVo> getInfoById(@PathVariable("id") Integer id) {
        return new ResponseMessage<>(houseService.getInfoById(id));
    }


}

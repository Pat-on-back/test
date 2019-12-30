package com.boot.renting.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.renting.entity.Trace;
import com.boot.renting.entity.User;
import com.boot.renting.mapper.TraceMapper;
import com.boot.renting.query.UserListQuery;
import com.boot.renting.service.TraceService;
import com.boot.renting.vo.TraceInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TraceServiceImpl extends ServiceImpl<TraceMapper, Trace> implements TraceService {
    @Resource
    private TraceMapper traceMapper;

    @Override
    public IPage<TraceInfoVo> tracePageList(UserListQuery query) {
        Page<User> page = new Page<>(query.getPageNo(), query.getPageSize());
        page.setDesc("tt.id");
        return traceMapper.tracePageList(page,query.getUserCode());
    }

}

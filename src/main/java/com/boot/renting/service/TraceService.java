package com.boot.renting.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.renting.entity.Trace;
import com.boot.renting.query.UserListQuery;
import com.boot.renting.vo.TraceInfoVo;


public interface TraceService extends IService<Trace> {


    IPage<TraceInfoVo> tracePageList(UserListQuery query);
}

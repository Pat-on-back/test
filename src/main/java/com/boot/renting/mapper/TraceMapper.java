package com.boot.renting.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boot.renting.entity.Trace;
import com.boot.renting.entity.User;
import com.boot.renting.vo.TraceInfoVo;
import org.apache.ibatis.annotations.Param;


public interface TraceMapper extends BaseMapper<Trace> {


    IPage<TraceInfoVo> tracePageList(@Param("page") Page<User> page, @Param("userCode") String userCode);

}

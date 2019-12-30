package com.boot.renting.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boot.renting.entity.Trace;
import com.boot.renting.query.UserListQuery;
import com.boot.renting.service.TraceService;
import com.boot.renting.utils.ResponseMessage;
import com.boot.renting.vo.TraceInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "足迹")
@RestController
@RequestMapping("trace")
public class TraceController {
    @Resource
    private TraceService traceService;

    @ApiOperation("分页列表")
    @PostMapping("tracePageList")
    public ResponseMessage<IPage<TraceInfoVo>> tracePageList(@RequestBody UserListQuery query) {
        return new ResponseMessage<>(traceService.tracePageList(query));
    }

    @ApiOperation("保存")
    @PostMapping("save")
    public ResponseMessage<Boolean> save(@RequestBody Trace trace) {
        if (trace.getUserCode() != null) {
            return new ResponseMessage<>(traceService.save(trace));
        }
        return new ResponseMessage<>();
    }

    @ApiOperation("通过id删除")
    @GetMapping("removeById/{id}")
    public ResponseMessage<Boolean> removeById(@PathVariable("id") Integer id) {
        return new ResponseMessage<>(traceService.removeById(id));
    }


}

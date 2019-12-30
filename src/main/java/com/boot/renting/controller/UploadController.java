package com.boot.renting.controller;


import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.boot.renting.utils.ResponseMessage;
import com.boot.renting.utils.create.CreateNo;
import com.boot.renting.utils.create.NoUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Api(tags = "文件")
@RestController
@RequestMapping("upload")
public class UploadController {



    private final String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\";


    @ApiOperation("上传文件")
    @PostMapping(value = "/uploadFile")
    public ResponseMessage<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        ResponseMessage<String> message = new ResponseMessage<>();
        if (file != null && !file.isEmpty()) {
            String name = NoUtil.getFileName(".png");
            File attachFile = FileUtil.touch(path, name);
            FileUtil.writeFromStream(file.getInputStream(), attachFile);
            message.setMessage("上传成功");
            message.setData("http://localhost/img/" + name);
        } else {
            message.setCode(500);
            message.setMessage("参数丢失");
        }
        return message;

    }

}

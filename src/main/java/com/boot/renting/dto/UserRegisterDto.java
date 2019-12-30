package com.boot.renting.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel("用户注册条件")
public class UserRegisterDto {

    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("手机号")
    private String name;
    @ApiModelProperty("验证码")
    private String code;
    @ApiModelProperty("类型 0:管理员 1:用户 2:房东")
    private Integer type;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}

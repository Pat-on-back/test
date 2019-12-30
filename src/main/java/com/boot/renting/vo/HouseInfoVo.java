package com.boot.renting.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel("房子详情")
public class HouseInfoVo {

    @ApiModelProperty("房源编码")
    private String houseCode;
    @ApiModelProperty("房东姓名")
    private String name;
    @ApiModelProperty("房东手机号")
    private String phone;
    @ApiModelProperty("标题")
    private String subject;
    @ApiModelProperty("地址")
    private String houseAddress;
    @ApiModelProperty("交易金额")
    private Integer price;
    @ApiModelProperty("备注")
    private String remark;
}

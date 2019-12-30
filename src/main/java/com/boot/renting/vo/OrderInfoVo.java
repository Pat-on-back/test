package com.boot.renting.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel("订单详情")
public class OrderInfoVo {

    @ApiModelProperty("订单编号")
    private String orderCode;
    @ApiModelProperty("用户姓名")
    private String userName;
    @ApiModelProperty("用户手机号")
    private String userPhone;
    @ApiModelProperty("房东姓名")
    private String landlordName;
    @ApiModelProperty("房东手机号")
    private String landlordPhone;
    @ApiModelProperty("标题")
    private String houseSubject;
    @ApiModelProperty("地址")
    private String houseAddress;
    @ApiModelProperty("交易金额")
    private Integer price;
}

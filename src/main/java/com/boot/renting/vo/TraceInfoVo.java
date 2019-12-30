package com.boot.renting.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("足迹详情")
public class TraceInfoVo {

    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("标题")
    private String subject;
    @ApiModelProperty("房东姓名")
    private String name;
    @ApiModelProperty("房东手机号")
    private String phone;
    @ApiModelProperty("地址")
    private String houseAddress;
    @ApiModelProperty("房源金额")
    private Integer price;
    @ApiModelProperty("房源备注")
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

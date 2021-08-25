package com.example.domain.DTOClass;

import io.swagger.annotations.ApiModelProperty;

public class DashBoardDTO {


    @ApiModelProperty(notes = "No of Customer")
    private Integer customerCount;

    @ApiModelProperty(notes = "email of the vehicleCount")
    private Integer vehicleCount;

    @ApiModelProperty(notes = "inquiryCount")
    private Integer inquiryCount;

    @ApiModelProperty(notes = "paymentCount")
    private Integer paymentCount;

    public Integer getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(Integer customerCount) {
        this.customerCount = customerCount;
    }

    public Integer getVehicleCount() {
        return vehicleCount;
    }

    public void setVehicleCount(Integer vehicleCount) {
        this.vehicleCount = vehicleCount;
    }

    public Integer getInquiryCount() {
        return inquiryCount;
    }

    public void setInquiryCount(Integer inquiryCount) {
        this.inquiryCount = inquiryCount;
    }

    public Integer getPaymentCount() {
        return paymentCount;
    }

    public void setPaymentCount(Integer paymentCount) {
        this.paymentCount = paymentCount;
    }
}

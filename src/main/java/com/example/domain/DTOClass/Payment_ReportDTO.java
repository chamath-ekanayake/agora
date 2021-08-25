package com.example.domain.DTOClass;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class Payment_ReportDTO {

    @ApiModelProperty(notes = " paymentDate")
    private Date paymentDate;

    @ApiModelProperty(notes = "customerName of the customerName")
    private String customerName;

    @ApiModelProperty(notes = "location")
    private String location;

    @ApiModelProperty(notes = "vehicleType")
    private String vehicleType;

    @ApiModelProperty(notes = "cabVehicle of the cabVehicle")
    private String cabVehicle;

    @ApiModelProperty(notes = "additionalKM")
    private String additionalKM;

    @ApiModelProperty(notes = "totalCharge")
    private String totalCharge;

    @ApiModelProperty(notes = "customerCharge")
    private String customerCharge;

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getCabVehicle() {
        return cabVehicle;
    }

    public void setCabVehicle(String cabVehicle) {
        this.cabVehicle = cabVehicle;
    }

    public String getAdditionalKM() {
        return additionalKM;
    }

    public void setAdditionalKM(String additionalKM) {
        this.additionalKM = additionalKM;
    }

    public String getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(String totalCharge) {
        this.totalCharge = totalCharge;
    }

    public String getCustomerCharge() {
        return customerCharge;
    }

    public void setCustomerCharge(String customerCharge) {
        this.customerCharge = customerCharge;
    }
}


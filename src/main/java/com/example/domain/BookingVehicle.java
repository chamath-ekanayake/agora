package com.example.domain;

import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.Date;

public class BookingVehicle {


    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "vehicleID of the BookingVehicle")
    private String id;

    @ApiModelProperty(notes = "driverName of the BookingVehicle")
    private String driverName;

    @ApiModelProperty(notes = "driverId of the BookingVehicle")
    private String driverId;

    @ApiModelProperty(notes = "vehicleNum of the BookingVehicle")
    private String vehicleNum;

    @ApiModelProperty(notes = "vehicleType of the BookingVehicle")
    private String vehicleType;

    @ApiModelProperty(notes = "startDate of the BookingVehicle")
    private Date startDate;

    @ApiModelProperty(notes = "endDate of the BookingVehicle")
    private Date endDate;

    @ApiModelProperty(notes = "endDate of the BookingVehicle")
    private Integer status;

    @ApiModelProperty(notes = "endDate of the BookingVehicle")
    private String inquiryId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getVehicleNum() {
        return vehicleNum;
    }

    public void setVehicleNum(String vehicleNum) {
        this.vehicleNum = vehicleNum;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(String inquiryId) {
        this.inquiryId = inquiryId;
    }

    @Override
    public String toString() {
        return "BookingVehicle{" +
                "id='" + id + '\'' +
                ", driverName='" + driverName + '\'' +
                ", driverId='" + driverId + '\'' +
                ", vehicleNum='" + vehicleNum + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                ", inquiryId='" + inquiryId + '\'' +
                '}';
    }
}

package com.example.domain;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class Vehicle {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "vehicleID of the vehicle")
    private String id;
    @ApiModelProperty(notes = "email of the vehicle")
    private String email;
    @ApiModelProperty(notes = "driverName of the vehicle")
    private String driverName;
    @ApiModelProperty(notes = "vehicleJointDate of the vehicle")
    private Date vehicleJointDate;
    @ApiModelProperty(notes = "vehicleNum of the vehicle")
    private String vehicleNum;
    @ApiModelProperty(notes = "vehicleType of the vehicle")
    private String vehicleType;
    @ApiModelProperty(notes = "seatNum of the vehicle")
    private String seatNum;
    @ApiModelProperty(notes = "contactNo of the vehicle")
    private String contactNo;
    @ApiModelProperty(notes = "vehicleAddress of the vehicle")
    private String vehicleAddress;
    @ApiModelProperty(notes = "driverNIC of the vehicle")
    private String driverNIC;
    @ApiModelProperty(notes = "vehicleModel of the vehicle")
    private String vehicleModel;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Date getVehicleJointDate() {
        return vehicleJointDate;
    }

    public void setVehicleJointDate(Date vehicleJointDate) {
        this.vehicleJointDate = vehicleJointDate;
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

    public String getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(String seatNum) {
        this.seatNum = seatNum;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getVehicleAddress() {
        return vehicleAddress;
    }

    public void setVehicleAddress(String vehicleAddress) {
        this.vehicleAddress = vehicleAddress;
    }

    public String getDriverNIC() {
        return driverNIC;
    }

    public void setDriverNIC(String driverNIC) {
        this.driverNIC = driverNIC;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }


    @Override
    public String toString() {
        return "Vehicle{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", driverName='" + driverName + '\'' +
                ", vehicleJointDate=" + vehicleJointDate +
                ", vehicleNum='" + vehicleNum + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", seatNum='" + seatNum + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", vehicleAddress='" + vehicleAddress + '\'' +
                ", driverNIC='" + driverNIC + '\'' +
                ", vehicleModel='" + vehicleModel + '\'' +
                '}';
    }
}

package com.example.domain.DTOClass;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class OurVehicleReportDTO {

    @ApiModelProperty(notes = " no")
    private String no;

    @ApiModelProperty(notes = " vehicleName")
    private String vehicleName;

    @ApiModelProperty(notes = "vehicleType of the vehicleType")
    private String vehicleType;

    @ApiModelProperty(notes = "location")
    private String location;

    @ApiModelProperty(notes = " hireCharge")
    private String hireCharge;

    @ApiModelProperty(notes = "driverSalary of the driverSalary")
    private String driverSalary;

    @ApiModelProperty(notes = " fuel")
    private String fuel;

    @ApiModelProperty(notes = "profit of the profit")
    private String profit;

    @ApiModelProperty(notes = "hire_date")
    private String hire_date;

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHireCharge() {
        return hireCharge;
    }

    public void setHireCharge(String hireCharge) {
        this.hireCharge = hireCharge;
    }

    public String getDriverSalary() {
        return driverSalary;
    }

    public void setDriverSalary(String driverSalary) {
        this.driverSalary = driverSalary;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getHire_date() {
        return hire_date;
    }

    public void setHire_date(String hire_date) {
        this.hire_date = hire_date;
    }
}

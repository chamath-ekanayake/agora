package com.example.domain.DTOClass;

import io.swagger.annotations.ApiModelProperty;

public class CustomerHireDTO {

    @ApiModelProperty(notes = " No")
    private String No;

    @ApiModelProperty(notes = "Customer_Name ")
    private String Customer_Name;

    @ApiModelProperty(notes = "hire_date")
    private String hire_date;

    @ApiModelProperty(notes = "vehicle_Name")
    private String vehicle_Name;

    @ApiModelProperty(notes = "vehicle_Type")
    private String vehicle_Type;

    @ApiModelProperty(notes = "location")
    private String location;

    @ApiModelProperty(notes = "hire_Charge")
    private String hire_Charge;

    public String getNo() {
        return No;
    }

    public void setNo(String no) {
        No = no;
    }

    public String getCustomer_Name() {
        return Customer_Name;
    }

    public void setCustomer_Name(String customer_Name) {
        Customer_Name = customer_Name;
    }

    public String getHire_date() {
        return hire_date;
    }

    public void setHire_date(String hire_date) {
        this.hire_date = hire_date;
    }

    public String getVehicle_Name() {
        return vehicle_Name;
    }

    public void setVehicle_Name(String vehicle_Name) {
        this.vehicle_Name = vehicle_Name;
    }

    public String getVehicle_Type() {
        return vehicle_Type;
    }

    public void setVehicle_Type(String vehicle_Type) {
        this.vehicle_Type = vehicle_Type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHire_Charge() {
        return hire_Charge;
    }

    public void setHire_Charge(String hire_Charge) {
        this.hire_Charge = hire_Charge;
    }
}

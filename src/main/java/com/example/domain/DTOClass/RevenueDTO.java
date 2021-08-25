package com.example.domain.DTOClass;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class RevenueDTO {

    @ApiModelProperty(notes = " No")
    private String No;

    @ApiModelProperty(notes = "Hire_Date ")
    private String Hire_Date;

    @ApiModelProperty(notes = "Customer_Name")
    private String Customer_Name;

    @ApiModelProperty(notes = "Location")
    private String Location;

    @ApiModelProperty(notes = "Vehicle_Type of the Vehicle_Type")
    private String Vehicle_Type;

    @ApiModelProperty(notes = "Cab_vehicle")
    private String Cab_vehicle;

    @ApiModelProperty(notes = "Customer_charge")
    private String Customer_charge;

    @ApiModelProperty(notes = "CabS_Commission")
    private String Cabs_Commission;


    public String getNo() {
        return No;
    }

    public void setNo(String no) {
        No = no;
    }

    public String getHire_Date() {
        return Hire_Date;
    }

    public void setHire_Date(String hire_Date) {
        Hire_Date = hire_Date;
    }

    public String getCustomer_Name() {
        return Customer_Name;
    }

    public void setCustomer_Name(String customer_Name) {
        Customer_Name = customer_Name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getVehicle_Type() {
        return Vehicle_Type;
    }

    public void setVehicle_Type(String vehicle_Type) {
        Vehicle_Type = vehicle_Type;
    }

    public String getCab_vehicle() {
        return Cab_vehicle;
    }

    public void setCab_vehicle(String cab_vehicle) {
        Cab_vehicle = cab_vehicle;
    }

    public String getCustomer_charge() {
        return Customer_charge;
    }

    public void setCustomer_charge(String customer_charge) {
        Customer_charge = customer_charge;
    }

    public String getCabs_Commission() {
        return Cabs_Commission;
    }

    public void setCabs_Commission(String cabs_Commission) {
        Cabs_Commission = cabs_Commission;
    }
}

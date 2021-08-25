package com.example.domain.DTOClass;

import io.swagger.annotations.ApiModelProperty;

public class CommissionDTO {

    @ApiModelProperty(notes = " No")
    private String No;

    @ApiModelProperty(notes = "vehicle_Owner ")
    private String vehicle_Owner;

    @ApiModelProperty(notes = "hire_date")
    private String hire_date;

    @ApiModelProperty(notes = "vehicle_Type")
    private String vehicle_Type;

    @ApiModelProperty(notes = "customer_Name")
    private String customer_Name;

    @ApiModelProperty(notes = "location")
    private String location;

    @ApiModelProperty(notes = "hire_Charge")
    private String hire_Charge;

    @ApiModelProperty(notes = "advance")
    private String advance;

    @ApiModelProperty(notes = "Commission")
    private String Commission;


}

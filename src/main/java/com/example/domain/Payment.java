package com.example.domain;

import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.Date;


public class Payment {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "vehicleID of the vehicle")
    private String id;
    @ApiModelProperty(notes = "inquiryId of the Payment")
    private String inquiryId;
    @ApiModelProperty(notes = "inquiryPrice of the Payment")
    private String inquiryPrice;
    @ApiModelProperty(notes = "AddKm of the Payment")
    private String addKm;
    @ApiModelProperty(notes = "kmCharge of the Payment")
    private String kmCharge;

    @ApiModelProperty(notes = "AddKm of the Payment")
    private String addHours;
    @ApiModelProperty(notes = "kmCharge of the Payment")
    private String hoursCharge;

    @ApiModelProperty(notes = "discount of the Payment")
    private String discount;
    @ApiModelProperty(notes = "customerCharge of the Payment")
    private String customerCharge;
    @ApiModelProperty(notes = "status of the Payment")
    private String status;
    @ApiModelProperty(notes = "fuel of the Payment")
    private String fuel;
    @ApiModelProperty(notes = "paymentDate of the Payment")
    private Date paymentDate;
    @ApiModelProperty(notes = "driverCharge of the Payment")
    private String driverCharge;
    @ApiModelProperty(notes = "commissionDis of the Payment")
    private String commissionDis;
    @ApiModelProperty(notes = "netProfit of the Payment")
    private String netProfit;
    @ApiModelProperty(notes = "netProfit of the Payment")
    private String LocalDate;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(String inquiryId) {
        this.inquiryId = inquiryId;
    }

    public String getInquiryPrice() {
        return inquiryPrice;
    }

    public void setInquiryPrice(String inquiryPrice) {
        this.inquiryPrice = inquiryPrice;
    }

    public String getAddKm() {
        return addKm;
    }

    public void setAddKm(String addKm) {
        this.addKm = addKm;
    }

    public String getKmCharge() {
        return kmCharge;
    }

    public void setKmCharge(String kmCharge) {
        this.kmCharge = kmCharge;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getCustomerCharge() {
        return customerCharge;
    }

    public void setCustomerCharge(String customerCharge) {
        this.customerCharge = customerCharge;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getDriverCharge() {
        return driverCharge;
    }

    public void setDriverCharge(String driverCharge) {
        this.driverCharge = driverCharge;
    }

    public String getCommissionDis() {
        return commissionDis;
    }

    public void setCommissionDis(String commissionDis) {
        this.commissionDis = commissionDis;
    }

    public String getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(String netProfit) {
        this.netProfit = netProfit;
    }


    public String getLocalDate() {
        return LocalDate;
    }

    public void setLocalDate(String localDate) {
        LocalDate = localDate;
    }


    public String getAddHours() {
        return addHours;
    }

    public void setAddHours(String addHours) {
        this.addHours = addHours;
    }

    public String getHoursCharge() {
        return hoursCharge;
    }

    public void setHoursCharge(String hoursCharge) {
        this.hoursCharge = hoursCharge;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id='" + id + '\'' +
                ", inquiryId='" + inquiryId + '\'' +
                ", inquiryPrice='" + inquiryPrice + '\'' +
                ", addKm='" + addKm + '\'' +
                ", kmCharge='" + kmCharge + '\'' +
                ", discount='" + discount + '\'' +
                ", customerCharge='" + customerCharge + '\'' +
                ", status='" + status + '\'' +
                ", fuel='" + fuel + '\'' +
                ", paymentDate=" + paymentDate +
                ", driverCharge='" + driverCharge + '\'' +
                ", commissionDis='" + commissionDis + '\'' +
                ", netProfit='" + netProfit + '\'' +
                ", LocalDate=" + LocalDate +
                '}';
    }
}

package com.example.domain;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class Inquiry {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "userId of the User")
    private String inquiryId;

    @ApiModelProperty(notes = "username of the user")
    private String contactNo;

    @ApiModelProperty(notes = "email of the user")
    private String email;

    @ApiModelProperty(notes = "address of the user")
    private String userName;

    @ApiModelProperty(notes = "nic of the user")
    private String startLocation;

    @ApiModelProperty(notes = "contactNo of the user")
    private String endLocation;

    @ApiModelProperty(notes = "dob of the user")
    private String selectBooking;

    @ApiModelProperty(notes = "dob of the user")
    private Date firstDate;

    @ApiModelProperty(notes = "dob of the user")
    private Date lastDate;

    @ApiModelProperty(notes = "dob of the user")
    private String price;

    @ApiModelProperty(notes = "dob of the user")
    private String remark;

    @ApiModelProperty(notes = "dob of the user")
    private String dateCount;

    @ApiModelProperty(notes = "dob of the user")
    private String selectVehicleType;

    @ApiModelProperty(notes = "dob of the user")
    private String status;

    @ApiModelProperty(notes = "driverName of the user")
    private String driverName;

    @ApiModelProperty(notes = "advance of the user")
    private String advance;

    @ApiModelProperty(notes = "pickUpTime of the user")
    private String pickUpTime;

    @ApiModelProperty(notes = "reminderDate of the inquiry")
    private Date reminderDate;

    @ApiModelProperty(notes = "nic of the inquiry")
    private String nic;

    @ApiModelProperty(notes = "address of the inquiry")
    private String address;

    @ApiModelProperty(notes = "night of the inquiry")
    private String night;

    @ApiModelProperty(notes = "allocateKM of the inquiry")
    private String allocateKM;

    @ApiModelProperty(notes = "kmCharge of the inquiry")
    private String kmCharge;

    @ApiModelProperty(notes = "allocateHours of the inquiry")
    private String allocateHours;

    @ApiModelProperty(notes = "hoursCharge of the inquiry")
    private String hoursCharge;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(String inquiryId) {
        this.inquiryId = inquiryId;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public String getSelectBooking() {
        return selectBooking;
    }

    public void setSelectBooking(String selectBooking) {
        this.selectBooking = selectBooking;
    }

    public Date getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Date firstDate) {
        this.firstDate = firstDate;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDateCount() {
        return dateCount;
    }

    public void setDateCount(String dateCount) {
        this.dateCount = dateCount;
    }

    public String getSelectVehicleType() {
        return selectVehicleType;
    }

    public void setSelectVehicleType(String selectVehicleType) {
        this.selectVehicleType = selectVehicleType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getAdvance() {
        return advance;
    }

    public void setAdvance(String advance) {
        this.advance = advance;
    }

    public String getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(String pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public Date getReminderDate() {
        return reminderDate;
    }

    public Date setReminderDate(Date reminderDate) {
        this.reminderDate = reminderDate;
        return reminderDate;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
    }

    public String getAllocateKM() {
        return allocateKM;
    }

    public void setAllocateKM(String allocateKM) {
        this.allocateKM = allocateKM;
    }

    public String getKmCharge() {
        return kmCharge;
    }

    public void setKmCharge(String kmCharge) {
        this.kmCharge = kmCharge;
    }

    public String getAllocateHours() {
        return allocateHours;
    }

    public void setAllocateHours(String allocateHours) {
        this.allocateHours = allocateHours;
    }

    public String getHoursCharge() {
        return hoursCharge;
    }

    public void setHoursCharge(String hoursCharge) {
        this.hoursCharge = hoursCharge;
    }

    @Override
    public String toString() {
        return "Inquiry{" +
                "inquiryId='" + inquiryId + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", startLocation='" + startLocation + '\'' +
                ", endLocation='" + endLocation + '\'' +
                ", selectBooking='" + selectBooking + '\'' +
                ", firstDate=" + firstDate +
                ", lastDate=" + lastDate +
                ", price='" + price + '\'' +
                ", remark='" + remark + '\'' +
                ", dateCount='" + dateCount + '\'' +
                ", selectVehicleType='" + selectVehicleType + '\'' +
                ", status='" + status + '\'' +
                ", driverName='" + driverName + '\'' +
                ", advance='" + advance + '\'' +
                ", pickUpTime='" + pickUpTime + '\'' +
                ", reminderDate=" + reminderDate +
                ", nic='" + nic + '\'' +
                ", address='" + address + '\'' +
                ", night='" + night + '\'' +
                ", allocateKM='" + allocateKM + '\'' +
                ", kmCharge='" + kmCharge + '\'' +
                ", allocateHours='" + allocateHours + '\'' +
                ", hoursCharge='" + hoursCharge + '\'' +
                '}';
    }
}

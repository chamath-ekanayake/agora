package com.example.service.daoService;

import com.example.domain.Inquiry;
import com.example.domain.Payment;

import java.util.Date;
import java.util.List;

public interface BookingDaoService {
    void insertBookingData(Inquiry inquiry);
    List<Inquiry> getAllPending();
    Inquiry getFind(String inquiryId);
    Inquiry updateBookingData(String inquiry, String price, String vehicle, String status);
    void RejectBookingInquiry(String inquiry, String status);
    List<Inquiry> isContactNo(String contactNo);
    List<Inquiry> getAllBooking(String status);
    List<Inquiry> isBookingContactNo(String contactNo);
    Inquiry updatePaymentData(String inquiry, String status);
    List<Inquiry> getAvailableVehicleList(Date firstDate, Date endDate, String vehicleType);
    List<Inquiry> getAll();
    Inquiry datePatch(String inquiryId,Date date);
    List<Inquiry> getReminderListToday(Date firstDate);
    List<Inquiry> getRunningHire_Today(Date firstDate);
    List<Inquiry> getMoreDayCount(String dayCount,String status);
    List<Inquiry> getReminderDateExtend(String inquiry,Date reminderDate,String charge);
    void reminderBooking(String inquiry, String price, String status);
    Inquiry updateBookingData2(String inquiry, String price, String vehicle, String status,String pickupTime,String advance);
    Inquiry getFindUpdateBooking(Inquiry inquiry);
    List<Inquiry> getSearchDateBetWeen(Date firstDate, Date LastDate);
    List<Inquiry> getSearchCustomerDateBetWeen(Date firstDate, Date LastDate,String contactNo);
    // Inquiry getInquiry(String In);
}

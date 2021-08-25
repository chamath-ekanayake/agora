package com.example.service.daoService;

import com.example.domain.BookingVehicle;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface VehicleBookingDaoService {
    List<BookingVehicle> getAvailableVehicleList(Date firstDate, Date endDate, String vehicleType);
    void getInsertVehicleBooking(BookingVehicle bookingVehicle);
    List<BookingVehicle> getFindAll();
    List<BookingVehicle> getAvailableVehicle(Date firstDate, Date endDate, String vehicleType);
    BookingVehicle getupdateData(String id,String status);
}

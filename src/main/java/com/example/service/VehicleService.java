package com.example.service;

import com.example.domain.BookingVehicle;
import com.example.domain.Inquiry;
import com.example.domain.User;
import com.example.domain.Vehicle;
import com.example.service.daoService.BookingDaoService;
import com.example.service.daoService.VehicleBookingDaoService;
import com.example.service.daoService.VehicleDaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VehicleService {

    private final Logger log = LoggerFactory.getLogger(BookingService.class);
    Vehicle vehicle = new Vehicle();

    @Qualifier("mysqlVehicle")
    @Autowired
    private VehicleDaoService vehicleDaoService;


    @Autowired
    @Qualifier("mysqlInquiry")
    private BookingDaoService bookingDaoService;

    @Autowired
    @Qualifier("mysqlVehicleBooking")
    private VehicleBookingDaoService vehicleBookingDaoService;

    public  Vehicle getPostVehicle(Vehicle vehicle)
    {

        List<Vehicle> listCount =vehicleDaoService.getFindAll();
        Integer count = listCount.size()+1;

        vehicle.setId("vehicle2020"+count);
        this.vehicleDaoService.insertVehicle(vehicle);
        return  vehicle;
    }
    public List findAvailableVehicle(String inquiryId) throws IOException {

        Inquiry inquiry = this.bookingDaoService.getFind(inquiryId);
         Date startDate = inquiry.getFirstDate();
        Date endDate = inquiry.getLastDate();
         String vehicleType = inquiry.getSelectVehicleType();
         List<BookingVehicle> bookingVehicle = this.vehicleBookingDaoService.getAvailableVehicleList(startDate,endDate,vehicleType);

         List<Vehicle> allVehicle = vehicleDaoService.getFindAll();
         List allAvailableVehicle = new ArrayList<>();

        for(Vehicle vehicle :allVehicle){
            boolean available=false;
            String loopVehicle=vehicle.getId();
            String driverName = vehicle.getDriverName();

            for(BookingVehicle booking : bookingVehicle){
                String bookingVehicleRecord = booking.getDriverId();
                if( loopVehicle.equals(bookingVehicleRecord)) {
                    available = true;
                    break;
                }
            }
//
//        for(BookingVehicle booking : bookingVehicle) {
//            String bookingVehicleRecord = booking.getDriverId();
//            for (Vehicle vehicle : allVehicle) {
//                String loopVehicle = vehicle.getId();
//                String driverName = vehicle.getDriverName();
//                if (loopVehicle.equals(bookingVehicleRecord)) {
//                    available = true;
//                    break;
//               }

                if (available == false) {
                   String inquirySelectVehicleType = inquiry.getSelectVehicleType();
                    String loopVehicleType = vehicle.getVehicleType();
                    if(loopVehicleType.equals(inquirySelectVehicleType)){
                        allAvailableVehicle.add(driverName);

                    }

                }

            }


        log.info("@@@@@@@@@@@@ ArrayList @@@@@@@@@@@"+allAvailableVehicle);
        return allAvailableVehicle;
    }


    public List<Vehicle> findAll() throws IOException {

        List<Vehicle>results = this.vehicleDaoService.getFindAll();
        log.info("@@@@@@@@@@@@ ArrayList @@@@@@@@@@@"+results);
        return results;
    }

    public List<Vehicle> getDriverNameSearch(String driverName)  {
        List<Vehicle> results= vehicleDaoService.isDriverNameSearch(driverName);
        return results;
    }

    public List<Vehicle> getAvailableVehicle(Date startDate,Date endDate,String vehicleType)  {

        List<BookingVehicle> bookingVehicle = this.vehicleBookingDaoService.getAvailableVehicle(startDate,endDate,vehicleType);

        log.info("@@@@@@@@@@@@ ArrayList getAvailableVehicle size  @@@@@@@@@@@"+bookingVehicle.size());
       List<Vehicle> allVehicle = vehicleDaoService.getAllVehicleForType(vehicleType);
        log.info("@@@@@@@@@@@@ ArrayList allVehicle size  @@@@@@@@@@@"+allVehicle.size());

        List<Vehicle> allAvailableVehicle = new ArrayList<>();



        for(Vehicle vehicle :allVehicle){

            boolean available=false;
            String loopVehicle=vehicle.getId();
            String driverName = vehicle.getDriverName();


            for(BookingVehicle booking : bookingVehicle){
                String bookingVehicleRecord = booking.getDriverId();

                if( loopVehicle.equals(bookingVehicleRecord)) {
                    available = true;
                    break;
                }
            }

            if (available == false) {
               // String inquirySelectVehicleType = inquiry.getSelectVehicleType();
                allAvailableVehicle.add(vehicle);

            }

        }

        List<Vehicle> results= allAvailableVehicle;
        return results;
    }

}

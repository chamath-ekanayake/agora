package com.example.service;

import com.example.domain.DTOClass.CustomerHireDTO;
import com.example.domain.DTOClass.OurVehicleReportDTO;
import com.example.domain.DTOClass.Payment_ReportDTO;
import com.example.domain.DTOClass.RevenueDTO;
import com.example.domain.Inquiry;
import com.example.domain.Payment;
import com.example.service.daoService.BookingDaoService;
import com.example.service.daoService.PaymetDaoService;
import com.example.service.daoService.VehicleBookingDaoService;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class ReportService {

    private final Logger log = LoggerFactory.getLogger(ReportService.class);
    //Inquiry inquiry = new Inquiry();

    @Qualifier("mysqlPayment")
    @Autowired
    private PaymetDaoService paymetDaoService;

    @Autowired
    @Qualifier("mysqlInquiry")
    private BookingDaoService bookingDaoService;

    @Autowired
    @Qualifier("mysqlVehicleBooking")
    private VehicleBookingDaoService vehicleBookingDaoService;


    public List<RevenueDTO> getsearchDateBetween(Date firstDate, Date LastDate) {

        // Date firstDaySearch = DateUtils.addDays(firstDate, 1);
        //   Date lastDaySearch = DateUtils.addDays(LastDate, 1);

        List<RevenueDTO> revenueDTOS = new ArrayList<>();
        List<Inquiry> results = this.bookingDaoService.getSearchDateBetWeen(firstDate, LastDate);

        log.info("REST *****************************************{{{{{  1  }}}}} ***********************************  : {}", results);

        for (int i = 0; i < results.size(); i++) {
            log.info("REST *****************************************{{{{{  count  }}}}} ***********************************  : {}", i);

            RevenueDTO revenueDTO = new RevenueDTO();


            int count = i + 1;
            revenueDTO.setNo("No " + count);
            Date date = results.get(i).getFirstDate();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = dateFormat.format(date);

            Date date1 = results.get(i).getLastDate();
            String strDate1 = dateFormat.format(date1);

            revenueDTO.setHire_Date(strDate + " To " + strDate1);

            revenueDTO.setCustomer_Name(results.get(i).getUserName());
            revenueDTO.setLocation(results.get(i).getStartLocation() + " To " + results.get(i).getEndLocation());
            revenueDTO.setVehicle_Type(results.get(i).getSelectVehicleType());
            Payment payment = this.paymetDaoService.getpayment(results.get(i).getInquiryId());

            if (payment.getCommissionDis() != null) {
                revenueDTO.setCab_vehicle("No");
            } else {
                revenueDTO.setCab_vehicle("Yes");
            }


            revenueDTO.setCustomer_charge("LKR " + payment.getCustomerCharge() + ".00");
            revenueDTO.setCabs_Commission("LKR " + payment.getNetProfit() + ".00");
            revenueDTOS.add(revenueDTO);
        }
        log.info("REST *****************************************{{{{{  2  }}}}} ***********************************  : {}", revenueDTOS);

        return revenueDTOS;
    }

    public List<CustomerHireDTO> getsearchCustomerDateBetween(Date firstDate, Date LastDate, String contactNo) {

        // Date firstDaySearch = DateUtils.addDays(firstDate, 1);
        //   Date lastDaySearch = DateUtils.addDays(LastDate, 1);

        List<CustomerHireDTO> customerHireDTOS = new ArrayList<>();
        List<Inquiry> results = this.bookingDaoService.getSearchCustomerDateBetWeen(firstDate, LastDate, contactNo);

        log.info("REST *****************************************{{{{{  1  }}}}} ***********************************  : {}", results);

        for (int i = 0; i < results.size(); i++) {
            log.info("REST *****************************************{{{{{  count  }}}}} ***********************************  : {}", i);

            CustomerHireDTO customerHireDTO = new CustomerHireDTO();


            int count = i + 1;
            customerHireDTO.setNo("No " + count);
            Date date = results.get(i).getFirstDate();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = dateFormat.format(date);

            Date date1 = results.get(i).getLastDate();
            String strDate1 = dateFormat.format(date1);

            customerHireDTO.setHire_date(strDate + " To " + strDate1);

            customerHireDTO.setCustomer_Name(results.get(i).getUserName());
            customerHireDTO.setVehicle_Name(results.get(i).getDriverName());
            customerHireDTO.setLocation(results.get(i).getStartLocation() + " To " + results.get(i).getEndLocation());
            customerHireDTO.setVehicle_Type(results.get(i).getSelectVehicleType());
            Payment payment = this.paymetDaoService.getpayment(results.get(i).getInquiryId());
            customerHireDTO.setHire_Charge(payment.getCustomerCharge());

//
            customerHireDTOS.add(customerHireDTO);
        }
        log.info("REST *****************************************{{{{{  2  }}}}} ***********************************  : {}", customerHireDTOS);

        return customerHireDTOS;
    }


    public List<OurVehicleReportDTO> getsearchOurVehicleBetween(Date firstDate, Date LastDate) {

        // Date firstDaySearch = DateUtils.addDays(firstDate, 1);
        //   Date lastDaySearch = DateUtils.addDays(LastDate, 1);

        List<OurVehicleReportDTO> ourVehicleReportDTOS = new ArrayList<>();
        List<Inquiry> results = this.bookingDaoService.getSearchDateBetWeen(firstDate, LastDate);

        log.info("REST *****************************************{{{{{  1  }}}}} ***********************************  : {}", results);

        for (int i = 0; i < results.size(); i++) {
            log.info("REST *****************************************{{{{{  count  }}}}} ***********************************  : {}", i);

            Payment payment = this.paymetDaoService.getpayment(results.get(i).getInquiryId());

            if (payment.getStatus().equals("Yes")) {

                OurVehicleReportDTO ourVehicleReportDTO = new OurVehicleReportDTO();
                int count = ourVehicleReportDTOS.size() + 1;
                ourVehicleReportDTO.setNo("No " + count);
                Date date = results.get(i).getFirstDate();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = dateFormat.format(date);

                Date date1 = results.get(i).getLastDate();
                String strDate1 = dateFormat.format(date1);

                ourVehicleReportDTO.setHire_date(strDate + " To " + strDate1);
                ourVehicleReportDTO.setVehicleName(results.get(i).getDriverName());
                ourVehicleReportDTO.setVehicleType(results.get(i).getSelectVehicleType());
                ourVehicleReportDTO.setLocation(results.get(i).getStartLocation() + " To " + results.get(i).getEndLocation());
                ourVehicleReportDTO.setHireCharge(payment.getCustomerCharge());
                ourVehicleReportDTO.setDriverSalary(payment.getDriverCharge());
                ourVehicleReportDTO.setFuel(payment.getFuel());
                ourVehicleReportDTO.setProfit(payment.getNetProfit());
                ourVehicleReportDTOS.add(ourVehicleReportDTO);
            }


            log.info("REST *****************************************{{{{{  2  }}}}} ***********************************  : {}", ourVehicleReportDTOS);


        }
        return ourVehicleReportDTOS;
    }

}

package com.example.service;

import com.example.domain.DTOClass.Payment_ReportDTO;
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

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class PaymentService {

    private final Logger log = LoggerFactory.getLogger(PaymentService.class);
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

    public Payment getPostInquiry(Payment payment) {
        String idInquiry = payment.getInquiryId();
        String statusInquiry = "4";

        //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        Date firstDay = DateUtils.addDays(date, 1);

        List<Payment> listcount = paymetDaoService.getAll();

        Integer count = listcount.size() + 1;
        payment.setId("payment2020" + count);
        payment.setPaymentDate(firstDay);

        this.paymetDaoService.insertPayment(payment);

        this.bookingDaoService.updatePaymentData(idInquiry, statusInquiry);

        this.vehicleBookingDaoService.getupdateData(payment.getInquiryId(), "2");

        return payment;
    }

    public List<Payment_ReportDTO> getsearchDateBetween(Date firstDate, Date LastDate) {

        // Date firstDaySearch = DateUtils.addDays(firstDate, 1);
        //   Date lastDaySearch = DateUtils.addDays(LastDate, 1);

        List<Payment_ReportDTO> payment_reportDTO = new ArrayList<>();
        List<Payment> results = paymetDaoService.SearchDateBetWeen(firstDate, LastDate);

        for (int i = 0; i < results.size(); i++) {

            Inquiry inquiry = bookingDaoService.getFind(results.get(i).getInquiryId());
            log.info("log info @@@@@@@@@@ Inquiry  @@@@@@@@@@" + inquiry);

            Payment_ReportDTO payment_reportDTO1 = new Payment_ReportDTO();
            payment_reportDTO1.setPaymentDate(results.get(i).getPaymentDate());
            log.info("log info @@@@@@@@@@ Inquiry  @@@@@@@@@@" + results.get(i).getPaymentDate());
            payment_reportDTO1.setCustomerName(inquiry.getUserName());
            log.info("log info @@@@@@@@@@ Inquiry  @@@@@@@@@@" + inquiry.getUserName());
            payment_reportDTO1.setLocation(inquiry.getStartLocation() + " to " + inquiry.getEndLocation());
            log.info("log info @@@@@@@@@@ Inquiry  @@@@@@@@@@" + inquiry.getStartLocation() + " to " + inquiry.getEndLocation());
            payment_reportDTO1.setVehicleType(inquiry.getSelectBooking());
            log.info("log info @@@@@@@@@@ Inquiry  @@@@@@@@@@" + inquiry.getSelectBooking());
            payment_reportDTO1.setCabVehicle(results.get(i).getStatus());
            log.info("log info @@@@@@@@@@ Inquiry  @@@@@@@@@@" + results.get(i).getStatus());

            if(results.get(i).getAddKm()==null){
                payment_reportDTO1.setAdditionalKM("0");
                log.info("log info @@@@@@@@@@ Inquiry  @@@@@@@@@@" + "0KM");
            }else{
                payment_reportDTO1.setAdditionalKM(results.get(i).getAddKm());
                log.info("log info @@@@@@@@@@ Inquiry  @@@@@@@@@@" + results.get(i).getAddKm());
            }

           // payment_reportDTO1.setAdditionalKM(results.get(i).getAddKm());
            log.info("log info @@@@@@@@@@ Inquiry  @@@@@@@@@@" + results.get(i).getStatus());
            payment_reportDTO1.setCustomerCharge(results.get(i).getCustomerCharge());
            log.info("log info @@@@@@@@@@ Inquiry  @@@@@@@@@@" + results.get(i).getCustomerCharge());
            payment_reportDTO1.setTotalCharge(results.get(i).getNetProfit());
            log.info("log info @@@@@@@@@@ Inquiry  @@@@@@@@@@" + results.get(i).getNetProfit());

            payment_reportDTO.add(payment_reportDTO1);
        }

        return payment_reportDTO;
    }


}

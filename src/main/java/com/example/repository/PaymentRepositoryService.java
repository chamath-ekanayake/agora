package com.example.repository;


import com.example.domain.Inquiry;
import com.example.domain.Payment;
import com.example.other.SequenceGenerator;
import com.example.service.daoService.PaymetDaoService;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository("mysqlPayment")
public class PaymentRepositoryService implements PaymetDaoService {

    private final Logger log = LoggerFactory.getLogger(PaymentRepositoryService.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    SequenceGenerator seq = new SequenceGenerator();
    private static class PaymentRowMapper implements RowMapper<Payment> {

        @Override
        public Payment mapRow(ResultSet resultSet, int i) throws SQLException {
            Payment payment = new Payment();
            payment.setInquiryId(resultSet.getString("inquiryId"));

            payment.setInquiryPrice(resultSet.getString("inquiryPrice"));
            payment.setAddKm(resultSet.getString("AddKm"));
            payment.setKmCharge(resultSet.getString("kmCharge"));
            payment.setDiscount(resultSet.getString("discount"));
            payment.setCustomerCharge(resultSet.getString("customerCharge"));
            payment.setStatus(resultSet.getString("status"));
            payment.setFuel(resultSet.getString("fuel"));
            payment.setPaymentDate(resultSet.getDate("paymentDate"));
            payment.setDriverCharge(resultSet.getString("driverCharge"));
            payment.setCommissionDis(resultSet.getString("commissionDis"));
            payment.setNetProfit(resultSet.getString("netProfit"));
            payment.setLocalDate(resultSet.getString("localDate"));
            payment.setAddHours(resultSet.getString("addHours"));
            payment.setHoursCharge(resultSet.getString("hoursCharge"));
            return payment;
        }
    }

    @Override
    public void insertPayment(Payment payment) {
        try {
           // payment.setId(SequenceGenerator.generateSequence("payment"));
            final String sql = "INSERT INTO payment (id,inquiryId,inquiryPrice,AddKm,kmCharge,discount,customerCharge,status,fuel,paymentDate,driverCharge,commissionDis,netProfit,localDate,addHours,hoursCharge) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            final String id = payment.getId();
            final String inquiryId = payment.getInquiryId();
            final String inquiryPrice = payment.getInquiryPrice();
            final String AddKm = payment.getAddKm();
            final String kmCharge = payment.getKmCharge();
            final String discount = payment.getDiscount();
            final String customerCharge = payment.getCustomerCharge();
            final String status = payment.getStatus();
            final String fuel = payment.getFuel();
            final Date paymentDate = payment.getPaymentDate();
            final String driverCharge = payment.getDriverCharge();
            final  String commissionDis = payment.getCommissionDis();
            final String netProfit = payment.getNetProfit();
            final String localDate = payment.getLocalDate();
            final String addHours = payment.getAddHours();
            final String hoursCharge = payment.getHoursCharge();
            log.info("log info @@@@@@@@@@ Payment Repository @@@@@@@@@@ payment Date111111111111111111111111111" + paymentDate);
            jdbcTemplate.update(sql, new Object[]{id,inquiryId, inquiryPrice, AddKm, kmCharge, discount , customerCharge, status, fuel,paymentDate,driverCharge,commissionDis,netProfit,localDate,addHours,hoursCharge});
        } catch (Exception e) {
            log.info("log info @@@@@@@@@@ Payment Repository @@@@@@@@@@" + e);
        }

    }



//    @Override
//    public List<Payment> SearchDateBetWeen(LocalDate firstDate,LocalDate lastDate) {
//        // SELECT column_name(s) FROM table_name
//
//        log.info("log info @@@@@@@@@@ Payment Repository @@@@@@@@@@ WelCome" + firstDate);
//      //  ZoneId defaultZoneId = ZoneId.systemDefault();
//       // Date date = Date.from(firstDate.atStartOfDay(defaultZoneId).toInstant());
////            "+firstDate+" AND "+lastDate+"
//    //    log.info("log info @@@@@@@@@@ Payment Repository @@@@@@@@@@ WelCome 2" + date);
//
//
//        String date = firstDate.toString();
//        final String sql = "SELECT * FROM payment where localDate=?";
//        List<Payment> PaymentList= jdbcTemplate.query(sql,new Object[]{date}, new PaymentRowMapper());
//        log.info("log info @@@@@@@@@@ Payment Repository @@@@@@@@@@ WelCome 3" + PaymentList);
//
//        return PaymentList;
//    }


//
//    @Override
//    public List<Payment> SearchDateBetWeen(LocalDate firstDate,LocalDate lastDate) {
//       // String stringDate = firstDate.toString();
//        String stringDate = "1";
//
//        log.info("log info @@@@@@@@@@ Payment Repository @@@@@@@@@@ WelCome" + stringDate);
//
//        final String sql = "SELECT * FROM payment WHERE inquiryPrice = "+stringDate;
//        List<Payment> PaymentList = null;
//        try {
//            PaymentList = jdbcTemplate.query(sql,  new PaymentRowMapper());
//            log.info("log info @@@@@@@@@@ Payment Repository @@@@@@@@@@ WelCome 2" + PaymentList);
//
//        }
//        catch(Exception ex)
//        {
//            log.info("log info @@@@@@@@@@ Payment Repository @@@@@@@@@@ 3" + ex);
//        }
//
//        return PaymentList;
//    }


    public  List<Payment> SearchDateBetWeen(Date firstDate, Date endDate) {
        List<Payment> vehicleList=null;
        try {
            Date firstDay12 = DateUtils.addDays(firstDate, 1);
            Date endDate12 = DateUtils.addDays(endDate, 1);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = simpleDateFormat.format(firstDay12);
            String date2 = simpleDateFormat.format(endDate12);
          //  String date1 = "2020-05-15";
          //  String date2 = "2020-05-16";

            final String sql = "SELECT * FROM payment where paymentDate  BETWEEN '"+ date1 +"' AND '"+ date2 +"' ";
          //  final String sql = "SELECT * FROM payment where (paymentDate>='2020-05-22')  AND (paymentDate>='2020-11-10')  ";

            vehicleList = jdbcTemplate.query(sql, new PaymentRowMapper());

            log.info("log info @@@@@@@@@@ vehicleList Repository @@@@@@@@@@" + date1);
            log.info("log info @@@@@@@@@@ vehicleList Repository @@@@@@@@@@" + date2);

            return vehicleList;
        }
        catch(Exception ex)
        {
            log.info("log info @@@@@@@@@@ vehicleList Repository @@@@@@@@@@" + ex);
        }
        return vehicleList;
    }

    public List<Payment> getAll() {
        // SELECT column_name(s) FROM table_name
        final String sql = "SELECT * FROM payment ";
        List<Payment> List = jdbcTemplate.query(sql, new PaymentRowMapper());
        return List;
    }



    @Override
    public Payment getpayment(String inquiryId) {

        Payment payment = null;
        final String sql = "SELECT * FROM payment where inquiryId =? ";
        try {

            payment =  jdbcTemplate.queryForObject(sql, new Object[]{inquiryId}, new PaymentRepositoryService.PaymentRowMapper());
            return payment;

        } catch (Exception e) {
            log.info("log info @@@@@@@@@@ Inquiry Repository @@@@@@@@@@" + e);
        }

        return payment;
    }
}

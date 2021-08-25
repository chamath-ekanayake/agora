package com.example.repository;


import com.example.domain.Inquiry;
import com.example.domain.Payment;
import com.example.other.SequenceGenerator;
import com.example.service.daoService.BookingDaoService;
import org.apache.commons.lang3.time.DateUtils;
import org.assertj.core.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Repository("mysqlInquiry")
public class BookingRepositoryService implements BookingDaoService {
    private final Logger log = LoggerFactory.getLogger(BookingRepositoryService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    SequenceGenerator seq = new SequenceGenerator();
    private static class BookingRowMapper implements RowMapper<Inquiry> {

        @Override
        public Inquiry mapRow(ResultSet resultSet, int i) throws SQLException {
            Inquiry inquiry = new Inquiry();
            inquiry.setInquiryId(resultSet.getString("inquiryId"));
            inquiry.setUserName(resultSet.getString("userName"));
            inquiry.setEmail(resultSet.getString("email"));
            inquiry.setContactNo(resultSet.getString("contactNo"));
            inquiry.setDateCount(resultSet.getString("dateCount"));
            inquiry.setEndLocation(resultSet.getString("endLocation"));
            inquiry.setStartLocation(resultSet.getString("startLocation"));
            inquiry.setFirstDate(resultSet.getDate("firstDate"));
            inquiry.setLastDate(resultSet.getDate("lastDate"));
            inquiry.setSelectBooking(resultSet.getString("selectBooking"));
            inquiry.setPrice(resultSet.getString("price"));
            inquiry.setRemark(resultSet.getString("remark"));
            inquiry.setSelectVehicleType(resultSet.getString("selectVehicleType"));
            inquiry.setStatus(resultSet.getString("status"));
            inquiry.setDriverName(resultSet.getString("driverName"));
            inquiry.setAdvance(resultSet.getString("advance"));
            inquiry.setPickUpTime(resultSet.getString("pickUpTime"));
            inquiry.setReminderDate(resultSet.getDate("reminderDate"));
            inquiry.setNic(resultSet.getString("nic"));
            inquiry.setAddress(resultSet.getString("address"));
            inquiry.setNight(resultSet.getString("night"));
            inquiry.setAllocateKM(resultSet.getString("allocateKM"));
            inquiry.setKmCharge(resultSet.getString("kmCharge"));
            inquiry.setAllocateHours(resultSet.getString("allocateHours"));
            inquiry.setHoursCharge(resultSet.getString("hoursCharge"));
            return inquiry;
        }

    }

    @Override
    public void insertBookingData(Inquiry inquiry) {
        try {
                // inquiry.setInquiryId(SequenceGenerator.generateSequence("inquiry"));
                final String sql = "INSERT INTO Inquiry (inquiryId,userName,email,contactNo,dateCount,endLocation,startLocation,firstDate,lastDate,selectBooking,price,remark,selectVehicleType,status,driverName,advance,pickUpTime,reminderDate,nic,address,night,allocateKM,kmCharge,allocateHours,hoursCharge) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                final String inquiryId = inquiry.getInquiryId();
                final String userName = inquiry.getUserName();
                final String email = inquiry.getEmail();
                final String contactNo = inquiry.getContactNo();
                final String dateCount = inquiry.getDateCount();
                final String endLocation = inquiry.getEndLocation();
                final String startLocation = inquiry.getStartLocation();

                final Date firstDate = inquiry.getFirstDate();
                final Date lastDate = inquiry.getLastDate();

                final String selectBooking = inquiry.getSelectBooking();
                final  String price = inquiry.getPrice();
                final String remark = inquiry.getRemark();
                final String status = inquiry.getStatus();
                final String selectVehicleType =inquiry.getSelectVehicleType();
                final String driverName = inquiry.getDriverName();
                final String advance = inquiry.getAdvance();
                final String pickUpTime = inquiry.getPickUpTime();
                final Date  reminderDate= inquiry.getReminderDate();
                final String nic = inquiry.getNic();
                final String address = inquiry.getAddress();
                final String night = inquiry.getNight();
                final String allocateKM = inquiry.getAllocateKM();
                final String kmCharge = inquiry.getKmCharge();
                final String allocateHours = inquiry.getAllocateHours();
                final String hoursCharge = inquiry.getHoursCharge();



            log.info("log info @@@@@@@@@@ Inquiry Repository Data Log @@@@@@@@@@" + firstDate);

                jdbcTemplate.update(sql, new Object[]{inquiryId, userName, email, contactNo, dateCount , endLocation, startLocation, firstDate,lastDate,selectBooking,price,remark,selectVehicleType,status,driverName,advance,pickUpTime,reminderDate,nic,address,night,allocateKM,kmCharge,allocateHours,hoursCharge});

        } catch (Exception e) {
            log.info("log info @@@@@@@@@@ Inquiry Repository @@@@@@@@@@" + e);
        }

    }

    public List<Inquiry> getAllPending() {
        // SELECT column_name(s) FROM table_name
        final String sql = "SELECT * FROM Inquiry where status=1 ";
        List<Inquiry> pendingList = jdbcTemplate.query(sql, new BookingRowMapper());
        return pendingList;
    }

    public Inquiry getFind(String inquiryId) {
        // SELECT column_name(s) FROM table_name
        final String sql = "SELECT * FROM Inquiry where inquiryId= ? ";

        Inquiry inquiry = null;
        try {
            inquiry =  jdbcTemplate.queryForObject(sql, new Object[]{inquiryId}, new BookingRowMapper());
            log.info("log info @@@@@@@@@@ Inquiry Repository @@@@@@@@@@" + inquiry);

            return inquiry;
        }
        catch(Exception ex)
        {
            log.info("log info @@@@@@@@@@ user Repository @@@@@@@@@@" + ex);
        }
        return inquiry;
    }

    @Override
    public Inquiry updateBookingData(String inquiry, String price, String vehicle, String status) {
        final String sql = "UPDATE Inquiry set price=? ,driverName =?,status =? where inquiryId =? ";
        try {

            jdbcTemplate.update(sql, new Object[]{price,vehicle,status,inquiry});

        } catch (Exception e) {
            log.info("log info @@@@@@@@@@ Inquiry Repository @@@@@@@@@@" + e);
        }

        return null;
    }

    @Override
    public void RejectBookingInquiry(String inquiry,String status) {
        final String sql = "UPDATE Inquiry set status =? where inquiryId =? ";
        try {

            jdbcTemplate.update(sql, new Object[]{status,inquiry});

        } catch (Exception e) {
            log.info("log info @@@@@@@@@@ Inquiry Repository @@@@@@@@@@" + e);
        }

    }

    @Override
    public List<Inquiry> isContactNo(String contactNo) {

        final String sql = "SELECT * FROM Inquiry WHERE contactNo = ? AND status = ?";
        List<Inquiry> inquiryList = null;
        try {
            inquiryList = jdbcTemplate.query(sql, new Object[]{contactNo,"1"}, new BookingRowMapper());
        }
        catch(Exception ex)
        {
            log.info("log info @@@@@@@@@@ Inquiry Repository @@@@@@@@@@" + ex);
        }
        return inquiryList;
    }

    public List<Inquiry> getAllBooking(String status) {
        // SELECT column_name(s) FROM table_name
        final String sql = "SELECT * FROM Inquiry where status= ?";
        List<Inquiry> pendingList = jdbcTemplate.query(sql,new Object[]{status}, new BookingRowMapper());
        return pendingList;
    }

    @Override
    public List<Inquiry> isBookingContactNo(String contactNo) {
        final String sql = "SELECT * FROM Inquiry WHERE contactNo = ? AND status = ?";
        List<Inquiry> inquiryList = null;
        try {
            inquiryList = jdbcTemplate.query(sql, new Object[]{contactNo,"2"}, new BookingRowMapper());
        }
        catch(Exception ex)
        {
            log.info("log info @@@@@@@@@@ Inquiry Repository @@@@@@@@@@" + ex);
        }
        return inquiryList;
    }

    @Override
    public Inquiry updatePaymentData(String inquiry,String status) {
        final String sql = "UPDATE Inquiry set status =? where inquiryId =? ";
        try {

            jdbcTemplate.update(sql, new Object[]{status,inquiry});

        } catch (Exception e) {
            log.info("log info @@@@@@@@@@ Inquiry Repository @@@@@@@@@@" + e);
        }

        return null;
    }



    public  List<Inquiry> getAvailableVehicleList(Date firstDate, Date endDate,String vehicleType) {

        Date startDate = DateUtils.addDays(firstDate, 1);
        Date endDate2 = DateUtils.addDays(endDate, 1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate1 = simpleDateFormat.format(startDate);
        String stringDate2 = simpleDateFormat.format(endDate2);
        String Status = "4";
        //  String date1 = "2020-05-15";
        //  String date2 = "2020-05-16";

        final String sql = "SELECT * FROM Inquiry where firstDate  BETWEEN '"+ stringDate1 +"' AND '"+ stringDate2 +"' AND lastDate  BETWEEN '"+ stringDate1 +"' AND '"+ stringDate2 +"' AND selectVehicleType= '"+ vehicleType +"' AND status = '"+ Status +"'";
        //  final String sql = "SELECT * FROM payment where (paymentDate>='2020-05-22')  AND (paymentDate>='2020-11-10')  ";
        List<Inquiry> vehicleList=null;
        try{
            vehicleList = jdbcTemplate.query(sql, new BookingRowMapper());
            log.info("log info @@@@@@@@@@ vehicleList Repository @@@@@@@@@@" + firstDate);
            return vehicleList;
        }
        catch(Exception ex)
        {
            log.info("log info @@@@@@@@@@ vehicleList Repository @@@@@@@@@@" + ex);
        }
        return vehicleList;
    }
    public List<Inquiry> getAll() {
        // SELECT column_name(s) FROM table_name
        final String sql = "SELECT * FROM Inquiry ";
        List<Inquiry> List = jdbcTemplate.query(sql, new BookingRowMapper());
        return List;
    }

    public Inquiry datePatch(String inquiryId,Date date)  {
        // SELECT column_name(s) FROM table_name
      //  datefield = NULL WHERE datefield = '0000-00-00 00:00:00';
        final String sql = "UPDATE Inquiry set reminderDate =NULL where inquiryId =? ";
        try {

            jdbcTemplate.update(sql, new Object[]{inquiryId});

        } catch (Exception e) {
            log.info("log info @@@@@@@@@@ Inquiry Repository @@@@@@@@@@" + e);
        }

        return null;
    }

    public  List<Inquiry> getReminderListToday(Date firstDate) {

        Instant now = Instant.now(); //current date
       // Instant before = now.minus(Duration.ofDays(1));
      //  Date dateBefore = Date.from(before);
        Date dateBefore1 = Date.from(now);
        log.info("log info @@@@@@@@@@ TODAY_DATE 11111111 @@@@@@@@@@" + dateBefore1);

        Date dateBefore2 = DateUtils.addDays(dateBefore1, 0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate1 = simpleDateFormat.format(dateBefore2);
        //String stringDate2 = simpleDateFormat.format(endDate2);

        log.info("log info @@@@@@@@@@ TODAY_DATE @@@@@@@@@@" + stringDate1);

        String Status = "8";
        //  String date1 = "2020-05-15";
        //  String date2 = "2020-05-16";
     //   String stringDate1 = "2020-08-18";
        final String sql = "SELECT * FROM Inquiry where reminderDate='"+ stringDate1 +"' AND status = '"+ Status +"'";
        //  final String sql = "SELECT * FROM payment where (paymentDate>='2020-05-22')  AND (paymentDate>='2020-11-10')  ";
        List<Inquiry> reminderListToday=null;
        try{
            reminderListToday = jdbcTemplate.query(sql, new BookingRowMapper());
            log.info("log info @@@@@@@@@@ vehicleList Repository @@@@@@@@@@" + firstDate);
            return reminderListToday;
        }
        catch(Exception ex)
        {
            log.info("log info @@@@@@@@@@ vehicleList Repository @@@@@@@@@@" + ex);
        }
        return reminderListToday;
    }

    public  List<Inquiry> getRunningHire_Today(Date firstDate) {

        Instant now = Instant.now(); //current date
        // Instant before = now.minus(Duration.ofDays(1));
        //  Date dateBefore = Date.from(before);
        Date dateBefore1 = Date.from(now);
        log.info("log info @@@@@@@@@@ TODAY_DATE 11111111 @@@@@@@@@@" + dateBefore1);

        Date dateBefore2 = DateUtils.addDays(dateBefore1, 0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate1 = simpleDateFormat.format(dateBefore2);
        String Status = "1";
        //  String date1 = "2020-05-15";
        //  String date2 = "2020-05-16";

        final String sql = "SELECT * FROM Inquiry where firstDate  BETWEEN '"+ stringDate1 +"' AND '"+ stringDate1 +"' AND lastDate  BETWEEN '"+ stringDate1 +"' AND '"+ stringDate1 +"' AND status = '"+ Status +"'";
        //  final String sql = "SELECT * FROM payment where (paymentDate>='2020-05-22')  AND (paymentDate>='2020-11-10')  ";
        List<Inquiry> vehicleList=null;
        try{
            vehicleList = jdbcTemplate.query(sql, new BookingRowMapper());
            log.info("log info @@@@@@@@@@ vehicleList Repository @@@@@@@@@@" + firstDate);
            return vehicleList;
        }
        catch(Exception ex)
        {
            log.info("log info @@@@@@@@@@ vehicleList Repository @@@@@@@@@@" + ex);
        }
        return vehicleList;
    }


    @Override
    public List<Inquiry> getMoreDayCount(String dayCount,String status)  {
        final String sql = "SELECT * FROM Inquiry WHERE dateCount = ? AND status = ?";
        List<Inquiry> inquiryList = null;
        try {
            inquiryList = jdbcTemplate.query(sql, new Object[]{dayCount,"1"}, new BookingRowMapper());
        }
        catch(Exception ex)
        {
            log.info("log info @@@@@@@@@@ Inquiry Repository @@@@@@@@@@" + ex);
        }
        return inquiryList;
    }

    public  List<Inquiry> getReminderDateExtend(String inquiryId,Date reminderDate,String charge)  {
        // SELECT column_name(s) FROM table_name
        //  datefield = NULL WHERE datefield = '0000-00-00 00:00:00';
        final String sql = "UPDATE Inquiry set reminderDate =?,price=? where inquiryId =? ";
        try {

            jdbcTemplate.update(sql, new Object[]{reminderDate,charge,inquiryId});

        } catch (Exception e) {
            log.info("log info @@@@@@@@@@ Inquiry Repository @@@@@@@@@@" + e);
        }

        return null;
    }


    @Override
    public void reminderBooking(String inquiry,String price,String status) {
        final String sql = "UPDATE Inquiry set status =?,price=? where inquiryId =? ";
        try {

            jdbcTemplate.update(sql, new Object[]{status,price,inquiry});

        } catch (Exception e) {
            log.info("log info @@@@@@@@@@ Inquiry Repository @@@@@@@@@@" + e);
        }

    }

    @Override
    public Inquiry updateBookingData2(String inquiry, String price, String vehicle, String status,String pickupTime,String advance) {
        final String sql = "UPDATE Inquiry set price=? ,driverName =?,status =? ,pickUpTime=?,advance=? where inquiryId =? ";
        try {

            jdbcTemplate.update(sql, new Object[]{price,vehicle,status,pickupTime,advance,inquiry});

        } catch (Exception e) {
            log.info("log info @@@@@@@@@@ Inquiry Repository @@@@@@@@@@" + e);
        }

        return null;
    }

    @Override
    public Inquiry getFindUpdateBooking(Inquiry inquiry) {
        String inquiryId = inquiry.getInquiryId();
        String selectVehicleType = inquiry.getSelectVehicleType();
        String startLocation = inquiry.getStartLocation();
        String endLocation = inquiry.getEndLocation();
        Date firstDate = inquiry.getFirstDate();
        Date lastDate = inquiry.getLastDate();
        String dateCount =inquiry.getDateCount();
        String night = inquiry.getNight();
        String price = inquiry.getPrice();
        String allocateKM = inquiry.getAllocateKM();
        String kmCharge = inquiry.getKmCharge();
        String allocateHours = inquiry.getAllocateHours();
        String hoursCharge = inquiry.getHoursCharge();


        final String sql = "UPDATE Inquiry set selectVehicleType=? ,startLocation=? ,endLocation =?,firstDate =? ,lastDate=?,dateCount=?,night=?,price=?,allocateKM=?,kmCharge=?,allocateHours=?,hoursCharge=? where inquiryId =? ";
        try {

            jdbcTemplate.update(sql, new Object[]{selectVehicleType,startLocation,endLocation,firstDate,lastDate,dateCount,night,price,allocateKM,kmCharge,allocateHours,hoursCharge,inquiryId});

        } catch (Exception e) {
            log.info("log info @@@@@@@@@@ Inquiry Repository @@@@@@@@@@" + e);
        }

        return null;
    }




    public  List<Inquiry> getSearchDateBetWeen(Date firstDate, Date endDate) {
        List<Inquiry> vehicleList=null;
        try {
            Date firstDay12 = DateUtils.addDays(firstDate, 1);
            Date endDate12 = DateUtils.addDays(endDate, 1);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = simpleDateFormat.format(firstDay12);
            String date2 = simpleDateFormat.format(endDate12);
            String Status = "4";
            //  String date1 = "2020-05-15";
            //  String date2 = "2020-05-16";

            final String sql = "SELECT * FROM Inquiry where firstDate  BETWEEN '"+ date1 +"' AND '"+ date2 +"' AND lastDate  BETWEEN '"+ date1 +"' AND '"+ date2 +"' AND status = '"+ Status +"' ";
            //  final String sql = "SELECT * FROM payment where (paymentDate>='2020-05-22')  AND (paymentDate>='2020-11-10')  ";

            vehicleList = jdbcTemplate.query(sql, new BookingRepositoryService.BookingRowMapper());

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


    public  List<Inquiry> getSearchCustomerDateBetWeen(Date firstDate, Date endDate,String contactNo) {
        List<Inquiry> vehicleList=null;
        try {
            Date firstDay12 = DateUtils.addDays(firstDate, 1);
            Date endDate12 = DateUtils.addDays(endDate, 1);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = simpleDateFormat.format(firstDay12);
            String date2 = simpleDateFormat.format(endDate12);
            String Status = "4";
            //  String date1 = "2020-05-15";
            //  String date2 = "2020-05-16";

            final String sql = "SELECT * FROM Inquiry where firstDate  BETWEEN '"+ date1 +"' AND '"+ date2 +"' AND lastDate  BETWEEN '"+ date1 +"' AND '"+ date2 +"' AND status = '"+ Status +"' AND contactNo = '"+ contactNo +"'";
            //  final String sql = "SELECT * FROM payment where (paymentDate>='2020-05-22')  AND (paymentDate>='2020-11-10')  ";

            vehicleList = jdbcTemplate.query(sql, new BookingRepositoryService.BookingRowMapper());

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

}

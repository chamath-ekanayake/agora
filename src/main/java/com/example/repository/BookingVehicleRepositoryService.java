package com.example.repository;


import com.example.domain.BookingVehicle;
import com.example.other.SequenceGenerator;
import com.example.service.daoService.VehicleBookingDaoService;
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

@Repository("mysqlVehicleBooking")
public class BookingVehicleRepositoryService implements VehicleBookingDaoService {
    private final Logger log = LoggerFactory.getLogger(VehicleRepositoryService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    SequenceGenerator seq = new SequenceGenerator();
    private static class BookingVehicleRowMapper implements RowMapper<BookingVehicle> {

        @Override
        public BookingVehicle mapRow(ResultSet resultSet, int i) throws SQLException {
            BookingVehicle bookingVehicle = new BookingVehicle();
            bookingVehicle.setId(resultSet.getString("id"));
            bookingVehicle.setDriverName(resultSet.getString("driverName"));
            bookingVehicle.setDriverId(resultSet.getString("driverId"));
            bookingVehicle.setVehicleNum(resultSet.getString("vehicleNum"));
            bookingVehicle.setVehicleType(resultSet.getString("vehicleType"));
            bookingVehicle.setStartDate(resultSet.getDate("startDate"));
            bookingVehicle.setEndDate(resultSet.getDate("endDate"));
            bookingVehicle.setStatus(resultSet.getInt("status"));
            bookingVehicle.setInquiryId(resultSet.getString("inquiryId"));
            return bookingVehicle;
        }
    }

    public  List<BookingVehicle> getAvailableVehicleList(Date firstDate, Date endDate,String vehicleType) {


        final String sql = "SELECT * FROM bookingVehicle where vehicleType = ? AND startDate  BETWEEN ? AND ? ";
        List<BookingVehicle> vehicleList=null;


try{
            vehicleList = jdbcTemplate.query(sql, new Object[]{vehicleType,firstDate,endDate}, new BookingVehicleRowMapper());

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
    public void getInsertVehicleBooking(BookingVehicle bookingVehicle) {
        try {
           // bookingVehicle.setId(SequenceGenerator.generateSequence("bookingVehicle"));
                final String sql = "INSERT INTO bookingVehicle (id,driverName,driverId,vehicleNum,vehicleType,startDate,endDate,status,inquiryId) VALUES (?,?,?,?,?,?,?,?,?)";
                final String id = bookingVehicle.getId();
                final String driverName = bookingVehicle.getDriverName();
                final String driverId = bookingVehicle.getDriverId();
                final String vehicleNum = bookingVehicle.getVehicleNum();
                final String vehicleType = bookingVehicle.getVehicleType();
                final Date startDate = bookingVehicle.getStartDate();
                final Date endDate = bookingVehicle.getEndDate();
                final Integer status = bookingVehicle.getStatus();
                final String inquiryId = bookingVehicle.getInquiryId();

                jdbcTemplate.update(sql, new Object[]{id, driverName, driverId, vehicleNum, vehicleType,startDate,endDate,status,inquiryId});

        } catch (Exception e) {
            log.info("log info @@@@@@@@@@ bookingVehicle  insert Repository @@@@@@@@@@" + e);
        }

    }

    public List<BookingVehicle> getFindAll() {
        // SELECT column_name(s) FROM table_name
        final String sql = "SELECT * FROM bookingVehicle";
        List<BookingVehicle> userList = jdbcTemplate.query(sql, new BookingVehicleRowMapper());
        return userList;
    }

    public  List<BookingVehicle> getAvailableVehicle(Date firstDate, Date endDate,String vehicleType) {
        Date startDate = DateUtils.addDays(firstDate, 1);
        Date endDate2 = DateUtils.addDays(endDate, 1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate1 = simpleDateFormat.format(startDate);
        String stringDate2 = simpleDateFormat.format(endDate2);

Integer status = 1;

        final String sql = "SELECT * FROM bookingVehicle where status= '"+ status +"' AND vehicleType= '"+ vehicleType +"' AND   (startDate  BETWEEN '"+ stringDate1 +"' AND '"+ stringDate2 +"') AND (endDate  BETWEEN '"+ stringDate1 +"' AND '"+ stringDate2 +"')";

        List<BookingVehicle> vehicleList=null;


        try{
            vehicleList = jdbcTemplate.query(sql,  new BookingVehicleRowMapper());

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
    public BookingVehicle getupdateData(String id,String status) {
        final String sql = "UPDATE bookingVehicle set status =? where id =? ";
        try {

            jdbcTemplate.update(sql, new Object[]{status,id});

        } catch (Exception e) {
            log.info("log info @@@@@@@@@@ Inquiry Repository @@@@@@@@@@" + e);
        }

        return null;
    }


}

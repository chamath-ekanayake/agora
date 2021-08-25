package com.example.repository;


import com.example.domain.Vehicle;
import com.example.other.SequenceGenerator;
import com.example.service.daoService.VehicleDaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository("mysqlVehicle")
public class VehicleRepositoryService implements VehicleDaoService {
    private final Logger log = LoggerFactory.getLogger(VehicleRepositoryService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    SequenceGenerator seq = new SequenceGenerator();
    private static class VehicleRowMapper implements RowMapper<Vehicle> {

        @Override
        public Vehicle mapRow(ResultSet resultSet, int i) throws SQLException {
            Vehicle inquiry = new Vehicle();
            inquiry.setId(resultSet.getString("id"));
            inquiry.setContactNo(resultSet.getString("contactNo"));
            inquiry.setDriverName(resultSet.getString("driverName"));
            inquiry.setDriverNIC(resultSet.getString("driverNIC"));
            inquiry.setEmail(resultSet.getString("email"));
            inquiry.setSeatNum(resultSet.getString("seatNum"));
            inquiry.setVehicleAddress(resultSet.getString("vehicleAddress"));
            inquiry.setVehicleJointDate(resultSet.getDate("vehicleJointDate"));
            inquiry.setVehicleType(resultSet.getString("vehicleType"));
            inquiry.setVehicleModel(resultSet.getString("vehicleModel"));
            inquiry.setVehicleNum(resultSet.getString("vehicleNum"));

            return inquiry;
        }
    }

    @Override
    public void insertVehicle(Vehicle vehicle) {
        try {
            //vehicle.setId(SequenceGenerator.generateSequence("vehicle"));
            final String sql = "INSERT INTO vehicle (id,email,driverName,vehicleJointDate,vehicleNum,vehicleModel,vehicleType,seatNum,contactNo,vehicleAddress,driverNIC) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            final String id = vehicle.getId();
            final String contactNo = vehicle.getContactNo();
            final String email = vehicle.getEmail();
            final String driverName = vehicle.getDriverName();
            final String driverNIC = vehicle.getDriverNIC();
            final String seatNum = vehicle.getSeatNum();
            final String vehicleAddress = vehicle.getVehicleAddress();
            final Date vehicleJointDate = vehicle.getVehicleJointDate();
            final String vehicleType = vehicle.getVehicleType();
            final  String vehicleModel = vehicle.getVehicleModel();
            final String vehicleNum = vehicle.getVehicleNum();
            jdbcTemplate.update(sql, new Object[]{id,email,driverName,vehicleJointDate,vehicleNum,vehicleModel,vehicleType,seatNum,contactNo,vehicleAddress,driverNIC});

        } catch (Exception e) {
            log.info("log info @@@@@@@@@@ Inquiry Repository @@@@@@@@@@" + e);
        }

    }

    public List<Vehicle> getAvailableVehicle() {
        // SELECT column_name(s) FROM table_name
        final String sql = "SELECT * FROM vehicle where status=1 ";
        List<Vehicle> vehicleList = jdbcTemplate.query(sql, new VehicleRowMapper());
        return vehicleList;
    }

    public List<Vehicle> getFindAll() {
        // SELECT column_name(s) FROM table_name
        final String sql = "SELECT * FROM vehicle";
        List<Vehicle> vehicleAll = jdbcTemplate.query(sql, new VehicleRowMapper());
        return vehicleAll;
    }

    public Vehicle selectedVehicle(String driverName) {
        // SELECT column_name(s) FROM table_name
        final String sql = "SELECT * FROM vehicle where driverName= ? ";
        Vehicle vehicleList = jdbcTemplate.queryForObject(sql,new Object[]{driverName}, new VehicleRowMapper());
        return vehicleList;
    }

    @Override
    public List<Vehicle> isDriverNameSearch(String driverName) {
        // SELECT column_name(s) FROM table_name
        final String sql = "SELECT * FROM vehicle WHERE driverName= Like   '%' + ? + '%' ";
        List<Vehicle> userList = jdbcTemplate.query(sql,new Object[]{driverName},new VehicleRowMapper());
        return userList;
    }
    public List<Vehicle> getAllVehicleForType(String vehicleType) {
        // SELECT column_name(s) FROM table_name
        final String sql = "SELECT * FROM vehicle where vehicleType = '"+ vehicleType +"'";
        List<Vehicle> vehicleAll = jdbcTemplate.query(sql, new VehicleRowMapper());
        return vehicleAll;
    }
}

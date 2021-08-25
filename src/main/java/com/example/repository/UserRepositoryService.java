package com.example.repository;


import com.example.domain.User;
import com.example.other.SequenceGenerator;
import com.example.service.UserService;
import com.example.service.daoService.UserDaoService;
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

/**
 * Spring Data MongoDB repository for the User entity.
 */
@Repository("mysql")
public class UserRepositoryService implements UserDaoService {
    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    SequenceGenerator seq = new SequenceGenerator();

    private static class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setUserId(resultSet.getString("userId"));
            user.setUserName(resultSet.getString("userName"));
            user.setEmail(resultSet.getString("email"));
            user.setAddress(resultSet.getString("address"));
            user.setContactNo(resultSet.getInt("contactNo"));
            user.setNic(resultSet.getString("nic"));
            user.setDob(resultSet.getDate("dob"));
            return user;
        }
    }

    // @Override
    public List<User> getAllUsers() {
        // SELECT column_name(s) FROM table_name
        final String sql = "SELECT * FROM users";
        List<User> userList = jdbcTemplate.query(sql, new UserRowMapper());
        return userList;
    }

    @Override
    public void insertUsersToDb(User user) {
        try {

            if (!isUserExit(user.getEmail())) {
                // user.setUserId(SequenceGenerator.generateSequence("user"));
                final String sql = "INSERT INTO users (userId,username,email,address,contactNo,nic,dob) VALUES (?,?,?,?,?,?,?)";
                final String userId = user.getUserId();
                final String userName = user.getUserName();
                final String email = user.getEmail();
                final String address = user.getAddress();
                final int contactNo = user.getContactNo();
                final String nic = user.getNic();
                final Date dob = user.getDob();

                jdbcTemplate.update(sql, new Object[]{userId, userName, email, address, contactNo, nic, dob});
            } else {
                System.out.println("Invalid");
            }


        } catch (Exception e) {
            log.info("log info @@@@@@@@@@ user Repository @@@@@@@@@@" + e);
        }

    }

    @Override
    public List<User> selectUsers(int contactNo) {
        // SELECT column_name(s) FROM table_name
        final String sql = "SELECT * FROM users WHERE contactNo=contactNo; ";
        List<User> userList = jdbcTemplate.query(sql, new UserRowMapper());
        return userList;
    }

    @Override
    public boolean isUserExit(String email) {
        //dont delete this code
        boolean result = false;
        final String sql = "SELECT count(*) FROM users WHERE email = ?";
        int count = (Integer) jdbcTemplate.queryForObject(sql, new Object[]{email}, Integer.class);
        if (count > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public User isEmailExit(String email) {

        final String sql = "SELECT * FROM users WHERE email = ?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, new Object[]{email}, new UserRowMapper());
        } catch (Exception ex) {
            log.info("log info @@@@@@@@@@ user Repository @@@@@@@@@@" + ex);
        }
        return user;
    }

    @Override
    public void updateUsersToDb(User user) {
        try {
            final String sql = "UPDATE users set username=? ,email =?, address=?,contactNo=?,nic=?,dob=? where userId =?";
            final String userId = user.getUserId();
            final String userName = user.getUserName();
            final String email = user.getEmail();
            final String address = user.getAddress();
            final int contactNo = user.getContactNo();
            final String nic = user.getNic();
            final Date dob = user.getDob();
            jdbcTemplate.update(sql, new Object[]{userName, email, address, contactNo, nic, dob, userId});
        } catch (Exception e) {
            log.info("log info @@@@@@@@@@ user Repository @@@@@@@@@@" + e);
        }
    }


    @Override
    public User selectUser(String email) {
        final String sql = "SELECT * FROM users WHERE email=email; ";
        User userList = (User) jdbcTemplate.query(sql, new UserRowMapper());
        return userList;
    }

    @Override
    public void deleteUsersToDb(String email) {
        try {
            final String sql = "delete from users where email=email";
            jdbcTemplate.update(sql, new UserRowMapper());
        } catch (Exception e) {
            log.info("log info @@@@@@@@@@ user Repository @@@@@@@@@@" + e);
        }
    }

    @Override
    public User updateLogoutUsersToDb(User user) {
        try {
            final String sql = "UPDATE Login set status=?  where name =?";
            final String userName = user.getUserName();
            final String status = "Active";
            jdbcTemplate.update(sql, new Object[]{userName, status,});
        } catch (Exception e) {
            log.info("log info @@@@@@@@@@ user Repository @@@@@@@@@@" + e);
        }
        return user;
    }

    @Override
    public User isContactNo(String contactNo) {

        final String sql = "SELECT * FROM users WHERE contactNo = ?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, new Object[]{contactNo}, new UserRowMapper());
        } catch (Exception ex) {
            log.info("log info @@@@@@@@@@ user Repository @@@@@@@@@@" + ex);
        }
        return user;
    }

    @Override
    public List<User> isContactNoUsers(String contactNo) {
        // SELECT column_name(s) FROM table_name
        final String sql = "SELECT * FROM users WHERE contactNo=? ";
        List<User> userList = jdbcTemplate.query(sql, new Object[]{contactNo}, new UserRowMapper());
        return userList;
    }

    @Override
    public void deleteUser(String userId) {
        try {
            final String sql = "DELETE FROM users WHERE userId=?";
            jdbcTemplate.update(sql, new Object[]{userId});
        } catch (Exception e) {
            log.info("log info @@@@@@@@@@ user Repository @@@@@@@@@@" + e);
        }
    }


    public User getTheLastRecord() {
        // SELECT column_name(s) FROM table_name
        final String sql = "SELECT * FROM users ORDER BY userId DESC LIMIT 1";

        User userList = jdbcTemplate.queryForObject(sql, new UserRowMapper());
        return userList;
    }

    @Override
    public boolean isContactAvailable(String contactNo) {
        return false;
    }

}

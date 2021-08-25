package com.example.service;


import com.example.domain.DTOClass.DashBoardDTO;
import com.example.domain.Inquiry;
import com.example.domain.User;
import com.example.domain.Vehicle;
import com.example.service.ThreadService.TestThreadService;
import com.example.service.daoService.BookingDaoService;
import com.example.service.daoService.PaymetDaoService;
import com.example.service.daoService.UserDaoService;
import com.example.service.daoService.VehicleDaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing users.
 */

@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);
    User user = new User();

    @Qualifier("mysql")
    @Autowired
    private UserDaoService userDaoService;

    @Autowired
    @Qualifier("mysqlVehicle")
    private VehicleDaoService vehicleDaoService;

    @Autowired
    @Qualifier("mysqlInquiry")
    private BookingDaoService bookingDaoService;


    @Autowired
    @Qualifier("mysqlPayment")
    private PaymetDaoService paymetDaoService;

    public User getUser(User user)  {

        List<User> selectUsers =userDaoService.selectUsers(user.getContactNo());


        if(selectUsers!=null){

          //  List<User> listCount = userDaoService.getAllUsers();
            User lastRecord = userDaoService.getTheLastRecord();
            String userlastId = lastRecord.getUserId();
           // final String input = "0-123-abc-456-xyz-789";
            final String result = stripNonDigits(userlastId);
            int i=Integer.parseInt(result);
            Integer count = i+1;
            user.setUserId("userId"+count);
            this.userDaoService.insertUsersToDb(user);
            return user;
        }
        return user;
    }

    public static String stripNonDigits(
            final CharSequence input /* inspired by seh's comment */){
        final StringBuilder sb = new StringBuilder(
                input.length() /* also inspired by seh's comment */);
        for(int i = 0; i < input.length(); i++){
            final char c = input.charAt(i);
            if(c > 47 && c < 58){
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public List<User> findAll() throws IOException {

        List<User>results = this.userDaoService.getAllUsers();
        log.info("@@@@@@@@@@@@ ArrayList @@@@@@@@@@@"+results);
        return results;
    }

    public User getEmail(String email)  {
        User results= userDaoService.isEmailExit(email);
        return results;
    }

    public User updateUser(User user)  {
            this.userDaoService.updateUsersToDb(user);
            return user;
    }
    public void threadTest ()  {

        for (int i = 0; i == 0; ) {
            try {
                TestThreadService testThreadService=new TestThreadService();
                testThreadService.run();
            }
            catch(Exception e) {
                System.err.println(" main error :"+e.getMessage());
            }
        }
    }
    public void deleteUser(String email)  {
        this.userDaoService.deleteUsersToDb(email);
    }

    public  User getLogoutDetails(User user)
    {
        this.userDaoService.updateLogoutUsersToDb(user);
        return  user;
    }

    public User getContactDetails(String contactNo)  {
        User results= userDaoService.isContactNo(contactNo);
        return results;
    }

    public List<DashBoardDTO> getDashBoardService()  {
        List<DashBoardDTO> results =new ArrayList<>();
        DashBoardDTO dashBoardDTO = new DashBoardDTO();

        List<User> AllUsers =userDaoService.getAllUsers();
        List<Vehicle> AllVehicle =vehicleDaoService.getFindAll();
        List<Inquiry> AllpendingInquiry=this.bookingDaoService.getAllPending();
        List<Inquiry> AllPayment =this.bookingDaoService.getAllBooking("2");
        Integer userCount= AllUsers.size();
        Integer vehicleCount = AllVehicle.size();
        Integer inquiryCount = AllpendingInquiry.size();
        Integer paymentCount = AllPayment.size();


        dashBoardDTO.setCustomerCount(userCount);
        dashBoardDTO.setVehicleCount(vehicleCount);
        dashBoardDTO.setInquiryCount(inquiryCount);
        dashBoardDTO.setPaymentCount(paymentCount);

        results.add(dashBoardDTO);

        return results;
    }

    public List<User> getContactDetailsusers(String contactNo)  {
        List<User> results= userDaoService.isContactNoUsers(contactNo);
        return results;
    }

    public void getdeleteUser(String userId)  {
        this.userDaoService.deleteUser(userId);
    }

}








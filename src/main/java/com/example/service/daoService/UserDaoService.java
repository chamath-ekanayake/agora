package com.example.service.daoService;

import com.example.domain.User;

import java.util.List;

public interface UserDaoService {

    void insertUsersToDb(User user);
    List<User> getAllUsers();
    List<User> selectUsers(int contactNo);
    User isEmailExit(String email);
    void updateUsersToDb(User user);
    boolean isUserExit(String email);
    User selectUser(String email);
    void deleteUsersToDb(String email);
    User updateLogoutUsersToDb(User user);
    User isContactNo(String contactNo);
    List<User> isContactNoUsers(String contactNo);
    void deleteUser(String userId);
    User getTheLastRecord();
    boolean isContactAvailable(String contactNo);
}

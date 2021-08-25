package com.example.service.ThreadService;


import com.example.service.UserService;
import com.example.service.daoService.UserDaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestThreadService implements Runnable {
    private UserDaoService userDaoService;

    private final Logger log = LoggerFactory.getLogger(UserService.class);


    @Override
    public void run(){
try {
       log.info("@@@@@____TEST_THREAD_____@@@@@@");
            Thread.sleep((long) (1 * 60000));
        }catch(Exception e){}
    }

//    public TestThreadService(String email){
//        User infoUser = userDaoService.selectUser(email);
//    }


}

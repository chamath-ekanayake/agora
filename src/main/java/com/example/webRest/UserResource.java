package com.example.webRest;


import com.example.domain.DTOClass.DashBoardDTO;
import com.example.domain.DTOClass.Token_GenerateDTO;
import com.example.domain.User;
import com.example.other.DynamicKey;
import com.example.service.UserService;
import com.example.webRest.util.HeaderUtil;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
@Api(value = "User Resource REST Endpoint", description = "Shows the user info")
public class UserResource {
    private final Logger log = LoggerFactory.getLogger(UserService.class);


    public UserResource() {

    }
    @Autowired
    UserService userService;



    @GetMapping("/users-all")
    public ResponseEntity<List<User>> getAll () throws IOException {
        List<User> userList=null;
        try {

            userList = userService.findAll();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<User> getUserRegister(@Valid @RequestBody User user) throws URISyntaxException, ParseException, Exception {
        log.info("REST request to save user : {}", user);
        try {


            User result = userService.getUser(user);
            //add default bankdetails,begin
            //basicDataService.addBank(result.getUserId());
            //add default bankdetails,end
            return ResponseEntity.created(new URI("/api/user/" + result.getEmail()))
                    .headers(HeaderUtil.createEntityCreationAlert(user.getUserName(), result.getEmail()))
                    .body(result);
        }
        catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/email")
    ResponseEntity<User> getEmail(String email)throws IOException  {
        User user = null;
        log.info("REST request to  email : {}", email);

        try {

               user = userService.getEmail(email);
             }
             catch (Exception ex){ex.getMessage();}
            return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/userUpdate")
    public ResponseEntity<User> getUpdateRegister(@Valid @RequestBody User user) throws URISyntaxException, ParseException, Exception {
        log.info("REST request to Update user : {}", user);
        try {

            User result = userService.updateUser(user);
            return ResponseEntity.created(new URI("/api/user/" + result.getEmail()))
                    .headers(HeaderUtil.createEntityCreationAlert(user.getUserName(), result.getEmail()))
                    .body(result);
        }
        catch(Exception e){
            e.printStackTrace();
            throw e; }
    }
    @GetMapping("/test-Thread")
    public void testThread ()  {

        userService.threadTest();
    }
    @DeleteMapping("/user/{email}")
    public ResponseEntity<Void> deleteUser (@PathVariable String email){
        log.debug("REST request to delete user : {}", email);
        try {

           userService.deleteUser(email);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(email, email)).build();
    }

    @PostMapping("/logoutService")
    public ResponseEntity<User> getLogout(@Valid @RequestBody User user) throws URISyntaxException, ParseException, Exception {

        log.info("REST recontactNoquest to Update user : {}", user);
        try {

            User result = userService.getLogoutDetails(user);
            return ResponseEntity.created(new URI("/api/user/" + result.getEmail()))
                    .headers(HeaderUtil.createEntityCreationAlert(user.getUserName(), result.getEmail()))
                    .body(result);
        }
        catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/contactNo/{contactNo}")
    ResponseEntity<User> getContactSearch(@PathVariable("contactNo") final String contactNo)throws IOException  {
        log.info("REST recontactNoquest to ContactNo  : {}", contactNo);

        User user = null;
        try {
            user = userService.getContactDetails(contactNo);
        }
        catch (Exception ex){ex.getMessage();}
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/getDashBoardDetails")
    public ResponseEntity<List<DashBoardDTO>> getDashBoardDetails () throws IOException {
        List<DashBoardDTO> DashBoard= null;
        try {
            DashBoard = userService.getDashBoardService();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        //Token_GenerateDTO
        return new ResponseEntity<>(DashBoard, HttpStatus.OK);
    }

    @GetMapping("/user/contactNo/{contactNo}")
    public ResponseEntity<List<User>> getContactSearchUser(@PathVariable("contactNo") final String contactNo)throws IOException  {
        log.info("REST recontactNoquest to ContactNo  : {}", contactNo);

        List<User> userList=null;
        try {
            userList = userService.getContactDetailsusers(contactNo);
        }
        catch (Exception ex){ex.getMessage();}
        return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
    }


    @DeleteMapping("/user/userId/{userId}")
    public ResponseEntity<List<User>> getDeleteCustomer(@PathVariable("userId") final String userId)throws IOException  {
        log.info("REST recontactNoquest to getDeleteCustomer  : {}", userId);
            try {

                userService.getdeleteUser(userId);
            }
            catch(Exception ex)
            {
                System.out.println(ex.getMessage());
            }
            return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(userId, userId)).build();
        }

//
//    @GetMapping("/token")
//    public ResponseEntity<String> getTokenWithAgora()throws IOException  {
//
//        log.info("REST request to  email : {}", "test_token");
//        String token=null;
//        try {
//
//
//
//        }
//        catch (Exception ex){ex.getMessage();}
//        return new ResponseEntity<String>(token, HttpStatus.OK);
//    }


    @GetMapping("/token")
    public ResponseEntity<List<Token_GenerateDTO>> getTokenWithAgora () throws IOException {
        List<Token_GenerateDTO> token_generateDTO = new ArrayList<>();
        Token_GenerateDTO tokenDTO = new Token_GenerateDTO();
        String token=null;
        try {


            String appID = "cee295f7558f401fa703cf7d1df24098";
            String appCertificate = "92d380737fb64574979557f6eb128511";
            String channel = "test2";
            int ts = (int)(new Date().getTime()/1000);
            int r = new Random().nextInt();
            long uid = 2882341273L;
            int expiredTs = 0;

            token =   DynamicKey.generate(appID,appCertificate,channel,ts,r);

            tokenDTO.setAgora_token(token);
            token_generateDTO.add(tokenDTO);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        //Token_GenerateDTO
        return new ResponseEntity<>(token_generateDTO, HttpStatus.OK);
    }
}

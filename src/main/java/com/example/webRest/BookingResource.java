package com.example.webRest;

import com.example.domain.DTOClass.Payment_ReportDTO;
import com.example.domain.Inquiry;
import com.example.domain.User;
import com.example.service.BookingService;
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
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
@Api(value = "Booking Resource REST Endpoint", description = "Shows the Booking info")
public class BookingResource {
    private final Logger log = LoggerFactory.getLogger(BookingResource.class);


    public BookingResource() {

    }

    @Autowired
    BookingService bookingService;

    @PostMapping("/inquiry")
    public ResponseEntity<Inquiry> getInquiryInsert(@Valid @RequestBody Inquiry inquiry) throws URISyntaxException, ParseException, Exception {
        log.info("REST request to save inquiry : {}", inquiry);
        try {

            Inquiry result = bookingService.getPostInquiry(inquiry);
            return ResponseEntity.created(new URI("/api/inquiry/" + result.getContactNo()))
                    .headers(HeaderUtil.createEntityCreationAlert(inquiry.getUserName(), result.getEmail()))
                    .body(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

//    @GetMapping("/inquiry-pending-All")
//    public ResponseEntity<Page<Inquiry>> getAll(Pageable pageable) throws IOException {
//        Page<Inquiry> InquiryList = null;
//        try {
//
//            InquiryList = bookingService.findAllPending(pageable);
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//        return new ResponseEntity<>(InquiryList, HttpStatus.OK);
//    }

    @GetMapping("/inquiry-pending-All")
    public ResponseEntity<List<Inquiry>> getAll() throws IOException {
        List<Inquiry> InquiryList = null;
        try {

            InquiryList = bookingService.findAllPending();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>(InquiryList, HttpStatus.OK);
    }

    @GetMapping("/inquiry/{inquiryId}/{price}/{vehicle}")
    public ResponseEntity<Void> getAcceptHire(@PathVariable("inquiryId") final String inquiryId,@PathVariable("price") final String price,@PathVariable("vehicle") final String vehicle)throws IOException  {
        log.info("REST recontactNoquest to inquiryId  : {}",vehicle);


        try {
            bookingService.getUpdate(inquiryId,price,vehicle);

        }catch (Exception ex){
            ex.getMessage();
        }

        //     log.info("REST  to Return List  : {}", driverName);

        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(inquiryId, price)).build();

    }

    @GetMapping("/inquiryReject/{inquiryId}")
    public ResponseEntity<Void> getRejectHire(@PathVariable("inquiryId") final String inquiryId)throws IOException  {
        log.info("REST recontactNoquest to inquiryId  : {}",inquiryId);


        try {
            bookingService.getRejectInquiry(inquiryId);

        }catch (Exception ex){
            ex.getMessage();
        }

        //     log.info("REST  to Return List  : {}", driverName);

        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(inquiryId,inquiryId)).build();

    }

    @GetMapping("/inquiryContactSearch/{contactNo}")
    ResponseEntity<List<Inquiry>> getContactSearch(@PathVariable("contactNo") final String contactNo)throws IOException  {
        log.info("REST recontactNoquest to inquiryContactSearch  : {}", contactNo);

        List<Inquiry> inquiry = null;
        try {
            inquiry = bookingService.getContactSearch(contactNo);
        }
        catch (Exception ex){ex.getMessage();}
        return new ResponseEntity<>(inquiry, HttpStatus.OK);
    }

    @GetMapping("/inquiry-Booking-All")
    public ResponseEntity<List<Inquiry>> getBookingAll() throws IOException {
        List<Inquiry> InquiryList = null;
        try {

            InquiryList = bookingService.findAllBooking();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>(InquiryList, HttpStatus.OK);
    }


    @GetMapping("/BookingContactSearch/{contactNo}")
    ResponseEntity<List<Inquiry>> getContactBookingSearch(@PathVariable("contactNo") final String contactNo)throws IOException  {
        log.info("REST recontactNoquest to inquiryBookingContactSearch  : {}", contactNo);

        List<Inquiry> inquiry = null;
        try {
            inquiry = bookingService.getContactBookingSearch(contactNo);
        }
        catch (Exception ex){ex.getMessage();}
        return new ResponseEntity<>(inquiry, HttpStatus.OK);
    }


    @GetMapping("/getVehicleBug")
    public void getVehicleBug() throws IOException {
      //  List<Inquiry> InquiryList = null;
        try {

            bookingService.getBugFix();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
       // return new ResponseEntity<>(InquiryList, HttpStatus.OK);
    }

    @GetMapping("/getDateBugFix")
    public void getRimberDateBug() throws IOException {
        //  List<Inquiry> InquiryList = null;
        try {

            bookingService.datePatch();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        // return new ResponseEntity<>(InquiryList, HttpStatus.OK);
    }

    @GetMapping("/inquiry-Reminder_list")
    public ResponseEntity<List<Inquiry>> getReminderList() throws IOException {
        List<Inquiry> InquiryList = null;
        try {

            InquiryList = bookingService.findReminderList();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>(InquiryList, HttpStatus.OK);
    }
    @GetMapping("/inquiry-Reminder_list_Today")
    public ResponseEntity<List<Inquiry>> getReminderList_Today() throws IOException {
        List<Inquiry> InquiryList = null;
        try {

            InquiryList = bookingService.getFindTodayRemaind();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>(InquiryList, HttpStatus.OK);
    }

    @GetMapping("/inquiry-today-running")
    public ResponseEntity<List<Inquiry>> getToday_Running() throws IOException {
        List<Inquiry> InquiryList = null;
        try {
            InquiryList = bookingService.getRunningHire();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>(InquiryList, HttpStatus.OK);
    }


    @GetMapping("/Remind_Extend/{inquiryId}/{reminderDate}/{charge}")
    public ResponseEntity<List<Inquiry>> getRemindDateExtend(@PathVariable("inquiryId") final String inquiryId, @PathVariable("reminderDate") final Date reminderDate, @PathVariable("charge") final String charge)throws IOException {

        log.info("REST PaymentResource to Reminder Date  : {}",reminderDate);
        List<Inquiry> reminderList = null;
        reminderList= bookingService.getReminderDateExtend(inquiryId,reminderDate,charge);
        log.info("REST  to Return paymentList List  : {}", reminderList);
        return new ResponseEntity<List<Inquiry>>(reminderList, HttpStatus.OK);

    }

    @GetMapping("/inquiry/{inquiryId}/{price}")
    public ResponseEntity<Void>getbookingStageReminder(@PathVariable("inquiryId") final String inquiryId, @PathVariable("price") final String price)throws IOException {
        log.info("REST recontactNoquest to inquiryId  : {}",inquiryId);
        try {
            bookingService.getbookingStageReminder(inquiryId,price);

        }catch (Exception ex){
            ex.getMessage();
        }
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(inquiryId,inquiryId)).build();
    }
    @GetMapping("/inquiry/{inquiryId}/{price}/{vehicle}/{pickUpTime}/{advance}")
    public ResponseEntity<Void> getAcceptHire(@PathVariable("inquiryId") final String inquiryId,@PathVariable("price") final String price,@PathVariable("vehicle") final String vehicle,@PathVariable("pickUpTime") final String pickUpTime,@PathVariable("advance") final String advance)throws IOException  {
        log.info("REST recontactNoquest to inquiryId  : {}",vehicle);




        try {
            bookingService.getUpdate2(inquiryId,price,vehicle,pickUpTime,advance);

        }catch (Exception ex){
            ex.getMessage();
        }

        //     log.info("REST  to Return List  : {}", driverName);

        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(inquiryId, price)).build();

    }

    @GetMapping("/inquiryId/{inquiryId}")
    ResponseEntity<Inquiry> getinquiryIdSearch(@PathVariable("inquiryId") final String inquiryId)throws IOException  {
        log.info("REST recontactNoquest to inquiryId  : {}", inquiryId);

        Inquiry inquiry = null;
        try {
            inquiry = bookingService.getInquiryIdDetails(inquiryId);
        }
        catch (Exception ex){ex.getMessage();}
        return new ResponseEntity<>(inquiry, HttpStatus.OK);
    }

    @PostMapping("/UpdateInquiry")
    public ResponseEntity<Inquiry> getInquiryUpdate(@Valid @RequestBody Inquiry inquiry) throws URISyntaxException, ParseException, Exception {
        log.info("REST request to update inquiry : {}", inquiry);
        try {

            Inquiry result = bookingService.updateBookingStage(inquiry);
            return ResponseEntity.created(new URI("/api/UpdateInquiry/" + result.getContactNo()))
                    .headers(HeaderUtil.createEntityCreationAlert(inquiry.getUserName(), result.getEmail()))
                    .body(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
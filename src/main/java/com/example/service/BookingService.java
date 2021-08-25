package com.example.service;

import com.example.domain.BookingVehicle;
import com.example.domain.Inquiry;
import com.example.domain.User;
import com.example.domain.Vehicle;
import com.example.service.daoService.BookingDaoService;
import com.example.service.daoService.UserDaoService;
import com.example.service.daoService.VehicleBookingDaoService;
import com.example.service.daoService.VehicleDaoService;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import static com.example.service.UserService.stripNonDigits;

/**
 * Service class for managing users.
 */

@Service
public class BookingService {

    private final Logger log = LoggerFactory.getLogger(BookingService.class);
    Inquiry inquiry = new Inquiry();

    @Qualifier("mysqlInquiry")
    @Autowired
    private BookingDaoService bookingDaoService;

    @Autowired
    @Qualifier("mysql")
    private UserDaoService userDaoService;

    @Autowired
    @Qualifier("mysqlVehicleBooking")
    private VehicleBookingDaoService vehicleBookingDaoService;

    @Autowired
    @Qualifier("mysqlVehicle")
    private VehicleDaoService vehicleDaoService;


    public Inquiry getPostInquiry(Inquiry inquiry) {

        log.info("REST request to service 1 : {}", inquiry);
        User userDetails = userDaoService.isContactNo(inquiry.getContactNo());

        Boolean availableContact = userDaoService.isContactAvailable(inquiry.getContactNo());
        log.info("REST request to service 2 : {}", inquiry.getUserName());

        if (!availableContact) {
            log.info("REST request to service 3 : {}", inquiry.getUserName());

            int contact = Integer.parseInt(inquiry.getContactNo());
            User user = new User();
            user.setUserName(inquiry.getUserName());
            user.setAddress(inquiry.getAddress());
            user.setContactNo(contact);
            Date date = new Date();

            Date firstDay = DateUtils.addDays(date, 1);
            user.setDob(firstDay);
            user.setEmail(inquiry.getEmail());
            List<User> selectUsers = userDaoService.selectUsers(user.getContactNo());
            if (selectUsers != null) {
                log.info("REST request to service 4 : {}", inquiry.getUserName());

                //  List<User> listCount = userDaoService.getAllUsers();
                User lastRecord = userDaoService.getTheLastRecord();
                String userlastId = lastRecord.getUserId();
                // final String input = "0-123-abc-456-xyz-789";
                final String result = stripNonDigits(userlastId);
                int i = Integer.parseInt(result);
                Integer count = i + 1;
                user.setUserId("userId" + count);
                this.userDaoService.insertUsersToDb(user);
            }
            inquiry.setUserName(inquiry.getUserName());
            inquiry.setEmail(inquiry.getEmail());
            inquiry.setNic(inquiry.getNic());
            inquiry.setAddress(inquiry.getAddress());

        } else {
            log.info("REST request to service 5 : {}", inquiry.getUserName());

            inquiry.setUserName(userDetails.getUserName());
            inquiry.setEmail(userDetails.getEmail());
            inquiry.setNic(userDetails.getNic());
            inquiry.setAddress(userDetails.getAddress());
        }

        log.info("REST request to service 6 : {}", inquiry.getUserName());


        Date reminderDate = null;

        if (inquiry.getReminderDate() != null) {
            log.info("REST request to service 7 : {}", inquiry.getUserName());

            inquiry.setStatus("8");
            Date Date3 = inquiry.getReminderDate();
            reminderDate = DateUtils.addDays(Date3, 1);

        } else {
            log.info("REST request to service 8 : {}", inquiry.getUserName());

            inquiry.setStatus("1");
        }
        log.info("REST request to service inquiry 9 : {}", inquiry.getStatus());

        Date Date1 = inquiry.getFirstDate();
        Date Date2 = inquiry.getLastDate();

        Date firstDay = DateUtils.addDays(Date1, 1);
        Date lastDay = DateUtils.addDays(Date2, 1);

        List<Inquiry> AllInquiry = bookingDaoService.getAll();

        Integer count = AllInquiry.size() + 1;

        inquiry.setInquiryId("Inquiry2020-10" + count);
        inquiry.setFirstDate(firstDay);
        inquiry.setLastDate(lastDay);
        inquiry.setReminderDate(reminderDate);
        inquiry.setPickUpTime(inquiry.getPickUpTime());
        inquiry.setNight(inquiry.getNight());
        inquiry.setSelectBooking("hire");
        inquiry.setAllocateKM(inquiry.getAllocateKM());
        inquiry.setKmCharge(inquiry.getKmCharge());
        inquiry.setAllocateHours(inquiry.getAllocateHours());
        inquiry.setHoursCharge(inquiry.getHoursCharge());

        this.bookingDaoService.insertBookingData(inquiry);
        log.info("REST request to service 10 : {}", inquiry.getUserName());

        return inquiry;
    }

    //    public Page<Inquiry> findAllPending(Pageable pageable) throws IOException {
//
//        List<Inquiry> results = this.bookingDaoService.getAllPending();
//
//        Page<Inquiry> pages = new PageImpl<Inquiry>(results, pageable, results.size());
//        log.info("@@@@@@@@@@@@ ArrayList @@@@@@@@@@@"+results);
//        return pages;
//    }
    public List<Inquiry> findAllPending() throws IOException {

        List<Inquiry> results = this.bookingDaoService.getAllPending();

        // Page<Inquiry> pages = new PageImpl<Inquiry>(results, pageable, results.size());
        log.info("@@@@@@@@@@@@ ArrayList @@@@@@@@@@@" + results);
        return results;
    }


    public void getUpdate(String inquiry, String price, String vehicle) {
        String status = "2";
        Inquiry updateList = this.bookingDaoService.updateBookingData(inquiry, price, vehicle, status);
        Inquiry selectedList = this.bookingDaoService.getFind(inquiry);
        Vehicle selectedVehicle = this.vehicleDaoService.selectedVehicle(selectedList.getDriverName());
        List<BookingVehicle> allRecords = this.vehicleBookingDaoService.getFindAll();
        BookingVehicle bookingVehicle = new BookingVehicle();
        Integer count = allRecords.size() + 1;
        log.info("@@@@@@@@@@@@ ArrayList lloping @@@@@@@@@@@" + selectedVehicle.getId());
        bookingVehicle.setId("vb" + count);
        bookingVehicle.setDriverName(selectedList.getDriverName());
        bookingVehicle.setDriverId(selectedVehicle.getId());
        bookingVehicle.setVehicleType(selectedList.getSelectVehicleType());
        bookingVehicle.setVehicleNum(selectedVehicle.getVehicleNum());
        bookingVehicle.setStartDate(selectedList.getFirstDate());
        bookingVehicle.setEndDate(selectedList.getLastDate());
        bookingVehicle.setStatus(1);
        this.vehicleBookingDaoService.getInsertVehicleBooking(bookingVehicle);
    }

    public void getRejectInquiry(String inquiry) {
        String status = "3";
        this.bookingDaoService.RejectBookingInquiry(inquiry, status);
    }

    public List<Inquiry> getContactSearch(String contactNo) {
        List<Inquiry> results = bookingDaoService.isContactNo(contactNo);
        return results;
    }

    public List<Inquiry> findAllBooking() throws IOException {
        String status = "2";
        List<Inquiry> results = this.bookingDaoService.getAllBooking(status);

        // Page<Inquiry> pages = new PageImpl<Inquiry>(results, pageable, results.size());
        log.info("@@@@@@@@@@@@ ArrayList @@@@@@@@@@@" + results);
        return results;
    }

    public List<Inquiry> getContactBookingSearch(String contactNo) {
        List<Inquiry> results = bookingDaoService.isBookingContactNo(contactNo);
        return results;
    }

    public void getBugFix() {
//        String status = "3";
//        this.bookingDaoService.RejectBookingInquiry(inquiry,status);
        List<Inquiry> AllInquiryList = this.bookingDaoService.getAllBooking("4");

        log.info("@@@@@@@@@@@@ ArrayList lloping LIST SIZE @@@@@@@@@@@" + AllInquiryList.size());
        for (Inquiry listOne : AllInquiryList) {
            {

                log.info("@@@@@@@@@@@@ ArrayList lloping @@@@@@@@@@@" + listOne.getInquiryId());
                Vehicle selectedVehicle = this.vehicleDaoService.selectedVehicle(listOne.getDriverName());
                List<BookingVehicle> allRecords = this.vehicleBookingDaoService.getFindAll();

                if (selectedVehicle != null) {

                    Integer count = allRecords.size() + 1;

                    log.info("@@@@@@@@@@@@ ArrayList lloping @@@@@@@@@@@" + selectedVehicle.getId());
                    BookingVehicle bookingVehicle = new BookingVehicle();
                    bookingVehicle.setId("vb" + count);
                    bookingVehicle.setDriverName(listOne.getDriverName());
                    bookingVehicle.setDriverId(selectedVehicle.getId());
                    bookingVehicle.setVehicleType(listOne.getSelectVehicleType());
                    bookingVehicle.setVehicleNum(selectedVehicle.getVehicleNum());
                    bookingVehicle.setStartDate(listOne.getFirstDate());
                    bookingVehicle.setEndDate(listOne.getLastDate());
                    bookingVehicle.setStatus(2);
                    bookingVehicle.setInquiryId(listOne.getInquiryId());
                    this.vehicleBookingDaoService.getInsertVehicleBooking(bookingVehicle);
                }
            }

        }


    }

    public void datePatch() {

        List<Inquiry> All_Inquiry = bookingDaoService.getAll();
        for (Inquiry listOne : All_Inquiry) {

            String date = "2020-08-10";
            try {
                Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                Inquiry inquiry = bookingDaoService.datePatch(listOne.getInquiryId(), date1);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            //Date date1 = listOne.setReminderDate(date);


        }


    }

    public List<Inquiry> findReminderList() {
        String status = "8";
        List<Inquiry> results = this.bookingDaoService.getAllBooking(status);
        log.info("@@@@@@@@@@@@ ArrayList @@@@@@@@@@@" + results);
        return results;
    }


    public List<Inquiry> getFindTodayRemaind() {
        Date date = new Date();
        Date today = DateUtils.addDays(date, 1);
        List<Inquiry> results = this.bookingDaoService.getReminderListToday(today);
        log.info("@@@@@@@@@@@@ ArrayList @@@@@@@@@@@" + results);
        return results;
    }

    public List<Inquiry> getRunningHire() {
        Date date = new Date();
        Date today = DateUtils.addDays(date, 1);
        List<Inquiry> results = new ArrayList<>();

        log.info("log info @@@@@@@@@@ TODAY_DATE 11111111 @@@@@@@@@@" + today);
        List<Inquiry> bookingHire = this.bookingDaoService.getAllBooking("2");
        log.info("log info @@@@@@@@@@ TODAY_DATE 1112211 @@@@@@@@@@" + bookingHire.size());

        Instant now = Instant.now(); //current date
        // Instant before = now.minus(Duration.ofDays(1));
        //  Date dateBefore = Date.from(before);
        Date dateBefore1 = Date.from(now);
        log.info("log info @@@@@@@@@@ TODAY_DATE 222222 @@@@@@@@@@" + dateBefore1);

        Date dateBefore2 = DateUtils.addDays(dateBefore1, 0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = simpleDateFormat.format(dateBefore2);
        //String currentDate = "2020-08-19";


        List<Date> dates = new ArrayList<Date>();
        for (Inquiry listOne : bookingHire) {

            Date str_date = listOne.getFirstDate();
            Date end_date = listOne.getLastDate();
            String DayCount = listOne.getDateCount();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
//            String strDate = dateFormat.format(str_date);
//            String endDate = dateFormat.format(end_date);
            String strDate = String.valueOf(str_date);
            String endDate = String.valueOf(end_date);
            int a = Integer.parseInt(DayCount);

            if (a > 2) {
                log.info("log info @@@@@@@@@@ TODAY_DATE 333 @@@@@@@@@@");
                DateFormat formatter;
                formatter = new SimpleDateFormat("dd/MM/yyyy");
                //Date startDate = null;
                try {
                    log.info("log info @@@@@@@@@@ TODAY_DATE 444 @@@@@@@@@@");
                    //  Date startDate= formatter.parse(strDate);
                    // Date startDate = (Date) formatter.parse(String.valueOf(str_date));
                    // Date end_Date = formatter.parse(endDate);

                    log.info("log info @@@@@@@@@@ TODAY_DATE 555 @@@@@@@@@@");
                    LocalDate dateStart = new LocalDate(strDate);
                    LocalDate dateEnd = new LocalDate(endDate);
// day by day:
//                    if(currentDate.equals(strDate) || currentDate.equals(endDate)){
//                        results.add(listOne);
//                    }
                    while (dateStart.isBefore(dateEnd)) {
                        System.out.println(dateStart);
                        dateStart = dateStart.plusDays(1);
                        log.info("log info @@@@@@@@@@ TODAY_DATE 555 @@@@@@@@@@" + dateStart);

                        String nowDate = dateStart.toString();
                        log.info("log info @@@@@@@@@@ TODAY_DATE 666 @@@@@@@@@@" + nowDate + " @@@@@@@@@@" + currentDate);
                        if (currentDate.equals(nowDate)) {
                            log.info("log info @@@@@@@@@@ TODAY_DATE 555 @@@@@@@@@@" + dateStart);
                            results.add(listOne);
                            break;
                        }
                    }


//                    long interval = 24 * 1000 * 60 * 60; // 1 hour in millis
//                    long endTime = end_Date.getTime(); // create your endtime here, possibly using Calendar or Date
//                    long curTime = startDate.getTime();
//                    while (curTime <= endTime) {
//                        dates.add(new Date(curTime));
//                        curTime += interval;
//                    }
//                    for (int i = 0; i < dates.size(); i++) {
//                        Date lDate = (Date) dates.get(i);
//                        String ds = formatter.format(lDate);
//                        log.info("log info @@@@@@@@@@ TODAY_DATE 3331 @@@@@@@@@@"+ds );
//
//                        String nowDate = dateFormat.format(ds);
//
//                        if(currentDate.equals(nowDate)){
//                            results.add(listOne);
//                            break;
//                        }
//                        System.out.println(" Date is ..." + ds);
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (currentDate.equals(strDate) || currentDate.equals(endDate)) {
                results.add(listOne);
            }
        }
        log.info("@@@@@@@@@@@@ ArrayList @@@@@@@@@@@" + results);
        return results;
    }


    public List<Inquiry> getReminderDateExtend(String inquiry, Date reminderDate, String charge) {

        Date firstDay = DateUtils.addDays(reminderDate, 1);
        List<Inquiry> results = this.bookingDaoService.getReminderDateExtend(inquiry, firstDay, charge);

        return results;
    }

    public void getbookingStageReminder(String inquiry, String price) {
        String status = "1";
        this.bookingDaoService.reminderBooking(inquiry, price, status);
    }


    public void getUpdate2(String inquiry, String price, String vehicle, String pickUpTime, String Advance) {
        String status = "2";
        Inquiry updateList = this.bookingDaoService.updateBookingData2(inquiry, price, vehicle, status, pickUpTime, Advance);
        Inquiry selectedList = this.bookingDaoService.getFind(inquiry);
        Vehicle selectedVehicle = this.vehicleDaoService.selectedVehicle(selectedList.getDriverName());
        List<BookingVehicle> allRecords = this.vehicleBookingDaoService.getFindAll();
        BookingVehicle bookingVehicle = new BookingVehicle();
        Integer count = allRecords.size() + 1;
        log.info("@@@@@@@@@@@@ ArrayList lloping @@@@@@@@@@@" + selectedVehicle.getId());
        bookingVehicle.setId("vb" + count);
        bookingVehicle.setDriverName(selectedList.getDriverName());
        bookingVehicle.setDriverId(selectedVehicle.getId());
        bookingVehicle.setVehicleType(selectedList.getSelectVehicleType());
        bookingVehicle.setVehicleNum(selectedVehicle.getVehicleNum());
        bookingVehicle.setStartDate(selectedList.getFirstDate());
        bookingVehicle.setEndDate(selectedList.getLastDate());
        bookingVehicle.setStatus(1);
        this.vehicleBookingDaoService.getInsertVehicleBooking(bookingVehicle);
    }


    public Inquiry getInquiryIdDetails(String inquiryId) {

        Inquiry inquiry = bookingDaoService.getFind(inquiryId);

        return inquiry;
    }

    public Inquiry updateBookingStage(Inquiry inquiry) {

        Inquiry getData = bookingDaoService.getFind(inquiry.getInquiryId());

        Date nowFirstDate = inquiry.getFirstDate();
        Date postFirstDate = getData.getFirstDate();

        Date nowLastDate = inquiry.getLastDate();
        Date postLastDate = getData.getLastDate();


        if (!postFirstDate.equals(nowFirstDate)) {
            Date Date3 = inquiry.getFirstDate();
            Date nowDate = DateUtils.addDays(Date3, 1);
            inquiry.setFirstDate(nowDate);
        }

        if (!postLastDate.equals(nowLastDate)) {
            Date Date3 = inquiry.getLastDate();
            Date nowDate = DateUtils.addDays(Date3, 1);
            inquiry.setLastDate(nowDate);
        }


        Inquiry findUpdateBooking = bookingDaoService.getFindUpdateBooking(inquiry);
        return inquiry;
    }


}








package com.example.webRest;


import com.example.domain.DTOClass.CustomerHireDTO;
import com.example.domain.DTOClass.OurVehicleReportDTO;
import com.example.domain.DTOClass.Payment_ReportDTO;
import com.example.domain.DTOClass.RevenueDTO;
import com.example.domain.Payment;
import com.example.service.PaymentService;
import com.example.service.ReportService;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
@Api(value = "ReportResource Resource REST Endpoint", description = "Shows the Payment info")
public class ReportResource {

    private final Logger log = LoggerFactory.getLogger(ReportResource.class);


    public ReportResource() {

    }

    @Autowired
    ReportService reportService;

    @GetMapping("/Report-customer/{firstDate}/{lastDate}/{contactNo}")
    public ResponseEntity<List<CustomerHireDTO>> getsearchCustomerDateBetween(@PathVariable("firstDate") final Date firstDate, @PathVariable("lastDate") final Date lastDate, @PathVariable("contactNo") final String contactNo)throws IOException {

        log.info("REST ReportResource to RevenueDTO  : {}",firstDate);
        List<CustomerHireDTO> Result = null;
        Result= reportService.getsearchCustomerDateBetween(firstDate,lastDate,contactNo);
        log.info("REST  to Return RevenueDTO List  : {}", Result);
        return new ResponseEntity<List<CustomerHireDTO>>(Result, HttpStatus.OK);

    }

    @GetMapping("/Report-revenue/{firstDate}/{lastDate}")
    public ResponseEntity<List<RevenueDTO>> getsearchDateBetween(@PathVariable("firstDate") final Date firstDate, @PathVariable("lastDate") final Date lastDate)throws IOException {

        log.info("REST ReportResource to RevenueDTO  : {}",firstDate);
        List<RevenueDTO> Result = null;
        Result= reportService.getsearchDateBetween(firstDate,lastDate);
        log.info("REST  to Return RevenueDTO List  : {}", Result);
        return new ResponseEntity<List<RevenueDTO>>(Result, HttpStatus.OK);

    }


    @GetMapping("/Report-ourVehicle/{firstDate}/{lastDate}")
    public ResponseEntity<List<OurVehicleReportDTO>> getsearchOurVehicleBetween(@PathVariable("firstDate") final Date firstDate, @PathVariable("lastDate") final Date lastDate)throws IOException {

        log.info("REST ReportResource to RevenueDTO  : {}",firstDate);
        List<OurVehicleReportDTO> Result = null;
        Result= reportService.getsearchOurVehicleBetween(firstDate,lastDate);
        log.info("REST  to Return RevenueDTO List  : {}", Result);
        return new ResponseEntity<List<OurVehicleReportDTO>>(Result, HttpStatus.OK);

    }


}

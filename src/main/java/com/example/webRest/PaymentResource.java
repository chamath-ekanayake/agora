package com.example.webRest;


import com.example.domain.DTOClass.Payment_ReportDTO;
import com.example.domain.Payment;
import com.example.service.PaymentService;
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
@Api(value = "PaymentResource Resource REST Endpoint", description = "Shows the Payment info")
public class PaymentResource {

    private final Logger log = LoggerFactory.getLogger(BookingResource.class);


    public PaymentResource() {

    }

    @Autowired
    PaymentService paymentService;

    @PostMapping("/Payment")
    public ResponseEntity<Payment> getPaymentInsert(@Valid @RequestBody Payment payment) throws URISyntaxException, ParseException, Exception {
        log.info("REST request to save Payment : {}", payment);
        try {


             Payment result = paymentService.getPostInquiry(payment);

            return ResponseEntity.created(new URI("/api/Payment/" + result.getAddKm()))
                    .headers(HeaderUtil.createEntityCreationAlert(payment.getCommissionDis(), result.getId()))
                    .body(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/Payment/{firstDate}/{lastDate}")
    public ResponseEntity<List<Payment_ReportDTO>> getsearchDateBetween(@PathVariable("firstDate") final Date firstDate, @PathVariable("lastDate") final Date lastDate)throws IOException {

            log.info("REST PaymentResource to Payment  : {}",firstDate);
        List<Payment_ReportDTO> paymentList = null;
         paymentList= paymentService.getsearchDateBetween(firstDate,lastDate);
        log.info("REST  to Return paymentList List  : {}", paymentList);
        return new ResponseEntity<List<Payment_ReportDTO>>(paymentList, HttpStatus.OK);

    }

}

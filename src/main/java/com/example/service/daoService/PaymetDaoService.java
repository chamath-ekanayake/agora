package com.example.service.daoService;

import com.example.domain.Payment;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface PaymetDaoService {

    void insertPayment(Payment payment);
    List<Payment> SearchDateBetWeen(Date firstDate, Date LastDate);
    List<Payment> getAll();
    Payment getpayment(String inquiryId);
}

package com.ftn.studentservice.service;

import com.ftn.studentservice.model.Payment;

import java.util.List;

public interface IPaymentService {

    Payment findOne(Long id);

    List<Payment> findAll();

    Payment save(Payment payment);

    void delete(Long id);
}

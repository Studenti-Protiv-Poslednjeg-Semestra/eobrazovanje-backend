package com.ftn.studentservice.service;

import com.ftn.studentservice.model.Payment;
import com.ftn.studentservice.web.dto.PaymentDTO;
import com.ftn.studentservice.web.dto.StudentAddMoneyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPaymentService {

    Payment findOne(Long id);

    List<Payment> findAll();

    List<PaymentDTO> findByStudentId(Long studentId, Pageable pageable);

    Payment save(Payment payment);

    Payment saveMoney(StudentAddMoneyDTO payment);

    void delete(Long id);
}

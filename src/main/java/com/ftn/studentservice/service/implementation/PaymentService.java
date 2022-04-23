package com.ftn.studentservice.service.implementation;

import com.ftn.studentservice.model.Payment;
import com.ftn.studentservice.repository.PaymentRepository;
import com.ftn.studentservice.service.IPaymentService;
import com.ftn.studentservice.util.mapper.PaymentMapper;
import com.ftn.studentservice.web.dto.PaymentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService implements IPaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentService(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    @Override
    public Payment findOne(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public List<PaymentDTO> findByStudentId(Long studentId, Pageable pageable) {
        return paymentRepository.findByStudent_Id(studentId, pageable).stream().map(paymentMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }
}

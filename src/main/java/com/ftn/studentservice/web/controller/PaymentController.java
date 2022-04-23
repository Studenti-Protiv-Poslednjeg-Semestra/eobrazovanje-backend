package com.ftn.studentservice.web.controller;

import com.ftn.studentservice.service.IPaymentService;
import com.ftn.studentservice.util.exception.EntityNotFoundException;
import com.ftn.studentservice.web.dto.PaymentDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/payment")
public class PaymentController {

    private final static Integer SIZE = 10;

    private final IPaymentService iPaymentService;

    public PaymentController(IPaymentService iPaymentService) {
        this.iPaymentService = iPaymentService;
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN', 'STUDENT')")
    @GetMapping(value = "/{studentId}")
    public ResponseEntity<List<PaymentDTO>> getPaymentsForStudent(@PathVariable Long studentId, @PathParam(value = "page") Integer page){

        List<PaymentDTO> payments = iPaymentService.findByStudentId(studentId, PageRequest.of(page, SIZE));

        if(payments.isEmpty() || payments == null){
            throw new EntityNotFoundException("Payments for student not found");
        }

        return new ResponseEntity<>(payments, HttpStatus.OK);
    }
}

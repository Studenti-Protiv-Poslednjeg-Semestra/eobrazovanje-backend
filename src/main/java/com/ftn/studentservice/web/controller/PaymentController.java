package com.ftn.studentservice.web.controller;

import com.ftn.studentservice.service.IPaymentService;
import com.ftn.studentservice.util.exception.EntityNotFoundException;
import com.ftn.studentservice.web.dto.PaymentDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "payments")
public class PaymentController {

    private final IPaymentService iPaymentService;

    public PaymentController(IPaymentService iPaymentService) {
        this.iPaymentService = iPaymentService;
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN', 'STUDENT')")
    @GetMapping(value = "/{studentId}")
    public ResponseEntity<List<PaymentDTO>> getPaymentsForStudent(@PathVariable Long studentId, @RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size){

        List<PaymentDTO> payments = iPaymentService.findByStudentId(studentId, PageRequest.of(page, size));

        if(payments.isEmpty() || payments == null){
            throw new EntityNotFoundException("Payments for student not found");
        }

        return new ResponseEntity<>(payments, HttpStatus.OK);
    }
}

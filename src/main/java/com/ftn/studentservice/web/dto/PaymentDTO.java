package com.ftn.studentservice.web.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentDTO {

    private Long id;

    private Double amount;

    private LocalDateTime timestamp;

    private String reasonForPayment;

    private StudentDTO studentDTO;
}

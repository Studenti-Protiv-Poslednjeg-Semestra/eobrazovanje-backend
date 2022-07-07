package com.ftn.studentservice.web.dto;

import lombok.Data;

@Data
public class StudentAddMoneyDTO {

    private String reasonForPayment;
    private Double amount;
    private Long studentId;
}

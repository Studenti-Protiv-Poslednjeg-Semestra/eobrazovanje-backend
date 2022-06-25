package com.ftn.studentservice.web.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ExaminationPeriodDTO implements Serializable {

    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

}

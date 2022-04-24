package com.ftn.studentservice.web.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ExamDTO implements Serializable {

    private Long id;
    private Integer points;
    private ExamScheduleDTO examScheduleDTO;
    private StudentDTO studentDTO;

}

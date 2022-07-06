package com.ftn.studentservice.web.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class StudentToSyllabusDTO implements Serializable {

    private Long studentId;
    private Long syllabusId;
}

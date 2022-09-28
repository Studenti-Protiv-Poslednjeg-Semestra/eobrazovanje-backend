package com.ftn.studentservice.web.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SyllabusDTO {

    private Long id;
    private LocalDate yearOfCreation;
    private MajorDTO majorDTO;

}

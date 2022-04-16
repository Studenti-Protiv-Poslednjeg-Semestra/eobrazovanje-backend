package com.ftn.studentservice.web.dto;

import lombok.Data;

@Data
public class SubjectDTO {

    private Long id;
    private String code;
    private String name;
    private String description;
    private Integer ECTS;
    private SyllabusDTO syllabusDTO;
}

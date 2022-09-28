package com.ftn.studentservice.web.dto;

import com.ftn.studentservice.model.ResponsibilityType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubjectDTO {

    private Long id;
    private String code;
    private String name;
    private Integer semester;
    private String description;
    private Integer ECTS;
    private SyllabusDTO syllabusDTO;
}

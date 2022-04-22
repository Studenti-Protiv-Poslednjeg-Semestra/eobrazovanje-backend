package com.ftn.studentservice.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SubjectDTO {

    private Long id;
    @JsonProperty("code")
    private String code;
    @JsonProperty("name")
    private String name;
    @JsonProperty("desc")
    private String description;
    @JsonProperty("ects")
    private Integer ECTS;
    private SyllabusDTO syllabusDTO;

}

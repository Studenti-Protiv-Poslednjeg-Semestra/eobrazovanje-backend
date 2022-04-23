package com.ftn.studentservice.web.dto;

import lombok.Data;

@Data
public class MajorDTO {

    private Long id;

    private String name;

    private Integer duration;

    private Integer totalETCS;
}

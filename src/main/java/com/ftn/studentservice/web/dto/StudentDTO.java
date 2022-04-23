package com.ftn.studentservice.web.dto;

import lombok.Data;

@Data
public class StudentDTO {

    private UserDTO userDTO;

    private Double funds;

    private Integer semester;

    private MajorDTO majorDTO;
}

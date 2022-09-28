package com.ftn.studentservice.web.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class MajorCreationDTO {

    @NotBlank(message = "Major must have a name!")
    private String name;

    @Min(value = 2, message = "Major must have at least 2 semesters.")
    @Max(value = 8, message = "Major must have maximum 8 semesters.")
    private Integer duration;

}

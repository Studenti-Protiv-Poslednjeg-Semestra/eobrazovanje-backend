package com.ftn.studentservice.web.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class SyllabusCreationDTO {

    @NotNull(message = "Syllabus must have major selected!")
    private Long majorId;

    @NotNull(message = "Syllabus must contain Date!")
    private LocalDate yearOfCreation;

}

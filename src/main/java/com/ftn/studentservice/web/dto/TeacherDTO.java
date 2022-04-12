package com.ftn.studentservice.web.dto;

import lombok.Data;

import java.util.Set;

@Data
public class TeacherDTO {

    private UserDTO userDTO;
    private Set<SubjectDTO> professorOn;
    private Set<SubjectDTO> assistantOn;
}

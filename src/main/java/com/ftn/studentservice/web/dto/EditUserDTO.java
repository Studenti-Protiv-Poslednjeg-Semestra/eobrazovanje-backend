package com.ftn.studentservice.web.dto;

import lombok.Data;

@Data
public class EditUserDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}

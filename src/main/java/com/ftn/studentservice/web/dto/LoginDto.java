package com.ftn.studentservice.web.dto;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class LoginDto {
    @Email
    private String email;
    private String password;
}

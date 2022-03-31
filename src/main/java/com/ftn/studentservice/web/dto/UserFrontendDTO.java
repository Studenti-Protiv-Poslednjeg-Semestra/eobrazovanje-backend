package com.ftn.studentservice.web.dto;

import com.ftn.studentservice.model.User;

import java.io.Serializable;

public class UserFrontendDTO implements Serializable {

    private String firstName;
    private String lastName;
    private String email;
    private String UCN;

    public UserFrontendDTO() {
    }

    public UserFrontendDTO(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.UCN = user.getUCN();
    }

    public UserFrontendDTO(String firstName, String lastName, String email, String UCN) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.UCN = UCN;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUCN() {
        return UCN;
    }

    public void setUCN(String UCN) {
        this.UCN = UCN;
    }
}

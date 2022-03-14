package com.ftn.studentservice.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Administrator {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private User user;
}

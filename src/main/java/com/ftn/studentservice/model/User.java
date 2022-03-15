package com.ftn.studentservice.model;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String UCN;
    @Enumerated
    @Column(nullable = false)
    private Role role;
}

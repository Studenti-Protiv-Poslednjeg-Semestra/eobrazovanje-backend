package com.ftn.studentservice.model;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
//@NoArgsConstructor
@AllArgsConstructor
//End of lombok
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

    //I commented out role because i feel like it
    // is going to be redundant once we implement
    // spring security and assign Authorities to the
    // User - WLQMPEK
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private Role role;
}

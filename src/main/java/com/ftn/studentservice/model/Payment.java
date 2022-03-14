package com.ftn.studentservice.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Payment {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private LocalDateTime timestamp;


    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
}

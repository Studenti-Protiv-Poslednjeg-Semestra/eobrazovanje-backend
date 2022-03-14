package com.ftn.studentservice.model;

import javax.persistence.*;

@Entity
public class Document {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String path;

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
}

package com.ftn.studentservice.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class Enrollment {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Column
    @Min(5)
    @Max(10)
    private Integer grade;

}

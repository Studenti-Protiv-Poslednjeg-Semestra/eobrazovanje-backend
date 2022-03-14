package com.ftn.studentservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
//End of lombok
@Entity
public class Responsibility {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private ResponsibilityType responsibilityType;

    @Column
    private Integer points;

    @Column
    private String place;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "subject_id")
    private Subject subject;


    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "student_id")
    private Student student;

}

package com.ftn.studentservice.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Major {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer duration;

    @Column(nullable = false)
    private Integer totalETCS;


    @OneToMany(mappedBy = "major", cascade = CascadeType.REFRESH, orphanRemoval = true)
    private Set<Student> students;

    @OneToMany(mappedBy = "major", cascade = CascadeType.REFRESH, orphanRemoval = true)
    private Set<Syllabus> syllabuses;
}
package com.ftn.studentservice.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ResponsibilityDefinition {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Enumerated
    @Column
    private ResponsibilityType responsibilityType;


    @OneToMany(mappedBy = "responsibilityDefinition", orphanRemoval = true)
    private Set<Responsibility> responsibilities = new java.util.LinkedHashSet<>();

}

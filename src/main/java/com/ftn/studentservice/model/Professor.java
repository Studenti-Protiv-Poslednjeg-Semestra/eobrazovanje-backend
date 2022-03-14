package com.ftn.studentservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
//End of lombok
@Entity
public class Professor {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "professor_subjects",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "subjects_id"))
    private Set<Subject> subjects = new java.util.LinkedHashSet<>();

    @Embedded
    private User user;

}

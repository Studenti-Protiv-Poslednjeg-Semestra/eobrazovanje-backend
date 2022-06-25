package com.ftn.studentservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
//@ToString
@RequiredArgsConstructor
//@NoArgsConstructor
@AllArgsConstructor
//End of lombok
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "professors", cascade = CascadeType.REFRESH)
    private Set<Subject> professorOn = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "assistants", cascade = CascadeType.REFRESH)
    private Set<Subject> assistantOn = new LinkedHashSet<>();

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    @MapsId
    private User user;

}

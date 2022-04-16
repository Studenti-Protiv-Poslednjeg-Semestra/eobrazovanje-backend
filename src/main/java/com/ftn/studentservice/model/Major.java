package com.ftn.studentservice.model;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
//@NoArgsConstructor
@AllArgsConstructor
//End of lombok
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
    @ToString.Exclude
    private Set<Syllabus> syllabuses;
}

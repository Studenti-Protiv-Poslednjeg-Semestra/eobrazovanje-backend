package com.ftn.studentservice.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
//@ToString
@RequiredArgsConstructor
//@NoArgsConstructor
@AllArgsConstructor
//End of lombok
@Entity
public class Enrollment {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "student_id")
    private Student student;

    @NotNull
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Column
    private Integer grade;

}

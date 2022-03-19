package com.ftn.studentservice.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
//@NoArgsConstructor
@AllArgsConstructor
//End of lombok
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

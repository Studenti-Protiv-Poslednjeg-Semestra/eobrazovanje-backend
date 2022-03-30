package com.ftn.studentservice.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
//@NoArgsConstructor
@AllArgsConstructor
//End of lombok
@Entity
public class Responsibility {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Integer points;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "student_id")
    private Student student;

    @Column
    private LocalDate date;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "responsibility_definition_id")
    private ResponsibilityDefinition responsibilityDefinition;

}

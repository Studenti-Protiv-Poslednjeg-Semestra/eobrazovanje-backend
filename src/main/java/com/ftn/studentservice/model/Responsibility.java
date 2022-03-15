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

    @Column
    private Integer points;

    @Column
    private String place;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "responsibility_definition_id")
    private ResponsibilityDefinition responsibilityDefinition;

}

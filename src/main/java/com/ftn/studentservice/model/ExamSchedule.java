package com.ftn.studentservice.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
//@NoArgsConstructor
@AllArgsConstructor
//End of lombok
@Entity
public class ExamSchedule {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private LocalDateTime timeOfExam;

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

}

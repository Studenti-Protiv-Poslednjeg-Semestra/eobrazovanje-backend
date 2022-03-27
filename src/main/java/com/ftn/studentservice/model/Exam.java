package com.ftn.studentservice.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
//@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
//End of lombok
@Entity
public class Exam {
    @Id
    @GeneratedValue
    private Long id;

    private Integer points;

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "exam_schedule_id", nullable = false)
    private ExamSchedule examSchedule;

}

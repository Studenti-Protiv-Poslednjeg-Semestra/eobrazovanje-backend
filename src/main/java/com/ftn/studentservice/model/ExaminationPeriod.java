package com.ftn.studentservice.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
//@ToString
//@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
//End of lombok
@Entity
public class ExaminationPeriod {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    @OneToMany(mappedBy = "examinationPeriod", cascade = CascadeType.REFRESH, orphanRemoval = true)
    private Set<ExamSchedule> examSchedules;
}

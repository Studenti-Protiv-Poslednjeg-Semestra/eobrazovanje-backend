package com.ftn.studentservice.web.dto;

import com.ftn.studentservice.model.ExamSchedule;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ExamScheduleDTO implements Serializable {

    private Long id;
    private LocalDateTime timeOfExam;
    private SubjectDTO subjectDTO;
    private String place;
    private ExaminationPeriodDTO examinationPeriodDTO;

}

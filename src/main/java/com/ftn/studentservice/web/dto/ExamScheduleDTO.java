package com.ftn.studentservice.web.dto;

import com.ftn.studentservice.model.ExamSchedule;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ExamScheduleDTO implements Serializable {

    private Long id;
    private LocalDateTime timeOfExam;
    private SubjectDTO subjectDTO;
    private String place;
    private ExaminationPeriodDTO examinationPeriodDTO;

    public ExamScheduleDTO() {
    }

    public ExamScheduleDTO(ExamSchedule examSchedule){
        this.id = examSchedule.getId();
        this.timeOfExam = examSchedule.getTimeOfExam();
        this.subjectDTO = new SubjectDTO(examSchedule.getSubject());
        this.place = examSchedule.getPlace();
        this.examinationPeriodDTO = new ExaminationPeriodDTO(examSchedule.getExaminationPeriod());
    }

    public ExamScheduleDTO(Long id, LocalDateTime timeOfExam, SubjectDTO subjectDTO,
                           String place, ExaminationPeriodDTO examinationPeriodDTO) {
        this.id = id;
        this.timeOfExam = timeOfExam;
        this.subjectDTO = subjectDTO;
        this.place = place;
        this.examinationPeriodDTO = examinationPeriodDTO;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimeOfExam() {
        return timeOfExam;
    }

    public void setTimeOfExam(LocalDateTime timeOfExam) {
        this.timeOfExam = timeOfExam;
    }

    public SubjectDTO getSubjectDTO() {
        return subjectDTO;
    }

    public void setSubjectDTO(SubjectDTO subjectDTO) {
        this.subjectDTO = subjectDTO;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public ExaminationPeriodDTO getExaminationPeriodDTO() {
        return examinationPeriodDTO;
    }

    public void setExaminationPeriodDTO(ExaminationPeriodDTO examinationPeriodDTO) {
        this.examinationPeriodDTO = examinationPeriodDTO;
    }
}

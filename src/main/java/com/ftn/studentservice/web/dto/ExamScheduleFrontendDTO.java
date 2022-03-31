package com.ftn.studentservice.web.dto;

import com.ftn.studentservice.model.ExamSchedule;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ExamScheduleFrontendDTO implements Serializable {

    private LocalDateTime timeOfExam;
    private SubjectFrontendDTO subjectFrontendDTO;
    private String place;
    private ExaminationPeriodFrontendDTO examinationPeriodFrontendDTO;

    public ExamScheduleFrontendDTO() {
    }

    public ExamScheduleFrontendDTO(ExamSchedule examSchedule){
        this.timeOfExam = examSchedule.getTimeOfExam();
        this.subjectFrontendDTO = new SubjectFrontendDTO(examSchedule.getSubject());
        this.place = examSchedule.getPlace();
        this.examinationPeriodFrontendDTO = new ExaminationPeriodFrontendDTO(examSchedule.getExaminationPeriod());
    }

    public ExamScheduleFrontendDTO(LocalDateTime timeOfExam, SubjectFrontendDTO subjectFrontendDTO, String place, ExaminationPeriodFrontendDTO examinationPeriodFrontendDTO) {
        this.timeOfExam = timeOfExam;
        this.subjectFrontendDTO = subjectFrontendDTO;
        this.place = place;
        this.examinationPeriodFrontendDTO = examinationPeriodFrontendDTO;
    }


    public LocalDateTime getTimeOfExam() {
        return timeOfExam;
    }

    public void setTimeOfExam(LocalDateTime timeOfExam) {
        this.timeOfExam = timeOfExam;
    }

    public SubjectFrontendDTO getSubjectFrontendDTO() {
        return subjectFrontendDTO;
    }

    public void setSubjectFrontendDTO(SubjectFrontendDTO subjectFrontendDTO) {
        this.subjectFrontendDTO = subjectFrontendDTO;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public ExaminationPeriodFrontendDTO getExaminationPeriodFrontendDTO() {
        return examinationPeriodFrontendDTO;
    }

    public void setExaminationPeriodFrontendDTO(ExaminationPeriodFrontendDTO examinationPeriodFrontendDTO) {
        this.examinationPeriodFrontendDTO = examinationPeriodFrontendDTO;
    }
}

package com.ftn.studentservice.web.dto;

import com.ftn.studentservice.model.ExaminationPeriod;

import java.io.Serializable;
import java.time.LocalDate;

public class ExaminationPeriodFrontendDTO implements Serializable {

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

    public ExaminationPeriodFrontendDTO() {
    }

    public ExaminationPeriodFrontendDTO(ExaminationPeriod examinationPeriod) {
        this.name = examinationPeriod.getName();
        this.startDate = examinationPeriod.getStartDate();
        this.endDate = examinationPeriod.getEndDate();
    }

    public ExaminationPeriodFrontendDTO(String name, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}

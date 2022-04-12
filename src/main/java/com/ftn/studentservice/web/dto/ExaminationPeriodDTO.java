package com.ftn.studentservice.web.dto;

import com.ftn.studentservice.model.ExaminationPeriod;

import java.io.Serializable;
import java.time.LocalDate;

public class ExaminationPeriodDTO implements Serializable {

    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

    public ExaminationPeriodDTO() {
    }

    public ExaminationPeriodDTO(ExaminationPeriod examinationPeriod) {
        this.id = examinationPeriod.getId();
        this.name = examinationPeriod.getName();
        this.startDate = examinationPeriod.getStartDate();
        this.endDate = examinationPeriod.getEndDate();
    }

    public ExaminationPeriodDTO(Long id, String name, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

package com.ftn.studentservice.web.dto;

import com.ftn.studentservice.model.Syllabus;

import java.io.Serializable;
import java.time.LocalDate;

public class SyllabusFrontendDTO implements Serializable {

    private Long id;
    private LocalDate yearOfCreation;
    private MajorFrontendDTO majorFrontendDTO;

    public SyllabusFrontendDTO() {
    }

    public SyllabusFrontendDTO(Syllabus syllabus) {
        this.id = syllabus.getId();
        this.yearOfCreation = syllabus.getYearOfCreation();
        this.majorFrontendDTO = new MajorFrontendDTO(syllabus.getMajor());
    }

    public SyllabusFrontendDTO(Long id, LocalDate yearOfCreation, MajorFrontendDTO majorFrontendDTO) {
        this.id = id;
        this.yearOfCreation = yearOfCreation;
        this.majorFrontendDTO = majorFrontendDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getYearOfCreation() {
        return yearOfCreation;
    }

    public void setYearOfCreation(LocalDate yearOfCreation) {
        this.yearOfCreation = yearOfCreation;
    }

    public MajorFrontendDTO getMajorFrontendDTO() {
        return majorFrontendDTO;
    }

    public void setMajorFrontendDTO(MajorFrontendDTO majorFrontendDTO) {
        this.majorFrontendDTO = majorFrontendDTO;
    }
}

package com.ftn.studentservice.web.dto;

import lombok.Data;
import java.time.LocalDate;
import com.ftn.studentservice.model.Syllabus;
import java.io.Serializable;

@Data
public class SyllabusDTO implements Serializable {

    private Long id;
    private LocalDate yearOfCreation;
    private MajorDTO majorDTO;

    public SyllabusDTO() {
    }

    public SyllabusDTO(Syllabus syllabus) {
        this.id = syllabus.getId();
        this.yearOfCreation = syllabus.getYearOfCreation();
        this.majorDTO = new MajorDTO(syllabus.getMajor());
    }

    public SyllabusDTO(Long id, LocalDate yearOfCreation, MajorDTO majorDTO) {
        this.id = id;
        this.yearOfCreation = yearOfCreation;
        this.majorDTO = majorDTO;
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

    public MajorDTO getMajorDTO() {
        return majorDTO;
    }

    public void setMajorDTO(MajorDTO majorDTO) {
        this.majorDTO = majorDTO;
    }

}

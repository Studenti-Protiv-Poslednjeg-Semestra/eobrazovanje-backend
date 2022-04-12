package com.ftn.studentservice.web.dto;

import com.ftn.studentservice.model.Subject;

import java.io.Serializable;

public class SubjectDTO implements Serializable {

    private Long id;
    private String name;
    private Integer semester;
    private String description;
    private Integer ECTS;
    private SyllabusDTO syllabusDTO;

    public SubjectDTO() {
    }

    public SubjectDTO(Subject subject) {
        this.id = subject.getId();
        this.name = subject.getName();
        this.semester = subject.getSemester();
        this.description = subject.getDescription();
        this.ECTS = subject.getECTS();
        this.syllabusDTO = new SyllabusDTO(subject.getSyllabus());
    }

    public SubjectDTO(Long id, String name, Integer semester, String description, Integer ECTS, SyllabusDTO syllabusDTO) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.description = description;
        this.ECTS = ECTS;
        this.syllabusDTO = syllabusDTO;
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

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getECTS() {
        return ECTS;
    }

    public void setECTS(Integer ECTS) {
        this.ECTS = ECTS;
    }

    public SyllabusDTO getSyllabusDTO() {
        return syllabusDTO;
    }

    public void setSyllabusDTO(SyllabusDTO syllabusDTO) {
        this.syllabusDTO = syllabusDTO;
    }
}

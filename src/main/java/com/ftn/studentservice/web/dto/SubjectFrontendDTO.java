package com.ftn.studentservice.web.dto;

import com.ftn.studentservice.model.Subject;

import java.io.Serializable;

public class SubjectFrontendDTO implements Serializable {

    private String name;
    private Integer semester;
    private String description;
    private Integer ECTS;
    private SyllabusFrontendDTO syllabusFrontendDTO;

    public SubjectFrontendDTO() {
    }

    public SubjectFrontendDTO(Subject subject) {
        this.name = subject.getName();
        this.semester = subject.getSemester();
        this.description = subject.getDescription();
        this.ECTS = subject.getECTS();
        this.syllabusFrontendDTO = new SyllabusFrontendDTO(subject.getSyllabus());
    }

    public SubjectFrontendDTO(String name, Integer semester, String description, Integer ECTS, SyllabusFrontendDTO syllabusFrontendDTO) {
        this.name = name;
        this.semester = semester;
        this.description = description;
        this.ECTS = ECTS;
        this.syllabusFrontendDTO = syllabusFrontendDTO;
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

    public SyllabusFrontendDTO getSyllabusFrontendDTO() {
        return syllabusFrontendDTO;
    }

    public void setSyllabusFrontendDTO(SyllabusFrontendDTO syllabusFrontendDTO) {
        this.syllabusFrontendDTO = syllabusFrontendDTO;
    }
}

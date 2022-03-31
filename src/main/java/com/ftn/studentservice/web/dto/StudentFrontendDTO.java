package com.ftn.studentservice.web.dto;

import com.ftn.studentservice.model.Student;

import java.io.Serializable;

public class StudentFrontendDTO implements Serializable {

    private Long id;
    private UserFrontendDTO userFrontendDTO;
    private Double funds;
    private Integer semester;
    private MajorFrontendDTO majorFrontendDTO;

    public StudentFrontendDTO() {
    }

    public StudentFrontendDTO(Student student) {
        this.id = student.getId();
        this.userFrontendDTO = new UserFrontendDTO(student.getUser());
        this.funds = student.getFunds();
        this.semester = student.getSemester();
        this.majorFrontendDTO = new MajorFrontendDTO(student.getMajor());
    }

    public StudentFrontendDTO(Long id, UserFrontendDTO userFrontendDTO, Double funds, Integer semester, MajorFrontendDTO majorFrontendDTO) {
        this.id = id;
        this.userFrontendDTO = userFrontendDTO;
        this.funds = funds;
        this.semester = semester;
        this.majorFrontendDTO = majorFrontendDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MajorFrontendDTO getMajorFrontendDTO() {
        return majorFrontendDTO;
    }

    public void setMajorFrontendDTO(MajorFrontendDTO majorFrontendDTO) {
        this.majorFrontendDTO = majorFrontendDTO;
    }

    public UserFrontendDTO getUserFrontendDTO() {
        return userFrontendDTO;
    }

    public void setUserFrontendDTO(UserFrontendDTO userFrontendDTO) {
        this.userFrontendDTO = userFrontendDTO;
    }

    public Double getFunds() {
        return funds;
    }

    public void setFunds(Double funds) {
        this.funds = funds;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

}

package com.ftn.studentservice.web.dto;

import com.ftn.studentservice.model.Student;

import java.io.Serializable;

public class StudentDTO implements Serializable {

    private Long id;
    private UserDTO userDTO;
    private Double funds;
    private Integer semester;
    private MajorDTO majorDTO;

    public StudentDTO() {
    }

    public StudentDTO(Student student) {
        this.id = student.getId();
        this.userDTO = new UserDTO(student.getUser());
        this.funds = student.getFunds();
        this.semester = student.getSemester();
        this.majorDTO = new MajorDTO(student.getMajor());
    }

    public StudentDTO(Long id, UserDTO userDTO, Double funds, Integer semester, MajorDTO majorDTO) {
        this.id = id;
        this.userDTO = userDTO;
        this.funds = funds;
        this.semester = semester;
        this.majorDTO = majorDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MajorDTO getMajorDTO() {
        return majorDTO;
    }

    public void setMajorDTO(MajorDTO majorDTO) {
        this.majorDTO = majorDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
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

package com.ftn.studentservice.web.dto;

import com.ftn.studentservice.model.Exam;

import java.io.Serializable;

public class ExamFrontendDTO implements Serializable {

    private Long id;
    private Integer points;
    private ExamScheduleFrontendDTO examScheduleFrontendDTO;
    private StudentFrontendDTO studentFrontendDTO;

    public ExamFrontendDTO(){}

    public ExamFrontendDTO(Exam exam){
        super();
        this.id = exam.getId();
        this.points = exam.getPoints();
        this.examScheduleFrontendDTO = new ExamScheduleFrontendDTO(exam.getExamSchedule());
        this.studentFrontendDTO = new StudentFrontendDTO(exam.getStudent());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public ExamScheduleFrontendDTO getExamScheduleFrontendDTO() {
        return examScheduleFrontendDTO;
    }

    public void setExamScheduleFrontendDTO(ExamScheduleFrontendDTO examScheduleFrontendDTO) {
        this.examScheduleFrontendDTO = examScheduleFrontendDTO;
    }

    public StudentFrontendDTO getStudentFrontendDTO() {
        return studentFrontendDTO;
    }

    public void setStudentFrontendDTO(StudentFrontendDTO studentFrontendDTO) {
        this.studentFrontendDTO = studentFrontendDTO;
    }
}

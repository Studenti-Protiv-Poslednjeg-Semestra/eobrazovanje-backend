package com.ftn.studentservice.web.dto;

import com.ftn.studentservice.model.Exam;

import java.io.Serializable;

public class ExamDTO implements Serializable {

    private Long id;
    private Integer points;
    private ExamScheduleDTO examScheduleDTO;
    private StudentDTO studentDTO;

    public ExamDTO(){}

//    public ExamDTO(Exam exam){
//        super();
//        this.id = exam.getId();
//        this.points = exam.getPoints();
//        this.examScheduleDTO = new ExamScheduleDTO(exam.getExamSchedule());
//        this.studentDTO = new StudentDTO(exam.getStudent());
//    }


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

    public ExamScheduleDTO getExamScheduleDTO() {
        return examScheduleDTO;
    }

    public void setExamScheduleDTO(ExamScheduleDTO examScheduleDTO) {
        this.examScheduleDTO = examScheduleDTO;
    }

    public StudentDTO getStudentDTO() {
        return studentDTO;
    }

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
    }
}

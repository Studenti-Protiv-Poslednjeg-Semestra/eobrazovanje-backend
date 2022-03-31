package com.ftn.studentservice.web.dto;

import com.ftn.studentservice.model.Exam;

import java.io.Serializable;

public class ExamFrontendDTO implements Serializable {

    private Integer points;
    private ExamScheduleFrontendDTO examScheduleFrontendDTO;
    private StudentFrontendDTO studentFrontendDTO;

    public ExamFrontendDTO(){}

    public ExamFrontendDTO(Exam exam){
        super();
        this.points = exam.getPoints();
        this.examScheduleFrontendDTO = new ExamScheduleFrontendDTO(exam.getExamSchedule());
        this.studentFrontendDTO = new StudentFrontendDTO(exam.getStudent());
    }


    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public ExamScheduleFrontendDTO getExamScheduleDTO() {
        return examScheduleFrontendDTO;
    }

    public void setExamScheduleDTO(ExamScheduleFrontendDTO examScheduleFrontendDTO) {
        this.examScheduleFrontendDTO = examScheduleFrontendDTO;
    }

    public StudentFrontendDTO getStudentDTO() {
        return studentFrontendDTO;
    }

    public void setStudentDTO(StudentFrontendDTO studentFrontendDTO) {
        this.studentFrontendDTO = studentFrontendDTO;
    }
}

package com.ftn.studentservice.service;

import com.ftn.studentservice.model.ExamSchedule;

import java.util.List;

public interface IExamScheduleService {

    ExamSchedule findOne(Long id);

    List<ExamSchedule> findAll();

    ExamSchedule save(ExamSchedule examSchedule);

    void delete(Long id);
}

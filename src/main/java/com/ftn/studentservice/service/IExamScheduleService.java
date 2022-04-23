package com.ftn.studentservice.service;

import com.ftn.studentservice.model.ExamSchedule;
import com.ftn.studentservice.web.dto.ExamScheduleDTO;

import java.util.List;

public interface IExamScheduleService {

    ExamSchedule findOne(Long id);

    List<ExamScheduleDTO> findAll();

    ExamScheduleDTO save(ExamScheduleDTO examScheduleDTO);

    void delete(Long id);
}

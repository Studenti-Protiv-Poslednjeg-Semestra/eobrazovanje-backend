package com.ftn.studentservice.service.implementation;

import com.ftn.studentservice.model.ExamSchedule;
import com.ftn.studentservice.repository.ExamScheduleRepository;
import com.ftn.studentservice.service.IExamScheduleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamScheduleService implements IExamScheduleService {

    private final ExamScheduleRepository examScheduleRepository;

    public ExamScheduleService(ExamScheduleRepository examScheduleRepository) {
        this.examScheduleRepository = examScheduleRepository;
    }

    @Override
    public ExamSchedule findOne(Long id) {
        return examScheduleRepository.findById(id).orElse(null);
    }

    @Override
    public List<ExamSchedule> findAll() {
        return examScheduleRepository.findAll();
    }

    @Override
    public ExamSchedule save(ExamSchedule examSchedule) {
        return examScheduleRepository.save(examSchedule);
    }

    @Override
    public void delete(Long id) {
        examScheduleRepository.deleteById(id);
    }
}

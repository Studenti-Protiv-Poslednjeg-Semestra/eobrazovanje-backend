package com.ftn.studentservice.service;

import com.ftn.studentservice.model.ExaminationPeriod;

import java.util.List;

public interface IExaminationPeriodService {

    ExaminationPeriod findOne(Long id);

    List<ExaminationPeriod> findAll();

    ExaminationPeriod save(ExaminationPeriod examinationPeriod);

    void delete(Long id);
}

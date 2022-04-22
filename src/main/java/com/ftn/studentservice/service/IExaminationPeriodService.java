package com.ftn.studentservice.service;

import com.ftn.studentservice.model.ExaminationPeriod;
import com.ftn.studentservice.web.dto.ExaminationPeriodDTO;

import java.util.List;

public interface IExaminationPeriodService {

    ExaminationPeriod findOne(Long id);

    List<ExaminationPeriodDTO> findAll();

    ExaminationPeriod save(ExaminationPeriod examinationPeriod);

    void delete(Long id);
}

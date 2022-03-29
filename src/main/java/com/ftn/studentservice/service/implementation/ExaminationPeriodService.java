package com.ftn.studentservice.service.implementation;

import com.ftn.studentservice.model.ExaminationPeriod;
import com.ftn.studentservice.repository.ExaminationPeriodRepository;
import com.ftn.studentservice.service.IExaminationPeriodService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExaminationPeriodService implements IExaminationPeriodService {

    private final ExaminationPeriodRepository examinationPeriodRepository;

    public ExaminationPeriodService(ExaminationPeriodRepository examinationPeriodRepository) {
        this.examinationPeriodRepository = examinationPeriodRepository;
    }

    @Override
    public ExaminationPeriod findOne(Long id) {
        return examinationPeriodRepository.findById(id).orElse(null);
    }

    @Override
    public List<ExaminationPeriod> findAll() {
        return examinationPeriodRepository.findAll();
    }

    @Override
    public ExaminationPeriod save(ExaminationPeriod examinationPeriod) {
        return examinationPeriodRepository.save(examinationPeriod);
    }

    @Override
    public void delete(Long id) {
        examinationPeriodRepository.deleteById(id);
    }
}

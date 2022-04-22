package com.ftn.studentservice.service.implementation;

import com.ftn.studentservice.model.ExaminationPeriod;
import com.ftn.studentservice.repository.ExaminationPeriodRepository;
import com.ftn.studentservice.service.IExaminationPeriodService;
import com.ftn.studentservice.util.mapper.ExaminationPeriodMapper;
import com.ftn.studentservice.web.dto.ExaminationPeriodDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExaminationPeriodService implements IExaminationPeriodService {

    private final ExaminationPeriodRepository examinationPeriodRepository;
    private final ExaminationPeriodMapper examinationPeriodMapper;

    public ExaminationPeriodService(ExaminationPeriodRepository examinationPeriodRepository, ExaminationPeriodMapper examinationPeriodMapper) {
        this.examinationPeriodRepository = examinationPeriodRepository;
        this.examinationPeriodMapper = examinationPeriodMapper;
    }


    @Override
    public ExaminationPeriod findOne(Long id) {
        return examinationPeriodRepository.findById(id).orElse(null);
    }

    @Override
    public List<ExaminationPeriodDTO> findAll() {
        return examinationPeriodRepository.findAll().stream().map(examinationPeriodMapper::toDto).collect(Collectors.toList());
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

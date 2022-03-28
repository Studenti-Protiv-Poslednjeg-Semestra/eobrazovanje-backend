package com.ftn.studentservice.service.implementation;

import com.ftn.studentservice.model.Responsibility;
import com.ftn.studentservice.repository.ResponsibilityRepository;
import com.ftn.studentservice.service.IResponsibilityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponsibilityService implements IResponsibilityService {

    private final ResponsibilityRepository responsibilityRepository;

    public ResponsibilityService(ResponsibilityRepository responsibilityRepository) {
        this.responsibilityRepository = responsibilityRepository;
    }

    @Override
    public Responsibility findOne(Long id) {
        return responsibilityRepository.findById(id).orElse(null);
    }

    @Override
    public List<Responsibility> findAll() {
        return responsibilityRepository.findAll();
    }

    @Override
    public Responsibility save(Responsibility responsibility) {
        return responsibilityRepository.save(responsibility);
    }

    @Override
    public void delete(Long id) {
        responsibilityRepository.deleteById(id);
    }
}

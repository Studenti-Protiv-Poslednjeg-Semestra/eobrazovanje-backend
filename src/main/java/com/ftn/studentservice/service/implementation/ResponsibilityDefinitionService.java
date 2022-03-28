package com.ftn.studentservice.service.implementation;

import com.ftn.studentservice.model.ResponsibilityDefinition;
import com.ftn.studentservice.repository.ResponsibilityDefinitionRepository;
import com.ftn.studentservice.service.IResponsibilityDefinitionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponsibilityDefinitionService implements IResponsibilityDefinitionService {

    private final ResponsibilityDefinitionRepository responsibilityDefinitionRepository;

    public ResponsibilityDefinitionService(ResponsibilityDefinitionRepository responsibilityDefinitionRepository) {
        this.responsibilityDefinitionRepository = responsibilityDefinitionRepository;
    }

    @Override
    public ResponsibilityDefinition findOne(Long id) {
        return responsibilityDefinitionRepository.findById(id).orElse(null);
    }

    @Override
    public List<ResponsibilityDefinition> findAll() {
        return responsibilityDefinitionRepository.findAll();
    }

    @Override
    public ResponsibilityDefinition save(ResponsibilityDefinition responsibilityDefinition) {
        return responsibilityDefinitionRepository.save(responsibilityDefinition);
    }

    @Override
    public void delete(Long id) {
        responsibilityDefinitionRepository.deleteById(id);
    }
}

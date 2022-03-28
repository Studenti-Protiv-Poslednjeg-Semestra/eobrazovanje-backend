package com.ftn.studentservice.service;

import com.ftn.studentservice.model.ResponsibilityDefinition;

import java.util.List;

public interface IResponsibilityDefinitionService {

    ResponsibilityDefinition findOne(Long id);

    List<ResponsibilityDefinition> findAll();

    ResponsibilityDefinition save(ResponsibilityDefinition responsibilityDefinition);

    void delete(Long id);
}

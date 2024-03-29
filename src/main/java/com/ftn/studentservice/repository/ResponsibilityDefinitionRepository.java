package com.ftn.studentservice.repository;

import com.ftn.studentservice.model.ResponsibilityDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsibilityDefinitionRepository extends JpaRepository<ResponsibilityDefinition, Long> {
}
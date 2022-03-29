package com.ftn.studentservice.service;

import com.ftn.studentservice.model.Responsibility;

import java.util.List;

public interface IResponsibilityService {

    Responsibility findOne(Long id);

    List<Responsibility> findAll();

    Responsibility save(Responsibility responsibility);

    void delete(Long id);
}

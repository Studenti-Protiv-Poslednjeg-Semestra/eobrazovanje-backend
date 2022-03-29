package com.ftn.studentservice.service;

import com.ftn.studentservice.model.Major;

import java.util.List;

public interface IMajorService {

    Major findOne(Long id);

    List<Major> findAll();

    Major save(Major major);

    void delete(Long id);
}

package com.ftn.studentservice.service;

import com.ftn.studentservice.model.Syllabus;
import com.ftn.studentservice.web.dto.SyllabusDTO;

import java.util.List;

public interface ISyllabusService {

    Syllabus findOne(Long id);

    List<SyllabusDTO> findAll();

    Syllabus save(Syllabus syllabus);

    void delete(Long id);

    int getRemainingSemesterECTS(Long syllabusId, int semesterNumber);
}

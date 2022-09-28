package com.ftn.studentservice.service.implementation;

import com.ftn.studentservice.model.ResponsibilityDefinition;
import com.ftn.studentservice.model.Subject;
import com.ftn.studentservice.repository.ResponsibilityDefinitionRepository;
import com.ftn.studentservice.repository.SubjectRepository;
import com.ftn.studentservice.service.ISubjectService;
import com.ftn.studentservice.util.exception.SubjectCreationException;
import com.ftn.studentservice.util.mapper.SubjectMapper;
import com.ftn.studentservice.web.dto.SubjectDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectService implements ISubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;
    private final SyllabusService syllabusService;

    private final ResponsibilityDefinitionRepository responsibilityDefinitionRepository;
    @Override
    public Subject findOne(Long id) {
        return subjectRepository.findById(id).orElse(null);
    }

    @Override
    public List<SubjectDTO> findAll(Pageable pageable) {
        return subjectRepository.findAll(pageable).stream().map(subjectMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<SubjectDTO> findAll() {
        return subjectRepository.findAll().stream().map(subjectMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<Subject> findAllBySyllabus_Id(Long syllabusId) {
        return subjectRepository.findAllBySyllabus_Id(syllabusId);
    }

    @Override
    public void delete(Long id) {
        subjectRepository.deleteById(id);
    }

    @Override
    @Transient
    public Subject save(Subject subject) {

        int remainingSemesterECTS = syllabusService.getRemainingSemesterECTS(
                subject.getSyllabus().getId(), subject.getSemester());

        if(remainingSemesterECTS < subject.getECTS()) {
            throw new SubjectCreationException("This Subject must contain <= " + remainingSemesterECTS + " ECTS!");
        }

        for(ResponsibilityDefinition rd: subject.getResponsibilityDefinitions()) {
            rd.setSubject(subject);
        }

        try {
            subject = subjectRepository.save(subject);
        } catch (DataIntegrityViolationException ex) {
            throw new SubjectCreationException("Subject's code already exists, please enter the new Subject code and try again!");
        }

//        for(ResponsibilityDefinition rd: subject.getResponsibilityDefinitions()) {
//            responsibilityDefinitionRepository.save(rd);
//        }
        return subject;
    }
}

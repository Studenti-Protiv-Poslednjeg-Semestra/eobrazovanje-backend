package com.ftn.studentservice.service.implementation;

import com.ftn.studentservice.model.Subject;
import com.ftn.studentservice.model.Syllabus;
import com.ftn.studentservice.repository.SubjectRepository;
import com.ftn.studentservice.repository.SyllabusRepository;
import com.ftn.studentservice.service.ISyllabusService;
import com.ftn.studentservice.util.mapper.SyllabusMapper;
import com.ftn.studentservice.web.dto.SyllabusDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SyllabusService implements ISyllabusService {

    private final SyllabusRepository syllabusRepository;
    private final SyllabusMapper syllabusMapper;

    private final SubjectRepository subjectRepository;

    @Override
    public Syllabus findOne(Long id) {
        return syllabusRepository.findById(id).orElse(null);
    }

    @Override
    public List<SyllabusDTO> findAll() {
        return syllabusRepository.findAll().stream().map(syllabusMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Syllabus save(Syllabus syllabus) {
        return syllabusRepository.save(syllabus);
    }

    @Override
    public void delete(Long id) {
        syllabusRepository.deleteById(id);
    }

    @Override
    public int getRemainingSemesterECTS(Long syllabusId, int semesterNumber) {
        Syllabus syllabus = syllabusRepository.getById(syllabusId);

        if (semesterNumber > syllabus.getMajor().getDuration())
            return 0;

        int currentSemesterECTS = syllabus.getSubjects()
                .stream()
                .filter(subject ->
                        subject.getSemester() == semesterNumber)
                .mapToInt(Subject::getECTS)
                .sum();

        return 60 - currentSemesterECTS;
    }
}

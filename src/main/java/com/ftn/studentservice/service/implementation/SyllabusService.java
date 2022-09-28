package com.ftn.studentservice.service.implementation;

import com.ftn.studentservice.model.Subject;
import com.ftn.studentservice.model.Syllabus;
import com.ftn.studentservice.repository.SubjectRepository;
import com.ftn.studentservice.repository.SyllabusRepository;
import com.ftn.studentservice.service.ISyllabusService;
import com.ftn.studentservice.util.exception.InvalidDateException;
import com.ftn.studentservice.util.exception.SyllabusCreationException;
import com.ftn.studentservice.util.mapper.SyllabusMapper;
import com.ftn.studentservice.web.dto.SyllabusDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        return syllabusRepository
                .findAll()
                .stream()
                .map(syllabusMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Syllabus save(Syllabus syllabus) {

        LocalDate startDate, endDate;

        try {
            startDate = LocalDate.of(syllabus.getYearOfCreation().getYear(), 1, 1);
            endDate = LocalDate.of(syllabus.getYearOfCreation().getYear(), 12, 31);
        } catch (DateTimeException ex) {
            throw new InvalidDateException(String.format("Entered date: '%t' is invalid!", syllabus.getYearOfCreation()));
        }

        //Trebas da proveris da li se syllabus nalazi u datom segmentu nekako magicno.
        boolean exists = syllabusRepository
                .existsByMajor_IdAndYearOfCreationBetween(
                    syllabus.getMajor().getId(),
                    startDate,
                    endDate
                );

        if(exists) {
            throw new SyllabusCreationException(
                    String.format(
                            "Selected Major already has Syllabus for the year: '%d' !",
                            syllabus.getYearOfCreation().getYear()));
        }

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

    @Override
    public List<Syllabus> findSyllabiByMajorId(Long majorId) {
        return syllabusRepository.getSyllabusByMajorId(majorId);
    }
}

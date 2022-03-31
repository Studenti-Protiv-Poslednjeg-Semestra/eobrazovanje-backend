package com.ftn.studentservice.service.implementation;

import com.ftn.studentservice.model.Exam;
import com.ftn.studentservice.repository.ExamRepository;
import com.ftn.studentservice.service.IExamService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService implements IExamService {

    private final ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public List<Exam> findByStudentId(Long id) {
        List<Exam> exams = examRepository.findByStudentId(id);
        return exams.isEmpty() ? null : exams;
    }

    @Override
    public Exam findOne(Long id) {
        return examRepository.findById(id).orElse(null);
    }

    @Override
    public List<Exam> findAll() {
        return examRepository.findAll();
    }

    @Override
    public Exam save(Exam exam) {
        return examRepository.save(exam);
    }

    @Override
    public void delete(Long id) {
        examRepository.deleteById(id);
    }
}

package com.ftn.studentservice.service.implementation;

import com.ftn.studentservice.model.Enrollment;
import com.ftn.studentservice.repository.EnrollmentRepository;
import com.ftn.studentservice.service.IEnrollmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService implements IEnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public List<Enrollment> findByStudentId(Long id) {
        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(id);
        return enrollments.isEmpty() ? null : enrollments;
    }

    @Override
    public Page<Enrollment> findByStudentId(Long id, PageRequest pageRequest) {

        Page<Enrollment> enrollments = enrollmentRepository.findByStudentId(id, pageRequest);
        return enrollments;
    }

    @Override
    public List<Enrollment> getFinishedExams(Long studentId) {
        Integer passingGrade = 6;
        return enrollmentRepository.findEnrollmentsByStudent_IdAndGradeGreaterThan(studentId, passingGrade);
    }

    @Override
    public Enrollment findOne(Long id) {
        return enrollmentRepository.findById(id).orElse(null);
    }

    @Override
    public Enrollment findByStudentIdAndSubjectId(Long studentId, Long subjectId) {
        return enrollmentRepository.findByStudentIdAndSubjectId(studentId, subjectId);
    }

    @Override
    public List<Enrollment> findAll() {
        return enrollmentRepository.findAll();
    }

    @Override
    public Enrollment save(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public void delete(Long id) {
        enrollmentRepository.deleteById(id);
    }
}

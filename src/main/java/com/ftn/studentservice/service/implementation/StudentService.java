package com.ftn.studentservice.service.implementation;

import com.ftn.studentservice.model.Enrollment;
import com.ftn.studentservice.model.Student;
import com.ftn.studentservice.model.Subject;
import com.ftn.studentservice.model.Syllabus;
import com.ftn.studentservice.repository.EnrollmentRepository;
import com.ftn.studentservice.repository.StudentRepository;
import com.ftn.studentservice.repository.SyllabusRepository;
import com.ftn.studentservice.service.IStudentService;
import com.ftn.studentservice.util.exception.EntityNotFoundException;
import com.ftn.studentservice.util.mapper.StudentMapper;
import com.ftn.studentservice.web.dto.StudentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService {

    private final StudentRepository studentRepository;
    private final SyllabusRepository syllabusRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, SyllabusRepository syllabusRepository, EnrollmentRepository enrollmentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.syllabusRepository = syllabusRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public Student findOne(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public StudentDTO addStudentToSyllabus(Long studentId, Long syllabusId) {

        Student student = studentRepository.findById(studentId).orElse(null);
        Syllabus syllabus = syllabusRepository.findById(syllabusId).orElse(null);

        if(student == null)
            throw new EntityNotFoundException("Student not found");

        if(syllabus == null)
            throw new EntityNotFoundException("Syllabus not found");

        student.setSemester(1);
        student.setMajor(syllabus.getMajor());

        Enrollment enrollment;

        for (Subject subject : syllabus.getSubjects()) {
            if(subject.getSemester() == 1){
                enrollment = new Enrollment();
                enrollment.setStudent(student);
                enrollment.setSubject(subject);
                enrollment = enrollmentRepository.save(enrollment);
                student.getEnrollments().add(enrollment);
            }
        }

        student = studentRepository.save(student);

        return studentMapper.toDto(student);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}

package com.ftn.studentservice.service.implementation;

import com.ftn.studentservice.model.*;
import com.ftn.studentservice.repository.*;
import com.ftn.studentservice.service.IStudentService;
import com.ftn.studentservice.util.exception.EntityNotFoundException;
import com.ftn.studentservice.util.exception.NotEnoughFunds;
import com.ftn.studentservice.util.mapper.StudentMapper;
import com.ftn.studentservice.web.dto.StudentDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentService implements IStudentService {

    private static final Double PRICE = 2000.00;

    private final StudentRepository studentRepository;
    private final SyllabusRepository syllabusRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final MajorRepository majorRepository;
    private final PaymentService paymentService;
    private final StudentMapper studentMapper;
    private final AuthorityRepository authorityRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public StudentService(StudentRepository studentRepository, SyllabusRepository syllabusRepository, EnrollmentRepository enrollmentRepository, MajorRepository majorRepository, PaymentService paymentService, StudentMapper studentMapper, AuthorityRepository authorityRepository) {
        this.studentRepository = studentRepository;
        this.syllabusRepository = syllabusRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.majorRepository = majorRepository;
        this.paymentService = paymentService;
        this.studentMapper = studentMapper;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public Student findOne(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream().map(studentMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> findAllPageAndSize(PageRequest request) {
        return studentRepository.findAll(request).stream().map(studentMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> findNewStudents() {
        System.err.println(studentRepository.findAllBySyllabus(null));
        return studentRepository.findAllBySyllabus(null).stream().map(studentMapper::toDto).collect(Collectors.toList());
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
        student.setSyllabus(syllabus);
        student.setMajor(syllabus.getMajor());

        syllabus.getMajor().getStudents().add(student);
        majorRepository.save(syllabus.getMajor());

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
    public StudentDTO enrollmentOnNextSemester(Long studentId) {

        Student student = studentRepository.findById(studentId).orElse(null);

        if(student == null)
            throw new EntityNotFoundException("Student not found");

        if(student.getFunds() >= PRICE){
            student.setFunds(student.getFunds() - PRICE);
        }else {
            throw new NotEnoughFunds("Not enough funds.");
        }

        if(student.getSemester() == student.getMajor().getDuration()){
            student = studentRepository.save(student);
            return studentMapper.toDto(student);
        }

        if(student.getSemester() < student.getMajor().getDuration())
            student.setSemester(student.getSemester() + 1);

        Payment payment = new Payment(-PRICE, LocalDateTime.now(), "Overa semestra", student);
        paymentService.save(payment);

        Enrollment enrollment;

        for (Subject subject : student.getSyllabus().getSubjects()) {
            if(student.getSemester() == subject.getSemester()){
                enrollment = new Enrollment();
                enrollment.setSubject(subject);
                enrollment.setStudent(student);

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
    public StudentDTO create(StudentDTO student) {
        Student studentForSave = studentMapper.toEntity(student);
        Set<Authority> authorities = new HashSet<>();
        authorities.add(authorityRepository.getAuthorityByName("ROLE_STUDENT"));
        studentForSave.getUser().setAuthorities(authorities);
        studentForSave.getUser().setPassword(bCryptPasswordEncoder.encode(student.getUserDTO().getUCN()));
        Student retVal = studentRepository.save(studentForSave);
        return studentMapper.toDto(retVal);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}

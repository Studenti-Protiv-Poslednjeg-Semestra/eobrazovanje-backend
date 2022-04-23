package com.ftn.studentservice.service.implementation;

import com.ftn.studentservice.model.Student;
import com.ftn.studentservice.repository.StudentRepository;
import com.ftn.studentservice.service.IStudentService;
import com.ftn.studentservice.util.mapper.StudentMapper;
import com.ftn.studentservice.web.dto.StudentDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService implements IStudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
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
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}

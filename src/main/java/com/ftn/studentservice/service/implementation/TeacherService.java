package com.ftn.studentservice.service.implementation;

import com.ftn.studentservice.model.Teacher;
import com.ftn.studentservice.repository.TeacherRepository;
import com.ftn.studentservice.service.ITeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService implements ITeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher findOne(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }
}

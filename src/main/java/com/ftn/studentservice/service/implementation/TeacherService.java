package com.ftn.studentservice.service.implementation;

import com.ftn.studentservice.model.Teacher;
import com.ftn.studentservice.repository.TeacherRepository;
import com.ftn.studentservice.service.ITeacherService;
import com.ftn.studentservice.util.exception.EntityNotFoundException;
import com.ftn.studentservice.util.mapper.SubjectMapper;
import com.ftn.studentservice.util.mapper.TeacherMapper;
import com.ftn.studentservice.web.dto.SubjectDTO;
import com.ftn.studentservice.web.dto.TeacherDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService implements ITeacherService {

    private final TeacherRepository teacherRepository;
    private final SubjectMapper subjectMapper;
    private final TeacherMapper teacherMapper;

    public TeacherService(TeacherRepository teacherRepository, SubjectMapper subjectMapper, TeacherMapper teacherMapper) {
        this.teacherRepository = teacherRepository;
        this.subjectMapper = subjectMapper;
        this.teacherMapper = teacherMapper;
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
    public TeacherDTO addProfessorToSubject(Long teacherId, SubjectDTO subjectDTO) {

        Teacher professor = teacherRepository.findById(teacherId).orElse(null);

        if(professor == null){
            throw new EntityNotFoundException("Teacher is not found");
        }

        professor.getProfessorOn().add(subjectMapper.toEntity(subjectDTO));
        teacherRepository.save(professor);

        return teacherMapper.toDto(professor);
    }

    @Override
    public TeacherDTO addAssistantToSubject(Long teacherId, SubjectDTO subjectDTO) {
        Teacher assistant = teacherRepository.findById(teacherId).orElse(null);

        if(assistant == null){
            throw new EntityNotFoundException("Teacher is not found");
        }

        assistant.getAssistantOn().add(subjectMapper.toEntity(subjectDTO));
        teacherRepository.save(assistant);

        return teacherMapper.toDto(assistant);
    }

    @Override
    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }
}

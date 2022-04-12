package com.ftn.studentservice.service.implementation;

import com.ftn.studentservice.model.Subject;
import com.ftn.studentservice.model.Teacher;
import com.ftn.studentservice.repository.SubjectRepository;
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
    private final SubjectRepository subjectRepository;
    private final TeacherMapper teacherMapper;

    public TeacherService(TeacherRepository teacherRepository, SubjectRepository subjectRepository, TeacherMapper teacherMapper) {
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
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
    public TeacherDTO addProfessorToSubject(Long teacherId, Long subjectId) {

        Teacher professor = teacherRepository.findById(teacherId).orElse(null);
        Subject subject = subjectRepository.findById(subjectId).orElse(null);

        if(professor == null || subject == null){
            throw new EntityNotFoundException("Professor or subject is not found");
        }

        professor.getProfessorOn().add(subject);
        subject.getProfessors().add(professor);
        teacherRepository.save(professor);
        subjectRepository.save(subject);

        return teacherMapper.toDto(professor);
    }

    @Override
    public TeacherDTO addAssistantToSubject(Long teacherId, Long subjectId) {
        Teacher assistant = teacherRepository.findById(teacherId).orElse(null);
        Subject subject = subjectRepository.findById(subjectId).orElse(null);

        if(assistant == null || subject == null){
            throw new EntityNotFoundException("Assistant or subject is not found");
        }

        assistant.getAssistantOn().add(subject);
        subject.getAssistants().add(assistant);
        teacherRepository.save(assistant);
        subjectRepository.save(subject);

        return teacherMapper.toDto(assistant);
    }

    @Override
    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }
}

package com.ftn.studentservice.service.implementation;

import com.ftn.studentservice.model.Authority;
import com.ftn.studentservice.model.Subject;
import com.ftn.studentservice.model.Teacher;
import com.ftn.studentservice.repository.AuthorityRepository;
import com.ftn.studentservice.repository.SubjectRepository;
import com.ftn.studentservice.repository.TeacherRepository;
import com.ftn.studentservice.service.ITeacherService;
import com.ftn.studentservice.util.exception.EntityNotFoundException;
import com.ftn.studentservice.util.mapper.TeacherMapper;
import com.ftn.studentservice.web.dto.TeacherDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TeacherService implements ITeacherService {

    private static final Integer SIZE = 10;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final AuthorityRepository authorityRepository;
    private final TeacherMapper teacherMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public TeacherService(TeacherRepository teacherRepository, SubjectRepository subjectRepository, AuthorityRepository authorityRepository, TeacherMapper teacherMapper) {
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
        this.authorityRepository = authorityRepository;
        this.teacherMapper = teacherMapper;
    }

    @Override
    public Teacher findOne(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    @Override
    public List<TeacherDTO> findAll(Integer page) {
        return teacherRepository.findAll(PageRequest.of(page, SIZE)).stream().map(teacherMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public TeacherDTO save(TeacherDTO teacher) {
        Teacher teacherForSave = teacherMapper.toEntity(teacher);
        Set<Authority> authorities = new HashSet<>();
        authorities.add(authorityRepository.getAuthorityByName("ROLE_TEACHER"));
        teacherForSave.getUser().setPassword(bCryptPasswordEncoder.encode(teacher.getUserDTO().getUCN()));
        teacherForSave.getUser().setAuthorities(authorities);
        Teacher retVal = teacherRepository.save(teacherForSave);
        return teacherMapper.toDto(retVal);
    }

    @Override
    public TeacherDTO addProfessorToSubject(Long teacherId, Long subjectId) {

        Teacher professor = teacherRepository.findById(teacherId).orElse(null);
        Subject subject = subjectRepository.findById(subjectId).orElse(null);

        if (professor == null || subject == null) {
            throw new EntityNotFoundException("Professor or subject is not found");
        }

        professor.getProfessorOn().add(subject);
        subject.getProfessors().add(professor);
        teacherRepository.save(professor);
        subjectRepository.save(subject);

        TeacherDTO teacherDTO = teacherMapper.toDto(professor);

        return teacherDTO;
    }

    @Override
    public TeacherDTO addAssistantToSubject(Long teacherId, Long subjectId) {
        Teacher assistant = teacherRepository.findById(teacherId).orElse(null);
        Subject subject = subjectRepository.findById(subjectId).orElse(null);

        if (assistant == null || subject == null) {
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

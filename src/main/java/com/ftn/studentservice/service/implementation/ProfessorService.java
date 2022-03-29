package com.ftn.studentservice.service.implementation;

import com.ftn.studentservice.model.Professor;
import com.ftn.studentservice.repository.ProfessorRepository;
import com.ftn.studentservice.service.IProfessorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService implements IProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public Professor findOne(Long id) {
        return professorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    @Override
    public Professor save(Professor professor) {
        return professorRepository.save(professor);
    }

    @Override
    public void delete(Long id) {
        professorRepository.deleteById(id);
    }
}

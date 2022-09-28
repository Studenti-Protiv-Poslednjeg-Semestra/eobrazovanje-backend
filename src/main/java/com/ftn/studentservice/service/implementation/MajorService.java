package com.ftn.studentservice.service.implementation;

import com.ftn.studentservice.model.Major;
import com.ftn.studentservice.repository.MajorRepository;
import com.ftn.studentservice.service.IMajorService;
import com.ftn.studentservice.util.exception.MajorCreationException;
import com.ftn.studentservice.util.exception.SubjectCreationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorService implements IMajorService {

    private final MajorRepository majorRepository;

    public MajorService(MajorRepository majorRepository) {
        this.majorRepository = majorRepository;
    }

    @Override
    public Major findOne(Long id) {
        return majorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Major> findAll() {
        return majorRepository.findAll();
    }

    @Override
    public Major save(Major major) {

        boolean majorNameConflict = majorRepository.existsByName(major.getName());

        if(majorNameConflict) {
            throw new MajorCreationException("Major name already exists, please enter the new Major name and try again!");
        }

        return majorRepository.save(major);
    }

    @Override
    public void delete(Long id) {
        majorRepository.deleteById(id);
    }
}

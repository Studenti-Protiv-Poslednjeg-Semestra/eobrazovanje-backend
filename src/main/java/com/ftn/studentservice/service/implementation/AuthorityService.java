package com.ftn.studentservice.service.implementation;

import com.ftn.studentservice.model.Authority;
import com.ftn.studentservice.repository.AuthorityRepository;
import com.ftn.studentservice.service.IAuthorityService;

import java.util.List;

public class AuthorityService implements IAuthorityService {

    private final AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public List<Authority> getAuthorities() {
        return authorityRepository.findAll();
    }

    @Override
    public Authority getAuthorityByName(String name) {
        return authorityRepository.getAuthorityByName(name);
    }
}

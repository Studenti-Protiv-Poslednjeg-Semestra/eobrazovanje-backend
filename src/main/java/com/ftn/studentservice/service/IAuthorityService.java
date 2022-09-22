package com.ftn.studentservice.service;

import com.ftn.studentservice.model.Authority;

import java.util.List;

public interface IAuthorityService {

    List<Authority> getAuthorities();

    Authority getAuthorityByName(String name);
}

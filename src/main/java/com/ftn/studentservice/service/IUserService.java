package com.ftn.studentservice.service;

import com.ftn.studentservice.model.User;

public interface IUserService {

    User findOne(Long id);

    User findUserByEmail(String username);

}

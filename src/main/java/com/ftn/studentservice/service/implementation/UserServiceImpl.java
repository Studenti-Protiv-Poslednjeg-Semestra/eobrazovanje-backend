package com.ftn.studentservice.service.implementation;

import com.ftn.studentservice.model.User;
import com.ftn.studentservice.repository.UserRepository;
import com.ftn.studentservice.service.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

import static java.lang.String.format;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException(format("User with email - %s, not found", email))
                );
    }

    @Override
    public User findOne(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new NoSuchElementException("User with id = " + id + " not found!");
        }
        return user.get();
    }

    @Override
    public User findUserByEmail(String username) {
        Optional<User> user = userRepository.findByEmail(username);

        return user.orElse(null);
    }
}

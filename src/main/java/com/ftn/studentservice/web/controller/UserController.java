package com.ftn.studentservice.web.controller;

import com.ftn.studentservice.model.User;
import com.ftn.studentservice.service.IUserService;
import com.ftn.studentservice.util.mapper.UserMapper;
import com.ftn.studentservice.web.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@RestController
@RequestMapping(value = "users")
public class UserController {

    private final IUserService iUserService;
    private final UserMapper userMapper;

    public UserController(IUserService iUserService, UserMapper userMapper) {
        this.iUserService = iUserService;
        this.userMapper = userMapper;
    }


    @PreAuthorize("hasAnyRole('ADMIN','TEACHER','STUDENT')")
    @GetMapping(value = "{id}")
    public ResponseEntity<UserDTO> getPersonalData(@PathVariable Long id) {
        User user  = iUserService.findOne(id);
        if (user == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<UserDTO>(userMapper.toDto(user), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @PutMapping
    public ResponseEntity<UserDTO> editPersonalData(@RequestBody EditUserDTO editUserDTO) {
        User user  = iUserService.findOne(editUserDTO.getId());
        if (user == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        user.setFirstName(editUserDTO.getFirstName());
        user.setLastName(editUserDTO.getLastName());
        user.setEmail(editUserDTO.getEmail());
        if (editUserDTO.getPassword() != null && !editUserDTO.getPassword().equals("")) {
            String newPassword = new BCryptPasswordEncoder().encode(editUserDTO.getPassword());
            System.out.println("New raw password: "+ editUserDTO.getPassword());
            System.out.println("New encoded password: "+ newPassword);
            user.setPassword(newPassword);
        }

        return new ResponseEntity<UserDTO>(userMapper.toDto(iUserService.save(user)), HttpStatus.OK);
    }

}

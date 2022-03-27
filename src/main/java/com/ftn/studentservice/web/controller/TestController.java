package com.ftn.studentservice.web.controller;

import com.ftn.studentservice.model.User;
import com.ftn.studentservice.service.IUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    private final IUserService userService;

    public TestController(IUserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("test")
    public String helloUser() {
        User user = userService.findOne(1L);
        System.out.println("Found user " + user);
        return "Hello User";
    }
}

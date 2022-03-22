package com.ftn.studentservice.web.controller;

import com.ftn.studentservice.model.User;
import com.ftn.studentservice.security.jwt.JWTResponse;
import com.ftn.studentservice.security.jwt.JwtTokenUtil;
import com.ftn.studentservice.service.IUserService;
import com.ftn.studentservice.web.dto.LoginDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final IUserService userService;
    private final JwtTokenUtil tokenUtil;

    public AuthController(AuthenticationManager authenticationManager, IUserService userService, JwtTokenUtil tokenUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenUtil = tokenUtil;
    }

    // https://www.browserling.com/tools/bcrypt 10x - Valid BCrypt
    @PostMapping(value = "login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        System.out.println("Login hit!");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        String jwt = tokenUtil.generateAccessToken(user);
        JWTResponse jwtResponse = new JWTResponse(jwt);

        return ResponseEntity.ok(jwtResponse);
    }


}




















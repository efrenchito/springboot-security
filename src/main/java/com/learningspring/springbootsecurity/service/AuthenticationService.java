package com.learningspring.springbootsecurity.service;

import com.learningspring.springbootsecurity.model.dto.LoginDto;
import com.learningspring.springbootsecurity.model.dto.SignupDto;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {

    ResponseEntity<String> signup(SignupDto signupDto);
}
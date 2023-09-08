package com.learningspring.springbootsecurity.controller;

import com.learningspring.springbootsecurity.model.dto.LoginDto;
import com.learningspring.springbootsecurity.model.dto.SignupDto;
import com.learningspring.springbootsecurity.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupDto signupDto) {
        return authenticationService.signup(signupDto);
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestBody LoginDto loginDto) {
        return authenticationService.login(loginDto);
    }

}

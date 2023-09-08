package com.learningspring.springbootsecurity.service;

import com.learningspring.springbootsecurity.model.dto.LoginDto;
import com.learningspring.springbootsecurity.model.dto.SignupDto;
import com.learningspring.springbootsecurity.model.entity.Role;
import com.learningspring.springbootsecurity.model.entity.User;
import com.learningspring.springbootsecurity.repository.RoleRepository;
import com.learningspring.springbootsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<String> signup(SignupDto signupDto) {
        String username = signupDto.getUsername();
        if(userRepository.existsByUsername(username)) {
            return new ResponseEntity<>("'username' is already taken!" , HttpStatus.BAD_REQUEST);
        }

        String email = signupDto.getEmail();
        if(userRepository.existsByEmail(email)) {
            return new ResponseEntity<>("'email' is already taken!!", HttpStatus.BAD_REQUEST);
        }

        User user = User.builder().name(signupDto.getName()).username(username)
                .password(passwordEncoder.encode(signupDto.getPassword())).email(email).build();

        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User signed-up successfully!", HttpStatus.OK);
    }

}

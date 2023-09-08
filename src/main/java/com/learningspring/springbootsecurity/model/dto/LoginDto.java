package com.learningspring.springbootsecurity.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}

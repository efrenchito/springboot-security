package com.learningspring.springbootsecurity.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupDto {
    private String name;
    private String username;
    private String password;
    private String email;
}

package com.learningspring.springbootsecurity.model.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private long id;
    private String name;
    private String username;
    private String password;
    private String email;
}

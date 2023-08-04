package com.learningspring.springbootsecurity.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {
    private String firstName;
    private String lastName;
    private String email;
}
package com.learningspring.springbootsecurity.model.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    private long id;
    private String name;
}

package com.fproject.sas.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    @Enumerated(value = EnumType.STRING)
    private UserType userType;
    private String username;
    private String email;
    private String password;
    private String submittedBy;
    private String updatedAt;
}

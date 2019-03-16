package com.fproject.sas.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Entity
@Table(name = "DEPARTMENT")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String departmentId;
    private String departmentName;
    private String submittedBy;
    private String updatedAt;
//    @OneToMany(mappedBy = "department")
}

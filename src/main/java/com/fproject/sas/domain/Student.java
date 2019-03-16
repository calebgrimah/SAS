package com.fproject.sas.domain;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String studentId;
    private String studentName;
    private String departmentId;
    private String studentClass;
//    @Lob
//    private String studentPhoto;
    private String submittedBy;
    private String updatedAt;
}

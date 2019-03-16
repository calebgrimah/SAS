package com.fproject.sas.controller.model;

import com.fproject.sas.domain.Student;
import lombok.Data;

@Data
public class StudentRquestBodyUpdate {
    private String studentId;
    private String username;
    private Student student;
}

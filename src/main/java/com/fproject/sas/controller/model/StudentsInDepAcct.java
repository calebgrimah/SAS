package com.fproject.sas.controller.model;

import com.fproject.sas.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StudentsInDepAcct {
    List<Student> students;
}

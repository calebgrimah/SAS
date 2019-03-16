package com.fproject.sas.controller.model;

import com.fproject.sas.domain.Student;
import lombok.Data;

import java.util.List;

@Data
public class CourseRosterMultipleStudents {
    List<Student> students;
}

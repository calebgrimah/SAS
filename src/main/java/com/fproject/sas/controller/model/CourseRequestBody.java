package com.fproject.sas.controller.model;

import com.fproject.sas.domain.Course;
import lombok.Data;

@Data
public class CourseRequestBody {
    private String username;
    private String departmentId;
    private Course course;
}

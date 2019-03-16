package com.fproject.sas.controller.model;

import com.fproject.sas.domain.Course;
import lombok.Data;

import java.util.List;

@Data
public class CourseResponseBody {

    List<Course> courses;
}

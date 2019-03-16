package com.fproject.sas.controller.model;

import com.fproject.sas.domain.Course;
import com.fproject.sas.domain.CourseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CoursesResponseBody {
    List<CourseResponse> courses;
}

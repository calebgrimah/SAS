package com.fproject.sas.domain;

import lombok.Data;

@Data
public class CourseResponse {
    private String courseId;
    private String courseName;
    private Semester semester;
    private String level;
    private Integer lectureSlots;
    private String departmentId;
}

package com.fproject.sas.controller.model;

import com.fproject.sas.domain.Semester;
import lombok.Data;

@Data
public class AttendanceForASingleCourseInADay {
    private String courseId;
    private String studentId;
}

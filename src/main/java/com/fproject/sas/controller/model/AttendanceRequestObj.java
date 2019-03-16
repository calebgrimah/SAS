package com.fproject.sas.controller.model;

import com.fproject.sas.domain.Semester;
import lombok.Data;

@Data
public class AttendanceRequestObj {
    //represents a single attendance record for a student in a day
    private String departmentId;
    private String courseId;
    private boolean isPresent;
    private String studentId;
    private Long date;
    private Semester semester;
}

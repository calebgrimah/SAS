package com.fproject.sas.controller.model;

import com.fproject.sas.domain.Attendance;
import lombok.Data;

import java.util.List;

@Data
public class AttendanceCreateRequestBody {
    private String courseId;
    List<Attendance> attendanceList;
}

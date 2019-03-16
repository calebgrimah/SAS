package com.fproject.sas.controller.model;

import lombok.Data;

import java.util.List;

@Data
public class AttendanceCreateRequest {
    private String courseId;
    List<AttendanceBody> attendanceBodies;
}

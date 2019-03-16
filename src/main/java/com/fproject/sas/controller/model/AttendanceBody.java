package com.fproject.sas.controller.model;

import lombok.Data;

@Data
public class AttendanceBody {
    private String studentId;
    private boolean isPresent;

}

package com.fproject.sas.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class AttendanceRecordForADay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String attendanceDate;
    private String courseId;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ATTENDANCE_RECORD_FOR_DAY_ID",nullable = false)
    private List<Attendance> attendances = new ArrayList<>();
}

package com.fproject.sas.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Attendance {
    //represents a single attendance record for a student in a day
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ATTENDANCE_ID")
    private Long id;
    @Column(name = "IS_PRESENT")
    private boolean isPresent;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "STUDENT_RECORD_ID")
    private Student student;

    public Attendance(boolean isPresent, Student student) {
        this.isPresent = isPresent;
        this.student = student;
    }
}

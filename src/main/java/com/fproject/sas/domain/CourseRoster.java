package com.fproject.sas.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class CourseRoster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseId;
    private String studentId;
    private String coursestudentId;

    public CourseRoster() {
    }
    public CourseRoster(String courseId, String studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }
}

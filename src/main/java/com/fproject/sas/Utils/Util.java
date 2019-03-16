package com.fproject.sas.Utils;

import com.fproject.sas.domain.Course;
import com.fproject.sas.domain.CourseResponse;

public class Util {
    public static CourseResponse changeState(Course course){
        CourseResponse response = new CourseResponse();
        response.setCourseId(course.getCourseId());
        response.setCourseName(course.getCourseName());
        response.setDepartmentId(course.getDepartment().getDepartmentId());
        response.setLectureSlots(course.getLectureSlots());
        response.setLevel(course.getLevel());
        response.setSemester(course.getSemester());
        return response;
    }
}

package com.fproject.sas.repository;

import com.fproject.sas.domain.CourseRoster;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRosterRepository extends CrudRepository<CourseRoster,Long> {
    //find one or multiple student id associated with a course or vice versa
    CourseRoster findFirstByCourseId(String courseId);
    List<CourseRoster> findByCourseId(String courseId);
    CourseRoster findFirstByStudentId(String studentId);
    List<CourseRoster> findByStudentId(String studentId);

    //get the number of courses a studen is doing
    //find all course roster records where student id == supplied student id
}

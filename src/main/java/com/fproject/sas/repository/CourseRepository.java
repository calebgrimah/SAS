package com.fproject.sas.repository;

import com.fproject.sas.domain.Course;
import com.fproject.sas.domain.Department;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course,Long> {

    Course findFirstByCourseId(String courseId);

    List<Course> findByDepartment(Department department);

}

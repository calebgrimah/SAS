package com.fproject.sas.repository;

import com.fproject.sas.domain.Attendance;
import com.fproject.sas.domain.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface StudentRepository extends CrudRepository<Student,Long> {
    Student findFirstByStudentId(String studentId);

    @Query("select a from Student a where a.departmentId = :departmentId")
    List<Student> getAllStudentsInAParticularDepartment(@Param("departmentId")String departmentId);

}

package com.fproject.sas.repository;

import com.fproject.sas.domain.Attendance;
import com.fproject.sas.domain.Course;
import com.fproject.sas.domain.Semester;
import com.fproject.sas.domain.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

//@Repository
public interface AttendanceRepository extends CrudRepository<Attendance,Long> {

//    //Search for the attendance for a single student for a particular date
//    @Query("select a from Attendance a where a.attendanceDate = :date and a.student = :student")
//    Attendance getStudentAttendanceWithDateAndStudentId(@Param("date")Date date , @Param("student")Student student);
//
//    //Search for the attendance record for a particular date
//    @Query("select a from Attendance a where a.attendanceDate = :date")
//    List<Attendance> getStudentAttendanceForAParticularDate(@Param("date")Date date);
//
//    //Search for the attendance records for a course for a particular date
//    @Query("select a from Attendance a where a.attendanceDate = :date and a.course = :course and a.semester = :semester and a.student = :student")
//    List<Attendance> getStudentAttendanceWithDateAndCourse(@Param("date")Long date , @Param("course")Course course, @Param("semester")Semester semester, @Param("student")Student student);
//
//    //Search for the attendance records for a single student in a particular course and semester
//    @Query("select a from Attendance a where a.semester = :semester and a.course = :course and a.student = :student")
//    List<Attendance> getTotalAttendanceForStudentInSemester(@Param("semester")Semester semester , @Param("course")Course course, @Param("student")Student student);

}

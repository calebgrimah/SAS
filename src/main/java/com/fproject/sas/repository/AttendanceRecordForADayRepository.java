package com.fproject.sas.repository;

import com.fproject.sas.domain.AttendanceRecordForADay;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AttendanceRecordForADayRepository extends CrudRepository<AttendanceRecordForADay,Long> {

//    @Query("select a from AttendanceRecordForADay a where a.courseId =:courseId and a.course = :course and a.semester = :semester and a.student = :student")
//    List<AttendanceRecordForADay> getAttendanceForAStudentDoingAPArticularCourse(@Param("courseId")String courseId, @Param("studentId")String studentId);

    List<AttendanceRecordForADay> findByCourseIdAndAttendances_Student_StudentId(String courseId, String studentId);

    AttendanceRecordForADay findByCourseIdAndAttendanceDate (String courseId, String attendanceDate);
}

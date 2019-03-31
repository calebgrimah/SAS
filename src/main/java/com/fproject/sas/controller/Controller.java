package com.fproject.sas.controller;


import com.fproject.sas.Utils.Util;
import com.fproject.sas.controller.model.*;
import com.fproject.sas.domain.*;
import com.fproject.sas.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin
@RestController
public class Controller {

    private AttendanceRepository attendanceRepository;
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;
    private UserRepository userRepository;
    private DepartmentRepository departmentRepository;
    private CourseRosterRepository courseRosterRepository;
    private AttendanceRecordForADayRepository attendanceRecordForADayRepository;

    public Controller(AttendanceRepository attendanceRepository, CourseRepository courseRepository,
                      StudentRepository studentRepository, UserRepository userRepository,
                      DepartmentRepository departmentRepository, CourseRosterRepository courseRosterRepository, AttendanceRecordForADayRepository attendanceRecordForADayRepository) {
        this.attendanceRepository = attendanceRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.courseRosterRepository = courseRosterRepository;
        this.attendanceRecordForADayRepository = attendanceRecordForADayRepository;
    }

    //LOGIN
    /*
     * check users table and retrieve user with supplied id and password
     * */
    @PostMapping(value = "/login",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody LoginRequestBody loginBody) {
//        Iterable<AuditRecord> allAuditRecords = auditRepository.findAll();
//        List<AuditRecord> auditRecordList = StreamSupport.stream(allAuditRecords.spliterator(), false)
//                .collect(Collectors.toList());
        User user = userRepository.findFirstByUsername(loginBody.getUsername());
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(user.getPassword().equalsIgnoreCase(loginBody.getPassword())){
            //passwords match
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    //COURSES
    /*1-create course
    * 2.delete course
    * 3.view courses
    * 4.update course
    * */
    @PostMapping(value = "/course",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCourse(@RequestBody CourseRequestBody courseRequestBody) {

        User user = userRepository.findFirstByUsername(courseRequestBody.getUsername());
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Department firstByDepartmentId = departmentRepository.findFirstByDepartmentId(courseRequestBody.getDepartmentId());
        if(firstByDepartmentId == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(user.getUserType() == UserType.HOD || user.getUserType() == UserType.ADMIN || user.getUserType() == UserType.DEAN){
            //allow to create course

            Course course = courseRequestBody.getCourse();
            Course firstByCourseId = courseRepository.findFirstByCourseId(course.getCourseId());
            if (firstByCourseId != null){
                //this course exists
                return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
            }
            course.setDepartment(firstByDepartmentId);
            courseRepository.save(course);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    @DeleteMapping(value = "/course/{courseId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteCourse(@PathVariable String courseId) {

        Course course = courseRepository.findFirstByCourseId(courseId);
        if(course == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        courseRepository.delete(course);
        return new ResponseEntity<>(HttpStatus.OK);
    }
//    @GetMapping(value = "/course",produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> getAllCourses() {
//        List<CourseResponse> courses = StreamSupport.stream(courseRepository.findAll().spliterator(), false)
//                .map(Util::changeState)
//                .collect(Collectors.toList());
//        if(courses == null){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(new CoursesResponseBody(courses),HttpStatus.OK);
//    }
    @GetMapping(value = "/course/{courseId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSingleCourse(@PathVariable String courseId) {
        Course course = courseRepository.findFirstByCourseId(courseId);
        if(course == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CourseResponse response = Util.changeState(course);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping(value = "/course/department/{departmentId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllCoursesInADepartment(@PathVariable String departmentId) {
        Department department = departmentRepository.findFirstByDepartmentId(departmentId);
        if(department == null)return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        List<Course> course = courseRepository.findByDepartment(department);
        if(course == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CourseResponseBody courseResponseBody = new CourseResponseBody();
        courseResponseBody.setCourses(course);
        //CourseResponse response = Util.changeState(course);
        return new ResponseEntity<>(courseResponseBody,HttpStatus.OK);
    }



    //ATTENDANCE
    /*
    * 1.retrieve attendance for a single student in on a particular date
    * 1.1.retrieve the total attendance for a single student in a particular course
    * 2.retrieve attendance for a course in particular date
    * 3.create attendance for a student in a date
    * 4.retrieve a list of all students in a particular course and create an attendance record for them in a given date
    * 5.for each student in the request body, create an attendance record for the particular date
    * 6. retrieve the total attendance for a course
    * 7.*/
    @PostMapping(value = "/attendance",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> retrieveAttendanceForSingleStudent(@RequestBody AttendanceRequestBody attendanceRequestBody) {

        Student student = studentRepository.findFirstByStudentId(attendanceRequestBody.getStudentId());
        if(student == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Date date = new Date();
        date.setTime(attendanceRequestBody.getDate());
//        Attendance attendance = attendanceRepository.getStudentAttendanceWithDateAndStudentId(date,student);
//        if(attendance == null){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        /*attendance*/
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    @PostMapping(value = "/attendance/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAttendanceForAStudentDoingAParticularCourse(@RequestBody AttendanceForASingleCourseInADay dateCourse) {
        List<AttendanceRecordForADay> byCourseIdAndAttendances_student_studentId = attendanceRecordForADayRepository.findByCourseIdAndAttendances_Student_StudentId(dateCourse.getCourseId(), dateCourse.getStudentId());
        if(byCourseIdAndAttendances_student_studentId == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new AttendanceResponseBody(byCourseIdAndAttendances_student_studentId),HttpStatus.OK);

    }
    //CREATE ATTENDANCE LIST
    @PostMapping(value = "/attendance/create",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAttendanceRecordForACourseInADay(@RequestBody AttendanceCreateRequest createRequest) {
        //get the list of attendance for the day and add each record to the attendance table
        //generate todays date
        String todaysDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        List<Attendance> attendances = new ArrayList<>();
        createRequest.getAttendanceBodies().forEach(body -> {
            Student student = studentRepository.findFirstByStudentId(body.getStudentId());
            boolean isPresent = body.isPresent();
            attendances.add(new Attendance(isPresent, student));
        });
        //check if the attendance for this course has been created today
        AttendanceRecordForADay attendanceRecordForADay = attendanceRecordForADayRepository.findByCourseIdAndAttendanceDate(createRequest.getCourseId(),todaysDate);
        if(attendanceRecordForADay != null)/*This attendance for today has been taken*/return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        attendanceRecordForADay = new AttendanceRecordForADay();
        attendanceRecordForADay.setAttendanceDate(todaysDate);
        attendanceRecordForADay.setAttendances(attendances);
        attendanceRecordForADay.setCourseId(createRequest.getCourseId());
        if(attendanceRecordForADay.getAttendances().get(0).getStudent() == null)return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        attendanceRecordForADayRepository.save(attendanceRecordForADay);
        return new ResponseEntity<>(HttpStatus.CREATED);
//        if(attendanceList.getAttendanceList() == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        for (AttendanceRequestObj item :
//                attendanceList.getAttendanceList()) {
//            Course currentCourse = courseRepository.findFirstByCourseId(item.getCourseId());
//            Student currentStudent = studentRepository.findFirstByStudentId(item.getStudentId());
//            Department currentDepartment = departmentRepository.findFirstByDepartmentId(item.getDepartmentId());
//            Attendance attendanceRecord = new Attendance();
//            attendanceRecord.setAttendanceDate(item.getDate());
//            attendanceRecord.setCourse(currentCourse);
//            attendanceRecord.setPresent(item.isPresent());
//            attendanceRecord.setSemester(item.getSemester());
//            attendanceRecord.setStudent(currentStudent);
//           // attendanceRecord.setDepartment(currentDepartment);
//            attendanceRepository.save(attendanceRecord);
////        }
    }

    //USERS
    /*
    * 1.view all users
    * 2.delete single user
    * 3.create single user
    * 4.update.
    * */
    @PostMapping(value = "/user",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody UserRequestBody userRequestBody) {

        User user = userRepository.findFirstByUsername(userRequestBody.getUsername());
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(userRepository.findFirstByUsername(userRequestBody.getUser().getUsername()) != null || userRepository.findFirstByUserId(userRequestBody.getUser().getUserId()) != null){
            //user being created already exists in the db
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
        if(user.getUserType() == UserType.HOD || user.getUserType() == UserType.ADMIN || user.getUserType() == UserType.DEAN){
            //allow to create course
            userRepository.save(userRequestBody.getUser());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    @DeleteMapping(value = "/user/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {

        User user = userRepository.findFirstByUserId(userId);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userRepository.delete(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping(value = "/users",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUsers() {
        List<User> users = StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new UsersResponseBody(users),HttpStatus.OK);
    }
    @GetMapping(value = "/user/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSingleUser(@PathVariable String userId) {
        User user = userRepository.findFirstByUserId(userId);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @PostMapping(value = "/user/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateSingleUser(@PathVariable String userId, @RequestBody User user)
    {
        User gottenUser = userRepository.findFirstByUserId(userId);
        if(gottenUser == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        gottenUser.setPassword(user.getPassword());
        userRepository.save(gottenUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //DEPARTMENT
    @PostMapping(value = "/department",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDepartment(@RequestBody DepartmentRequestBody departmentRequestBody) {

        User user = userRepository.findFirstByUsername(departmentRequestBody.getUsername());
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(user.getUserType() == UserType.ADMIN || user.getUserType() == UserType.DEAN){
            //allow to create course
            String departmentId = departmentRequestBody.getDepartment().getDepartmentId();
            Department currentDepartment = departmentRepository.findFirstByDepartmentId(departmentId);
            if(currentDepartment == null){
                //new depaertment
                departmentRepository.save(departmentRequestBody.getDepartment());
                return new ResponseEntity<>(HttpStatus.CREATED);
            }else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    @DeleteMapping(value = "/department/{departmentId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteDepartment(@PathVariable String departmentId) {

        Department department = departmentRepository.findFirstByDepartmentId(departmentId);
        if(department == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        departmentRepository.delete(department);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping(value = "/department",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllDepartments() {
        List<Department> departments = StreamSupport.stream(departmentRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        if(departments == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DepartmentResponseBody(departments),HttpStatus.OK);
    }
    @GetMapping(value = "/department/{departmentId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSingleDepartment(@PathVariable String departmentId) {
        Department department = departmentRepository.findFirstByDepartmentId(departmentId);
        if(department == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(department,HttpStatus.OK);
    }
    @PutMapping(value = "/department/{departmentId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateSingleDepartment(@RequestBody Department department, @PathVariable String departmentId) {
        Department departmentR = departmentRepository.findFirstByDepartmentId(departmentId);
        if(departmentR == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        departmentRepository.save(department);
        return new ResponseEntity<>(department,HttpStatus.OK);
    }

    /*
    * 1.Get all students in a department
    * 1.1.Get all courses in a department
    * 2.Add student to a course in a particular department
    * */
    @GetMapping(value = "/student/all/{departmentId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllStudentsInADepartment(@PathVariable String departmentId) {
//        List<Student> students = StreamSupport.stream(studentRepository.findAll().spliterator(), false)
//                .collect(Collectors.toList());
        List<Student> allStudentsInAParticularDepartment = studentRepository.getAllStudentsInAParticularDepartment(departmentId);
        if(allStudentsInAParticularDepartment == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        User user = userRepository.findFirstByUsername(departmentRequestBody.getUsername());
////        if(user == null){
////            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
////        }
//        if(user.getUserType() == UserType.ADMIN || user.getUserType() == UserType.DEAN){
//            //allow to create course
//            departmentRepository.save(departmentRequestBody.getDepartment());
//            return new ResponseEntity<>(HttpStatus.CREATED);
//        }
        return new ResponseEntity<>(new StudentsInDepAcct(allStudentsInAParticularDepartment),HttpStatus.OK);
    }
    @PostMapping(value = "/student",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createStudent(@RequestBody StudentRequestBody studentRequestBody) {

        User user = userRepository.findFirstByUsername(studentRequestBody.getUsername());
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(user.getUserType() == UserType.ADMIN || user.getUserType() == UserType.HOD || user.getUserType() == UserType.DEAN){
            //allow to create course
            studentRepository.save(studentRequestBody.getStudent());
            User userStudent = userRepository.findFirstByUserId(studentRequestBody.getStudent().getStudentId());
            if(userStudent == null){
                //user does not exist in the db, allow to add the student
                User user1 = new User();
                user1.setEmail(studentRequestBody.getStudent().getStudentName());
                user1.setUserId(studentRequestBody.getStudent().getStudentId());
                user1.setPassword(studentRequestBody.getStudent().getStudentId());
                user1.setUserType(UserType.STUDENT);
                user1.setUsername(studentRequestBody.getStudent().getStudentId());
                userRepository.save(user1);

            }
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    @DeleteMapping(value = "/student/{studentId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteStudent(@PathVariable String studentId) {

        Student student = studentRepository.findFirstByStudentId(studentId);
        if(student == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studentRepository.delete(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping(value = "/student",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateStudent(@RequestBody StudentRquestBodyUpdate studentRquestBodyUpdate) {

        User user = userRepository.findFirstByUsername(studentRquestBodyUpdate.getUsername());
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Student student = studentRepository.findFirstByStudentId(studentRquestBodyUpdate.getStudentId());
        if(student == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(user.getUserType() == UserType.ADMIN || user.getUserType() == UserType.HOD){
            //allow to create course
            studentRepository.save(student);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    @GetMapping(value = "/student/{studentId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSingleStudent(@PathVariable String studentId) {

        Student student = studentRepository.findFirstByStudentId(studentId);
        if(student == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student,HttpStatus.UNAUTHORIZED);
    }


    //COURSE ROSTER
    //add a course to a student
    @PostMapping(value = "/courseroster",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addCourseToStudent(@RequestBody CourseRosterRequestBody courseRosterRequestBody) {
        Course firstByCourseId = courseRepository.findFirstByCourseId(courseRosterRequestBody.getCourseId());
        Student firstByStudentId = studentRepository.findFirstByStudentId(courseRosterRequestBody.getStudentId());
        if(firstByCourseId == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if( firstByStudentId == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        courseRosterRepository.save(new CourseRoster(firstByCourseId.getCourseId(),firstByStudentId.getStudentId()));
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //get the students doing a particular course
    @GetMapping(value = "/courseroster/student/{courseId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getStudentsDoingAParticularCourse(@PathVariable String courseId) {
        if(courseRepository.findFirstByCourseId(courseId) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<CourseRoster> byCourseId = courseRosterRepository.findByCourseId(courseId);
        CourseRosterMultipleStudents courseRosterMultipleStudents = new CourseRosterMultipleStudents();
        List<Student> students = byCourseId.stream().map(item -> studentRepository.findFirstByStudentId(item.getStudentId())).collect(Collectors.toList());
       courseRosterMultipleStudents.setStudents(students);
        return new ResponseEntity<>(courseRosterMultipleStudents,HttpStatus.OK);
    }
    @GetMapping(value = "/courseroster/course/{studentId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllCoursesAStudentIsDoing(@PathVariable String studentId) {
        if(studentRepository.findFirstByStudentId(studentId) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<CourseRoster> byStudentId = courseRosterRepository.findByStudentId(studentId);
        CourseRosterMultipleCourses courseRosterMultipleCourses = new CourseRosterMultipleCourses();
        List<Course> courses = byStudentId.stream().map(item -> courseRepository.findFirstByCourseId(item.getCourseId())).collect(Collectors.toList());
        courseRosterMultipleCourses.setCourses(courses);
        return new ResponseEntity<>(courseRosterMultipleCourses,HttpStatus.OK);
    }


}

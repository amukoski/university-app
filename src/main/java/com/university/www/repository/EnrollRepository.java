package com.university.www.repository;

import com.university.www.model.Course;
import com.university.www.model.Enroll;
import com.university.www.model.Semester;
import com.university.www.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EnrollRepository extends CrudRepository<Enroll, Long> {
    List<Enroll> findByStudent_Account_UsernameOrderBySemesterId(String username);
    List<Enroll> findByCourseAndSemester(Course course, Semester semester);
    List<Enroll> findBySemesterAndStudent(Semester semester, Student student);
}

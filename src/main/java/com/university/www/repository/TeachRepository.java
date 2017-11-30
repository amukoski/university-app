package com.university.www.repository;

import com.university.www.model.Course;
import com.university.www.model.Semester;
import com.university.www.model.Teach;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeachRepository extends CrudRepository<Teach, Long> {
    Teach findByCourseAndSemester(Course course, Semester semester);
    List<Teach> findByProfessor_UsernameOrderByIdDesc(String username);
    List<Teach> findBySemesterId(Long id);
}

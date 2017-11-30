package com.university.www.repository;

import com.university.www.model.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Long> {
    List<Course> findAllByOrderBySemesterNumberDesc();
}

package com.university.www.repository;

import com.university.www.model.Semester;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SemesterRepository extends CrudRepository<Semester, Long> {
    List<Semester> findAllByOrderByIdDesc();
}

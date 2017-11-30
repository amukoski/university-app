package com.university.www.repository;

import com.university.www.model.Exam;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExamRepository extends CrudRepository<Exam, Long> {
    List<Exam> findByEnroll_Student_Account_Username(String username);
}

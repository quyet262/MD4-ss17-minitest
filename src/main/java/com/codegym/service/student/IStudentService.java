package com.codegym.service.student;

import com.codegym.model.student.Student;
import com.codegym.service.IGenerateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IStudentService extends IGenerateService<Student> {
    Page<Student> findAll(Pageable pageable);
    Page<Student> findByFirstNameContaining(String firstName, Pageable pageable);
}

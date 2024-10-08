package com.codegym.repository;

import com.codegym.model.student.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends JpaRepository<Student, Long> {
    Page<Student> findAll(Pageable pageable);
   Page<Student> findByFirstNameContaining(String firstName, Pageable pageable);
}

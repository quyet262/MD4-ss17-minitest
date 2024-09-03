package com.codegym.service.student.impl;

import com.codegym.model.student.Student;
import com.codegym.repository.IStudentRepository;
import com.codegym.service.student.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService implements IStudentService {
    @Autowired
    private IStudentRepository studentRepository;

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Page<Student> findByFirstNameContaining(String firstName, Pageable pageable) {
        return studentRepository.findByFirstNameContaining(firstName, pageable);
    }

    @Override
    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public void save(Student student) {
    studentRepository.save(student);
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
    studentRepository.deleteById(id);
    }
}

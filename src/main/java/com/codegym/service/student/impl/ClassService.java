package com.codegym.service.student.impl;

import com.codegym.model.DTO.ClassStudentDTO;
import com.codegym.model.student.ClassStudent;
import com.codegym.repository.IClassRepository;
import com.codegym.service.student.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClassService implements IClassService {
    @Autowired
    private IClassRepository classRepository;

    @Override
    public Iterable<ClassStudent> findAll() {
        return classRepository.findAll();
    }

    @Override
    public void save(ClassStudent classStudent) {
    classRepository.save(classStudent);
    }

    @Override
    public Optional<ClassStudent> findById(Long id) {
        return classRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
    classRepository.deleteById(id);
    }

    @Override
    public Page<ClassStudent> findAll(Pageable pageable) {
        return classRepository.findAll(pageable);
    }

    @Override
    public Iterable<ClassStudentDTO> getCountClass() {
        return classRepository.getCountClass();
    }

    @Override
    public void deleteClassByid(Long id) {
    classRepository.deleteClassByid(id);
    }
}

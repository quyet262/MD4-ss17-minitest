package com.codegym.service.student;

import com.codegym.model.DTO.ClassStudentDTO;
import com.codegym.model.student.ClassStudent;
import com.codegym.service.IGenerateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface IClassService extends IGenerateService<ClassStudent> {
    Page<ClassStudent> findAll(Pageable pageable);
    Iterable<ClassStudentDTO> getCountClass();
    void deleteClassByid(@Param("id") Long id);
}

package com.codegym.controller;

import com.codegym.model.student.ClassStudent;
import com.codegym.model.student.Student;
import com.codegym.model.student.StudentForm;
import com.codegym.service.student.IClassService;
import com.codegym.service.student.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;


@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IClassService classService;
    @Autowired
    Environment env;

    @ModelAttribute("classes")
    public Iterable<ClassStudent> listClass(){
        return classService.findAll();
    }
    @GetMapping
    public ModelAndView listStudents(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size,
                                     @RequestParam(defaultValue = "") String search){
        Page<Student> students;
        Pageable pageable = PageRequest.of(page, size, Sort.by("firstName").descending());
        if(search.trim().isEmpty()){
            students = studentService.findAll(pageable);
        }else {
            students = studentService.findByFirstNameContaining(search, pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/students/index");
        modelAndView.addObject("students", students);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createStudent(){
        ModelAndView modelAndView = new ModelAndView("/students/create");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }
    @PostMapping("/save")
    public RedirectView saveStudent(@ModelAttribute("student") StudentForm student){
        Student student1 = new Student(student.getFirstName(),student.getLastName(),student.getDob(),student.getAddress(),student.getMark(),student.getClassStudent());
        MultipartFile multipartFile = student.getImage();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("upload.path").toString();
        try {
            FileCopyUtils.copy(student.getImage().getBytes(),new File(fileUpload + fileName));
        }catch (IOException e){
            e.printStackTrace();
        }
        student1.setImage(fileName);
        studentService.save(student1);
        return new RedirectView("/students");
    }
    @GetMapping("/{id}/edit")
    public ModelAndView updateStudent(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("/students/update");
        modelAndView.addObject("student", studentService.findById(id).get());
        return modelAndView;
    }
    @PostMapping("/update")
    public RedirectView updateStudent(@ModelAttribute("student") StudentForm studentForm) {
        Student existingStudent = studentService.findById(studentForm.getId()).orElse(null);

        if (existingStudent != null) {
            existingStudent.setFirstName(studentForm.getFirstName());
            existingStudent.setLastName(studentForm.getLastName());
            existingStudent.setDob(studentForm.getDob());
            existingStudent.setAddress(studentForm.getAddress());
            existingStudent.setMark(studentForm.getMark());
            existingStudent.setClassStudent(studentForm.getClassStudent());

            MultipartFile multipartFile = studentForm.getImage();
            if (multipartFile != null && !multipartFile.isEmpty()) {
                String fileName = multipartFile.getOriginalFilename();
                String fileUpload = env.getProperty("upload.path");

                try {
                    FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload + fileName));
                    existingStudent.setImage(fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            studentService.save(existingStudent);
        }

        return new RedirectView("/students");
    }

    @GetMapping("/{id}/delete")
    public ModelAndView deleteStudent(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("/students/delete");
        modelAndView.addObject("student", studentService.findById(id).get());
        return modelAndView;
    }
    @PostMapping("/delete")
    public String deleteStudent(Student student){
        studentService.remove(student.getId());
        return "redirect:/students";
    }
    @GetMapping("/{id}/view")
    public ModelAndView viewStudent(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("/students/view");
        modelAndView.addObject("student", studentService.findById(id).get());
        return modelAndView;
    }


}

package com.codegym.controller;


import com.codegym.model.student.ClassStudent;
import com.codegym.service.student.IClassService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/classes")
public class ClassController {
    private final IClassService classService;

    public ClassController(IClassService classService) {
        this.classService = classService;
    }

    @GetMapping("")
    public ModelAndView index(@PageableDefault(value = 5) Pageable pageable) {
        ModelAndView mav = new ModelAndView("/classes/index");
        Page<ClassStudent> classes = classService.findAll(pageable);
        mav.addObject("classes", classes);
        return mav;
    }
    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView mav = new ModelAndView("/classes/create");
        mav.addObject("classStudent", new ClassStudent());
        return mav;
    }
    @PostMapping("/create")
    public String create(@ModelAttribute ClassStudent classStudent) {
        classService.save(classStudent);
        return "redirect:/classes";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("/classes/delete");
        ClassStudent classStudent  = classService.findById(id).get();
        mav.addObject("classStudent", classStudent);
        return mav;
    }
    @PostMapping("/delete")
    public String delete(ClassStudent classStudent) {
        classService.deleteClassByid(classStudent.getId());
        return "redirect:/classes";
    }
}

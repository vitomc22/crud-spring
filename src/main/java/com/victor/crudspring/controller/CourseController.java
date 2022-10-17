package com.victor.crudspring.controller;

import java.util.List;

import com.victor.crudspring.model.Course;
import com.victor.crudspring.repository.CourseRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    } // usando injeção de dependencia via construtor ao invés do Autowired
      // boa prática

    @GetMapping // mesmo que @RequestMapping(method.get)
    public List<Course> list() {
        return courseRepository.findAll();
    }

}

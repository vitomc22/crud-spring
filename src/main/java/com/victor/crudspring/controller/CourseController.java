package com.victor.crudspring.controller;

import com.victor.crudspring.model.Course;
import com.victor.crudspring.repository.CourseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    } // usando injeção de dependencia via construtor ao invés do Autowired
    // boa prática

    @GetMapping // mesmo que @RequestMapping(method.get)
    public List<Course> list() {
        return courseRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody Course course) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseRepository.save(course));

    }


}

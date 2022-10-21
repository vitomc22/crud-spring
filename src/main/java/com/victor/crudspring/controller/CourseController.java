package com.victor.crudspring.controller;

import com.victor.crudspring.model.Course;
import com.victor.crudspring.repository.CourseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<Course> findByid(@PathVariable Long id){
       return courseRepository.findById(id)
               .map(course -> ResponseEntity.ok().body(course))
               .orElse(ResponseEntity.notFound().build());
        //aqui ResponseEntity ja retorna um Optional, nao precisa declarar de novo
        //Usamos map caso ache resultado e orElse caso nao ache
    }

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody Course course) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseRepository.save(course));

    }


}

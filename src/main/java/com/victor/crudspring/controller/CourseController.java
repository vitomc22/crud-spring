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

    @GetMapping("/{id}")
    public ResponseEntity<Course> findByid(@PathVariable Long id) {
        return courseRepository.findById(id).map(recordFound -> ResponseEntity.ok().body(recordFound)).orElse(ResponseEntity.notFound().build());
        //aqui ResponseEntity ja retorna um Optional, nao precisa declarar de novo
        //Usamos map caso ache resultado e orElse caso nao ache
    }

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody Course course) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseRepository.save(course));

    }

    //victor
    /*@PutMapping("/{id}")
    public ResponseEntity<Optional<Course>> update(@PathVariable Long id, @RequestBody Course course){
        Optional<Course> recordFound =  courseRepository.findById(id);
        recordFound.get().setName(course.getName());
        recordFound.get().setCategory(course.getCategory());
        courseRepository.save(recordFound.get());
        return ResponseEntity.ok().body(recordFound);

    }; */

    //loiane style
    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody Course course) {
        return courseRepository.findById(id).map(recordFound -> {
            recordFound.setName(course.getName());
            recordFound.setCategory(course.getCategory());
            Course updated = courseRepository.save(recordFound);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        return courseRepository.findById(id).map(recordFound -> {
            courseRepository.delete(recordFound);
            return ResponseEntity.noContent().<Void>build(); //necessário cast para vazio pois delete retorna VOID
        }).orElse(ResponseEntity.notFound().build());
    }
}


package com.victor.crudspring.controller;

import com.victor.crudspring.model.Course;
import com.victor.crudspring.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated //faz todas validações das anotações de validações
@RestController
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired
    CourseRepository courseRepository;

    @GetMapping
    public List<Course> list() {
        return courseRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> findByid(@PathVariable @NotNull @Positive Long id) { //nao pode vazio e somente numero positivo
        return courseRepository.findById(id).map(recordFound -> ResponseEntity.ok().body(recordFound)).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody @Valid Course course) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseRepository.save(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody @Valid Course course) {
        return courseRepository.findById(id).map(recordFound -> {
            recordFound.setName(course.getName());
            recordFound.setCategory(course.getCategory());
            Course updated = courseRepository.save(recordFound);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive Long id) { //nao pode vazio e somente numero positivo
        return courseRepository.findById(id).map(recordFound -> {
            courseRepository.delete(recordFound);
            return ResponseEntity.noContent().<Void>build(); //necessário cast para vazio pois delete retorna VOID
        }).orElse(ResponseEntity.notFound().build());
    }
}


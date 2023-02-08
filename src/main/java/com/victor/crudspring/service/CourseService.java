package com.victor.crudspring.service;

import com.victor.crudspring.model.Course;
import com.victor.crudspring.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Validated
@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> list() {
        return courseRepository.findAll();
    }

    public Optional<Course> findByid(@PathVariable @NotNull @Positive Long id) { //nao pode vazio e somente numero positivo
        return courseRepository.findById(id);
    }

    public Course create(@Valid Course course) {
        return courseRepository.save(course);
    }

    public Optional<Course> update(@NotNull @Positive Long id, @Valid Course course) {
        return courseRepository.findById(id).map(recordFound -> {
            recordFound.setName(course.getName());
            recordFound.setCategory(course.getCategory());
            return courseRepository.save(recordFound);
        });
    }

    public boolean delete(@NotNull @Positive Long id) {
        return courseRepository.findById(id).map(recordFound -> {
            courseRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
}

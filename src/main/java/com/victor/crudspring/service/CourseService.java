package com.victor.crudspring.service;

import com.victor.crudspring.dto.CourseDTO;
import com.victor.crudspring.dto.mapper.CourseMapper;
import com.victor.crudspring.exception.RecordNotFoundException;
import com.victor.crudspring.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public List<CourseDTO> list() {
        return courseRepository.findAll().stream().map(courseMapper::toDto).toList();

    }

    public CourseDTO findByid(@NotNull @Positive Long id) { //nao pode vazio e somente numero positivo
        return courseRepository.findById(id).map(courseMapper::toDto)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CourseDTO create(@Valid @NotNull CourseDTO course) {
        return courseMapper.toDto(courseRepository.save(courseMapper.toEntity(course)));
    }

    public CourseDTO update(@NotNull @Positive Long id, @Valid @NotNull CourseDTO course) {
        return courseRepository.findById(id).map(recordFound -> {
            recordFound.setName(course.name());
            recordFound.setCategory(courseMapper.convertCategoryValue(course.category()));
            return courseMapper.toDto(courseRepository.save(recordFound));
        }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {
        courseRepository.delete(courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }
}

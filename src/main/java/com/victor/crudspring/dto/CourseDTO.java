package com.victor.crudspring.dto;

import com.victor.crudspring.model.Lesson;

import java.util.List;

public record CourseDTO(Long id, String name, String category, List<Lesson> lessons) {

}

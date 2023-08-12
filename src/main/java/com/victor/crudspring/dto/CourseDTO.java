package com.victor.crudspring.dto;

import java.util.List;

public record CourseDTO(Long id, String name, String category, List<LessonDTO> lessons) {

}

package com.victor.crudspring.dto.mapper;

import com.victor.crudspring.dto.CourseDTO;
import com.victor.crudspring.dto.LessonDTO;
import com.victor.crudspring.enums.Category;
import com.victor.crudspring.model.Course;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseMapper {
    public CourseDTO toDto(Course course) {
        if (course == null) {
            return null;
        }
        List<LessonDTO> lessons = course.getLessons().stream().map(lesson -> new LessonDTO(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl())).toList();
        return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue(), lessons);

    }

    public Course toEntity(CourseDTO courseDTO) {
        if (courseDTO == null) {
            return null;
        }
        Course course = new Course();
        if (courseDTO.id() != null) {
            course.setId(courseDTO.id());
        }
        course.setName(courseDTO.name());
        course.setCategory(convertCategoryValue(courseDTO.category()));
        return course;
    }

    public Category convertCategoryValue(String value) {
        if (value == null) {
            return null;
        }
        return switch (value) { // new Swith expression JAVA14
            case "Front-end" -> Category.FRONT_END;
            case "Back-end" -> Category.BACK_END;
            default -> throw new IllegalArgumentException("Invalid value: " + value);
        };
    }
}



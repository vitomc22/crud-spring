package com.victor.crudspring.repository;

import com.victor.crudspring.model.Course;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}

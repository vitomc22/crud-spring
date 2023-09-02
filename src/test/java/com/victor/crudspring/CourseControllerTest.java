package com.victor.crudspring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.victor.crudspring.controller.CourseController;
import com.victor.crudspring.dto.CourseDTO;
import com.victor.crudspring.dto.LessonDTO;
import com.victor.crudspring.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CourseControllerTest {
    @InjectMocks
    CourseController controller;
    @Mock
    private CourseService service;
    private MockMvc mockMvc;
    private CourseDTO course;
    private LessonDTO lesson;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).alwaysDo(print()).build();
    }

    @Test
    void listAllCourses() throws Exception {
        when(service.list()).thenReturn(Collections.singletonList(course));

        mockMvc.perform(get("/course/list").contentType("aplication/json"))
                .andExpect(status().isOk());
        verify(service).list();
        verifyNoMoreInteractions(service);
    }

    @Test
    void findByid() throws Exception {

    }

    @Test
    void create() throws Exception {

    }

    @Test
    void update() throws Exception {

    }

    @Test
    void delete() throws Exception {

    }
}
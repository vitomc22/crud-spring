package com.victor.crudspring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.victor.crudspring.controller.CourseController;
import com.victor.crudspring.dto.CourseDTO;
import com.victor.crudspring.dto.LessonDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class CourseControllerTest {
    @Autowired
    ObjectMapper objectMapper;
    LessonDTO lesson = new LessonDTO(1L, "Bom dia meu consagrado", "/ql70nnlZt-Y");
    CourseDTO course3 = new CourseDTO(3L, "Cpp Advanced", "Back-end", Stream.of(lesson).toList());
    CourseDTO course4 = new CourseDTO(4L, "Cpp Advanced", "Back-end", Stream.of(lesson).toList());
    CourseDTO course5 = new CourseDTO(5L, "Cpp Advanced", "Back-end", Stream.of(lesson).toList());
    CourseDTO course6 = new CourseDTO(6L, "Cpp Advanced", "Back-end", Stream.of(lesson).toList());
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CourseController courseController;

    @Before    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(courseController).build();
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/courses").contentType("application/json").content(objectMapper.writeValueAsString(course4))).andExpect(status().isCreated());
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/courses").contentType("application/json").content(objectMapper.writeValueAsString(course5))).andExpect(status().isCreated());
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/courses").contentType("application/json").content(objectMapper.writeValueAsString(course6))).andExpect(status().isCreated());

    }

    @Test
    public void list() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/courses")).andExpect(status().isOk());
    }

    @Test
    public void findByid() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/courses/4")).andExpect(status().isOk());
    }

    @Test
    public void create() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/courses").contentType("application/json").content(objectMapper.writeValueAsString(course3))).andExpect(status().isCreated());
    }

    @Test
    public void update() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.put("/api/courses/5").contentType("application/json").content(objectMapper.writeValueAsString(course5))).andExpect(status().isOk());
    }

    @Test
    public void delete() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/courses/6")).andExpect(status().isNoContent());
    }
}
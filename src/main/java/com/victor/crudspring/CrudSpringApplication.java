package com.victor.crudspring;

import com.victor.crudspring.enums.Category;
import com.victor.crudspring.model.Course;
import com.victor.crudspring.model.Lesson;
import com.victor.crudspring.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudSpringApplication.class, args);
    }

    @Bean
        // managed by the Spring container
    CommandLineRunner initDatabse(CourseRepository courseRepository) {
        return args -> {
            courseRepository.deleteAll();

            Course c = new Course();
            c.setName("Angular com spring");
            c.setCategory(Category.FRONT_END);

            Lesson l = new Lesson();
            l.setName("Bom dia meu consgrado");
            l.setYoutubeUrl("/ql70nnlZt-Y");
            l.setCourse(c);
            c.getLessons().add(l);

            Lesson l2 = new Lesson();
            l2.setName("Bom dia meu consgrado 2");
            l2.setYoutubeUrl("/ql70nnlZt-Y");
            l2.setCourse(c);
            c.getLessons().add(l2);
            courseRepository.save(c);

        };

    }
}
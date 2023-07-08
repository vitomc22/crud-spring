package com.victor.crudspring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.victor.crudspring.enums.Category;
import com.victor.crudspring.enums.Status;
import com.victor.crudspring.enums.converters.CategoryConverter;
import com.victor.crudspring.enums.converters.StatusConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity // JPA para acesso ao banco, entidade de mapeamento no banco
@SQLDelete(sql = "UPDATE course SET status = 'Inativo' WHERE id = ?")
//query que roda ao chamar metodo delete no hibernate
@Where(clause = "status = 'Ativo'") //Every query "find" will have
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id") //vai trocar a forma de resposta do json para _id e nao id
    private Long id;

    @NotNull(message = "O nome não pode ser vazio!")
    @Size(min = 1, max = 100)
    @Column(length = 200, nullable = false)
    private String name;

    @NotNull(message = "A categoria não pode ser vazia!")
    @Column(length = 10, nullable = false)
    @Convert(converter = CategoryConverter.class)
    private Category category;

    @NotNull(message = "Status não pode ser vazio!!!")
    @Column(length = 10, nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ACTIVE; //valor padrao

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")
    private final List<Lesson> lessons = new ArrayList<>();

    public Course() {
    }


    public Course(Long id, @NotNull String name, @NotNull Category category, @NotNull Status status) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public @NotNull Category getCategory() {
        return category;
    }

    public void setCategory(@NotNull Category category) {
        this.category = category;
    }

    public @NotNull Status getStatus() {
        return status;
    }

    public void setStatus(@NotNull Status status) {
        this.status = status;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course course)) return false;
        return Objects.equals(getId(), course.getId()) && Objects.equals(getName(), course.getName()) && getCategory() == course.getCategory() && getStatus() == course.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCategory(), getStatus());
    }
}

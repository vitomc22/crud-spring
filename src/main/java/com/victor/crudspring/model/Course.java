package com.victor.crudspring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data // Lombok, cria get set construtor e hash code equals automaticamente
@Entity // JPA para acesso ao banco, entidade de mapeamento no banco
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id") //vai trocar a forma de resposta do json para _id e nao id
    private Long id;

    @Column(length = 200, nullable = false)
    @NotBlank(message = "O nome não pode ser vazio!")
    @Size(min = 1, max = 100)
    private String name;

    @Column(length = 10, nullable = false)
    @NotBlank(message = "A categoria não pode ser vazia!")
    @Size(min = 1, max = 10)
    private String category;

}

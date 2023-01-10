package com.victor.crudspring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data // Lombok, cria get set construtor e hash code equals automaticamente
@Entity // JPA para acesso ao banco, entidade de mapeamento no banco
@SQLDelete(sql = "UPDATE course SET status = 'Inativo' WHERE id = ?") //query que roda ao chamar metodo delete no hibernate
@Where(clause = "status = 'Ativo'") //Todo find do hibernate tera essa clause
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id") //vai trocar a forma de resposta do json para _id e nao id
    private Long id;

    @NotBlank(message = "O nome não pode ser vazio!")
    @Size(min = 1, max = 100)
    @Column(length = 200, nullable = false)
    private String name;

    @NotBlank(message = "A categoria não pode ser vazia!")
    @Size(min = 1, max = 10)
    @Column(length = 10, nullable = false)
    private String category;

    @NotBlank(message = "O status não pode ser vazio!")
    @Size(min = 1, max = 10)
    @Pattern(regexp = "Ativo|Inativo")
    @Column(length = 10, nullable = false)
    private String status = "Ativo"; //valor padrao

}

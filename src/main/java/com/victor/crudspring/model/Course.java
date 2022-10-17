package com.victor.crudspring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data // Lombok, cria get set construtor e hash code equals automaticamente
@Entity // JPA para acesso ao banco, entidade de mapeamento no banco
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id") //vai trocar a forma de resposta do json para _id e nao id
    private Long id;
    
    @Column(length = 200, nullable = false)
    private String name;
    
    @Column(length = 10 , nullable = false)
    private String category;

}

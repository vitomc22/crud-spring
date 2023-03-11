package com.victor.crudspring.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

public record CourseDTO(Long id, @NotNull @NotBlank @Length(min = 5, max = 100) String name,
                        @NotBlank(message = "A categoria não pode ser vazia!") @Size(min = 1, max = 10) @NotNull @Column(length = 10, nullable = false) String category) {
}

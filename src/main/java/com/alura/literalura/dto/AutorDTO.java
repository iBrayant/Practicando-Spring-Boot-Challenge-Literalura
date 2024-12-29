package com.alura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutorDTO {
    private String name;
    @JsonProperty("birth_year")
    private Integer anioNacimiento;
    @JsonProperty("death_year")
    private Integer anioFallecimiento;

    // Getters y setters generados por Lombok
} 
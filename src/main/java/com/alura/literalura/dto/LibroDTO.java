package com.alura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LibroDTO {
    private Integer id;
    private String title;
    private List<AutorDTO> authors;
    private List<String> languages;
    @JsonProperty("download_count")
    private Integer numeroDescargas;

    // Getters y setters generados por Lombok
} 
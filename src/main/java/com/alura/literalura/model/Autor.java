package com.alura.literalura.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Autor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private Integer anioNacimiento;
    private Integer anioFallecimiento;
    
    public boolean estabaVivo(int anio) {
        return (anioNacimiento == null || anio >= anioNacimiento) &&
               (anioFallecimiento == null || anio <= anioFallecimiento);
    }
} 
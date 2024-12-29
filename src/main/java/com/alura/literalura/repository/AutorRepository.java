package com.alura.literalura.repository;

import com.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    
    @Query("SELECT a FROM Autor a WHERE " +
           "(a.anioNacimiento IS NULL OR a.anioNacimiento <= :anio) AND " +
           "(a.anioFallecimiento IS NULL OR a.anioFallecimiento >= :anio)")
    List<Autor> findAutoresVivosEnAnio(@Param("anio") int anio);
} 
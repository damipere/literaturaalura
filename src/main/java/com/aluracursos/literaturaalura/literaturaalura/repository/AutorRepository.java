package com.aluracursos.literaturaalura.literaturaalura.repository;

import com.aluracursos.literaturaalura.literaturaalura.model.Autores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface AutorRepository extends JpaRepository<Autores, Long> {
    Autores findByNombreIgnoreCase(String nombre);
    @Query("SELECT DISTINCT a FROM Autores a LEFT JOIN FETCH a.libros")
    List<Autores> findAllAutores();
    @Query("SELECT a FROM Autores a WHERE a.fechaDeNacimiento <= :anio AND (a.fechaDeDefencion > :anio OR a.fechaDeDefencion IS NULL) ORDER BY a.fechaDeNacimiento DESC")
    List<Autores> findAllByAnio(@Param("anio") Long anio);
}

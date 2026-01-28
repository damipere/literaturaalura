package com.aluracursos.literaturaalura.literaturaalura.repository;

import com.aluracursos.literaturaalura.literaturaalura.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libros, Long> {
    Boolean existsByIdApi(Long idApi);
    @Query("SELECT DISTINCT l FROM Libros l LEFT JOIN FETCH l.autores")
    List<Libros> findAllLibros();
    @Query("SELECT l FROM Libros l WHERE l.idiomas LIKE %:idioma%")
    List<Libros> findLibrosPorIdioma(String idioma);
    @Query("SELECT s FROM Libros s ORDER BY s.descargas DESC LIMIT 10")
    List<Libros> ordenarConMasDescargas();
}

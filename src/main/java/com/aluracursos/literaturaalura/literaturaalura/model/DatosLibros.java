package com.aluracursos.literaturaalura.literaturaalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
        @JsonAlias("id")
        Long idapi,
        @JsonAlias("title")
        String titulo,
        @JsonAlias("authors")
        List<DatosAutor> autores,
        @JsonAlias("download_count")
        Long descargas,
        @JsonAlias("languages")
        List<String> idiomas
) {

}

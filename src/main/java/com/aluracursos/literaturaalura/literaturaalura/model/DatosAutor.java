package com.aluracursos.literaturaalura.literaturaalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(
        @JsonAlias("name")
        String nombre,
        @JsonAlias("birth_year")
        Long fechaDeNacimiento,
        @JsonAlias("death_year")
        Long fechaDeDefencion
) {
}

package com.aluracursos.literaturaalura.literaturaalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RespuestaBruta(
        @JsonAlias("results")
        List<DatosLibros> Libros,
        @JsonAlias("next")String next,
        @JsonAlias("previous")String prev
)
{}

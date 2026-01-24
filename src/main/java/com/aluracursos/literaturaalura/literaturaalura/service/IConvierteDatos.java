package com.aluracursos.literaturaalura.literaturaalura.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json,Class<T> clase);
}

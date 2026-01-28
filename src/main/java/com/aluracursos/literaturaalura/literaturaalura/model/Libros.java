package com.aluracursos.literaturaalura.literaturaalura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long idApi;
    private String titulo;
    //private String autores;
    private Long descargas;
    private String idiomas;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name ="libro_autor",
            joinColumns = @JoinColumn(name="id_libro"),
            inverseJoinColumns = @JoinColumn(name = "id_autor")
    )
    private List<Autores> autores;

    protected Libros() {
    }
    public Libros(DatosLibros datosLibros) {
        this.idApi =datosLibros.idapi();
        this.titulo = datosLibros.titulo();
        this.autores = datosLibros.autores().stream().map(Autores::new).toList();
        this.descargas=datosLibros.descargas();
        this.idiomas=datosLibros.idiomas().stream().collect(Collectors.joining(", "));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdApi() {
        return idApi;
    }

    public void setIdApi(Long idApi) {
        this.idApi = idApi;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getDescargas() {
        return descargas;
    }

    public void setDescargas(Long descargas) {
        this.descargas = descargas;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public List<Autores> getAutores() {
        return autores;
    }

    public void setAutores(List<Autores> autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
        return "Libros{" +
                "id=" + id +
                ", idApi=" + idApi +
                ", titulo='" + titulo + '\'' +
                ", autores='" + autores + '\'' +
                ", descargas=" + descargas +
                ", idiomas='" + idiomas + '\'' +
                '}';
    }
}

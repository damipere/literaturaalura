package com.aluracursos.literaturaalura.literaturaalura.principal;

import com.aluracursos.literaturaalura.literaturaalura.model.*;
import com.aluracursos.literaturaalura.literaturaalura.repository.AutorRepository;
import com.aluracursos.literaturaalura.literaturaalura.repository.LibroRepository;
import com.aluracursos.literaturaalura.literaturaalura.service.ConsumoApi;
import com.aluracursos.literaturaalura.literaturaalura.service.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner sc=new Scanner(System.in);
    private ConsumoApi consumoApi=new ConsumoApi();
    private final String URL_BASE= "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;
    private List<Libros> libros;
    private List<Autores> autores;

    public Principal (LibroRepository libroRepository,AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }



    public void iniciar(){
        var opcion =-1;
        while(opcion!=-0){
            var menu = """
                        ========== LITERAlura ==========
                        
                        📚 GESTIÓN DE LIBROS
                        1 - Buscar libro por título 
                        2 - Listar libros registrados
                        3 - Listar autores registrados
                        4 - Listar autores vivos en año dado
                        5 - Listar libros por idioma
                        
                        📊 ESTADÍSTICAS (Extras)
                        6 - Top 10 libros más descargados
                                            
                        0 - Salir
                        ================================
                        """;
            System.out.println(menu);
            try{
                opcion=Integer.parseInt(sc.nextLine().trim());
            }catch(NumberFormatException e){
                System.out.println("❌ Solo números, por favor");
                continue;
            }

            switch (opcion) {
                case 1:
                    buscarLibrosPorTitulo();
                    break;
                case 2:
                    listarlibros();
                    break;
                case 3:
                    listarautores();
                    break;
                case 4:
                    buscarporaniodado();
                    break;
                case 5:
                    buscarPorIdioma();
                    break;
                case 6:
                    buscarTop10();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }
    public void imprimirLibros(List<Libros> libros){

            for(int i=0;i<libros.size();i++){
                String autores;

                if(libros.get(i).getAutores().isEmpty()){
                    autores="Autor no encontrado";
                }else{
                    autores = libros.get(i).getAutores().stream()
                            .map(Autores::getNombre)
                            .collect(Collectors.joining(", "));
                }
                System.out.println((i + 1) + ". " + libros.get(i).getTitulo() +"\n"+
                        " | Autor: " + autores + "\n"+
                        " | Idiomas: " + libros.get(i).getIdiomas()+
                        " | Descargas: " + libros.get(i).getDescargas());

            }


    }

    public void guardarLibro(DatosLibros libros){

        if(libroRepository.existsByIdApi(libros.idapi())){
            System.out.println("Libro ya registrado, intente con otro");
            return;
        }else {
            Libros libro = new Libros(libros);
            DatosAutor datosAutor = libros.autores().get(0);
            Autores autor = autorRepository.findByNombreIgnoreCase(datosAutor.nombre());
                if(autor == null){
                    autor = new Autores(datosAutor);
                    autor = autorRepository.save(autor);
                }
            libro.setAutores(List.of(autor));
            autor.getLibros().add(libro);
            libroRepository.save(libro);
            System.out.println("Libro guardado: "+libro.getTitulo());
        }
    }


    public void buscarLibrosPorTitulo(){
        System.out.println("Escribe el nombre del libro");
        var titulo = sc.nextLine().trim().replace(" ","+");
        String urlAc = URL_BASE+"?search="+titulo;
        while(urlAc !=null){
            System.out.println("Cargando por favor espera....");
            var json = consumoApi.obtenerDatos(urlAc);
            var respuestaBruta = conversor.obtenerDatos(json, RespuestaBruta.class);
            if(!respuestaBruta.Libros().isEmpty()) {
                List<Libros> lista = respuestaBruta.Libros().stream().map(e -> new Libros(e)).collect(Collectors.toList());
                imprimirLibros(lista);
                    System.out.println("Selecciona una opcion: \n" +
                            " 1 - Seleccionar Libro De Esta Pagina \n" +
                            " 2 - Pagina Anterior \n" +
                            " 3 - Pagina Siguiente \n" +
                            " 0 - Salir");
                    try {
                        var opcion = Integer.parseInt(sc.nextLine().trim());
                        if (opcion == 1) {
                            System.out.println("Ingresa el numero del libro");
                            int seleccion = Integer.parseInt(sc.nextLine().trim());
                            guardarLibro(respuestaBruta.Libros().get(seleccion - 1));
                        } else if (opcion == 2 && respuestaBruta.prev() != null) {
                            urlAc = respuestaBruta.prev();
                        } else if (opcion == 3 && respuestaBruta.next() != null) {
                            urlAc = respuestaBruta.next();
                        } else if (opcion == 0) {
                            return;
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("❌ Solo números, por favor");
                    }

            }
            else {
                System.out.println("Libro no encontrado, intente con otro");
                return;
            }
        }
    }
    public void listarlibros(){
        libros=libroRepository.findAllLibros();
        imprimirLibros(libros);
    }
    public void listarautores(){
        autores=autorRepository.findAllAutores();
        if(autores.isEmpty()){
            System.out.println("No hay autores registrados");
            return;
        }
        for (Autores autor : autores) {
            System.out.println("Nombre del Autor: "+autor.getNombre()+"\n"+
                    " | Año de nacimiento: " + (autor.getFechaDeNacimiento()==null ? "Año no encontrado": autor.getFechaDeNacimiento()) + "\n"+
                    " | Año de defuncion: " + (autor.getFechaDeDefencion()==null ? "Año no encontrado": autor.getFechaDeDefencion()));

            libros = autor.getLibros();
            if(libros.isEmpty()){
                System.out.println("No hay libros registrados");
            }else  {
                String titulos =libros.stream().map(Libros::getTitulo).collect(Collectors.joining(", "));
                System.out.println("Libros: "+titulos);
            }
        }

    }
    public void buscarporaniodado(){
        System.out.println("Ingresa el año: ");
        try{
            var anio = Long.parseLong(sc.nextLine().trim());
            autores = autorRepository.findAllByAnio(anio);
            if(autores.isEmpty()){
                System.out.println("No hay autores registrados");
            }
            else {
                for(Autores autor : autores){
                    System.out.println("Nombre: " + autor.getNombre() +
                            " (" + autor.getFechaDeNacimiento() + " - " +
                            (autor.getFechaDeDefencion() != null ? autor.getFechaDeDefencion() : "Actualidad") + ")");
                }
            }
        }
        catch (NumberFormatException e){
            System.out.println("Solo numeros por favor");
        }
    }
    public void  buscarPorIdioma() {
        System.out.println("Seleccione el idioma que desea buscar:");
        var menu = """
            es - Español
            en - Inglés
            fr - Francés
            pt - Portugués
            """;
        System.out.println(menu);
        var texto = sc.nextLine();
        try {
            var idiomaSeleccionado = Idioma.fromString(texto);
            List<Libros> libros = libroRepository.findLibrosPorIdioma(idiomaSeleccionado.getCategoriaGutendex());
            if (libros.isEmpty()) {
                System.out.println("No hay libros en " + idiomaSeleccionado.getCategoriaEspanol());
            } else {
                imprimirLibros(libros);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Opción no válida. Por favor ingrese un código correcto (ej: es, en).");
        }
    }
    public void buscarTop10(){
        System.out.println("Listando TOP 10");
        libros=libroRepository.ordenarConMasDescargas();
        if (libros.isEmpty()){
            System.out.println("No hay libros");
            return;
        }else{
            imprimirLibros(libros);
        }
    }
}

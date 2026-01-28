# 📚 LiterAlura - Catálogo de Libros

<p align="left">

   <img src="https://img.shields.io/badge/JAVA-17-orange">
   <img src="https://img.shields.io/badge/SPRING%20BOOT-3-green">
</p>

## 📝 Descripción

LiterAlura es una aplicación de consola que permite buscar libros, autores y estadísticas literarias mediante el consumo de la API **Gutendex**. La aplicación permite guardar los datos en una base de datos y poder realizar consultas sobre la información recolectada.

Este proyecto forma parte del desafío del programa Alura.

## ⚙️ Funcionalidades

El sistema cuenta con un menú interactivo que permite:

1.  **Buscar libro por título:** Consulta la API, muestra los resultados permite paginacion en caso de que no se encuentre el libro en la primera pagina y guarda el libro junto con su autor en la base de datos local.
<img width="1170" height="582" alt="image" src="https://github.com/user-attachments/assets/71530299-03b1-432e-99a3-3355d22c6989" />
<img width="1356" height="390" alt="image" src="https://github.com/user-attachments/assets/3129c183-bb57-4dee-8435-631c3bafd5a6" />
2.  **Listar libros guardados:** Muestra todos los libros que han sido buscados y guardados previamente.  
<img width="1309" height="677" alt="image" src="https://github.com/user-attachments/assets/c039d2cf-1498-42e4-8979-3256f9a246f1" />
3.  **Listar autores guardados:** Muestra los autores y junto con sus libros.
<img width="1305" height="797" alt="image" src="https://github.com/user-attachments/assets/87308617-3043-47ea-986e-52fa293e5574" />
4.  **Listar autores vivos en un año dado:** Permite filtrar autores que estaban vivos en un año especifico.
<img width="472" height="186" alt="image" src="https://github.com/user-attachments/assets/955269f4-996e-4091-8edb-b1d3627c9fcd" />
5.  **Listar libros por idioma:** Filtra la colección local por idiomas (ES, EN, FR, PT).
<img width="1300" height="560" alt="image" src="https://github.com/user-attachments/assets/205e5f2c-3a55-4966-8e89-49f84dfe705f" />
6.- **Mostrar TOP10 de libros:** Muestra los 10 libros mas descargados en la base de datos.
<img width="1269" height="700" alt="image" src="https://github.com/user-attachments/assets/6b55c5b2-c2bf-42f3-a65d-1a03354f26b9" />



## 🛠️ Tecnologías Utilizadas

* **Java 17:**
* **Spring Boot:** Framework para la inyección de dependencias y configuración automática.
* **Spring Data JPA:** Para la persistencia y manejo de la base de datos.
* **PostgreSQL:** Base de datos.
* **Gutendex API:** API pública de libros.
* **Jackson:** Para la deserialización de datos JSON provenientes de la API.

## 🚀 Cómo ejecutar el proyecto

1.  **Clonar el repositorio:**
    ```bash
    git clone [https://github.com/damipere/literaturaalura.git](https://github.com/damipere/literaturaalura.git)
    ```
2.  **Configurar variables de entorno:**
    Asegúrate de tener PostgreSQL corriendo y actualiza el archivo `application.properties` con tus credenciales:
    ```properties
    spring.datasource.url=jdbc:postgresql://${DB_HOST}/${DB_NAME}
    spring.datasource.username=${DB_USER}
    spring.datasource.password=${DB_PASSWORD}
    ```
3.  **Ejecutar la aplicación:**
    Desde tu IDE (IntelliJ/Eclipse) ejecuta la clase `LiteraturaaluraApplication.java`.

## 🗂️ Estructura del Proyecto

* `model`: Contiene las Entidades (Libros, Autores) y los Records para mapear la API.
* `repository`: Interfaces que extienden de `JpaRepository` para las consultas SQL.
* `service`: Lógica de consumo de API y conversión de datos.
* `principal`: Lógica del menú y flujo de usuario.

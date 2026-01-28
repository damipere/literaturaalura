package com.aluracursos.literaturaalura.literaturaalura;

import com.aluracursos.literaturaalura.literaturaalura.principal.Principal;
import com.aluracursos.literaturaalura.literaturaalura.repository.AutorRepository;
import com.aluracursos.literaturaalura.literaturaalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaaluraApplication implements CommandLineRunner {
	@Autowired
	private LibroRepository libroRepository;
	@Autowired
	private AutorRepository autorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaaluraApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(libroRepository,autorRepository);
		principal.iniciar();
	}
}

package com.aluracursos.literaturaalura.literaturaalura;

import com.aluracursos.literaturaalura.literaturaalura.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaaluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaaluraApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.iniciar();
	}
}

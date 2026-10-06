package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;
//Verificar la dependencia del pom para el .env

@SpringBootApplication
public class FinalApplication {

	public static void main(String[] args) {
		// Cargar variables del .env a System Properties
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		dotenv.entries().forEach(entry ->
				System.setProperty(entry.getKey(), entry.getValue())
		);
		//Las líneas anteriores dan error porque no logran cargar el env
		//Verificar la dependencia posteriormente
		SpringApplication.run(FinalApplication.class, args);
	}

}

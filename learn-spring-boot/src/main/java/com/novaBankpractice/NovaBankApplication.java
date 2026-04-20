package com.novaBankpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NovaBankApplication {
	public static void main(String[] args) {
		SpringApplication.run(NovaBankApplication.class, args);
		System.out.println("¡Servidor NovaBank iniciado! Listo para recibir peticiones de Postman.");
	}
}
package br.com.api.api_teste_mysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiTesteMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiTesteMysqlApplication.class, args);
		System.out.println("API Teste MySQL - Rodando na porta 8080");
	}

}

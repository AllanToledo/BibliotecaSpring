package com.allantoledo.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BibliotecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}

}

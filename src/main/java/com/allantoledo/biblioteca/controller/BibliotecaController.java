package com.allantoledo.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allantoledo.biblioteca.model.Livro;
import com.allantoledo.biblioteca.service.LivroService;

@RestController
@RequestMapping("/biblioteca")
public class BibliotecaController {
	
	@Autowired
	LivroService livroService;
	
	@GetMapping("/livros")
	public List<Livro> listarLivros() {
		return livroService.getAllLivros();
	}
	
}

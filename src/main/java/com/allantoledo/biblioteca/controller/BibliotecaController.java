package com.allantoledo.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allantoledo.biblioteca.model.Livro;
import com.allantoledo.biblioteca.service.LivroService;

@RestController
@RequestMapping("/biblioteca/livros")
public class BibliotecaController {
	
	@Autowired
	LivroService livroService;
	
	@GetMapping
	public List<Livro> listarLivros() {
		return livroService.getAllLivros();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Livro> listarLivro(@PathVariable Long id){
		try {
			Livro livro = livroService.getLivro(id);
			return ResponseEntity.ok(livro);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public Livro cadastrarLivro(@RequestBody Livro livro) {
		return livroService.saveLivro(livro);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Livro> updateLivro(@PathVariable Long id, @RequestBody Livro livro) {
		try {
			Livro atualizado = livroService.updateLivro(livro, id);
			return ResponseEntity.ok(atualizado);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Livro> deleteLivro(@PathVariable Long id){
		try {
			Livro livro = livroService.deleteLivro(id);
			return ResponseEntity.ok(livro);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}

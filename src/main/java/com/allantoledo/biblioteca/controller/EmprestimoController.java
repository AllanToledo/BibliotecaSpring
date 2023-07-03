package com.allantoledo.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allantoledo.biblioteca.model.Emprestimo;
import com.allantoledo.biblioteca.model.Usuario;
import com.allantoledo.biblioteca.service.EmprestimoService;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {
	
	@Autowired
	EmprestimoService emprestimoService;

	@PostMapping
	public Emprestimo criarEmprestimo(@RequestBody Emprestimo emprestimo){
		return emprestimoService.createEmprestimo(emprestimo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Emprestimo> updateEmprestimo(@RequestBody Emprestimo emprestimo, @PathVariable Long id) {
		try {
			Emprestimo atualizado = emprestimoService.updateEmprestimo(emprestimo, id);
			return ResponseEntity.ok(atualizado);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
}
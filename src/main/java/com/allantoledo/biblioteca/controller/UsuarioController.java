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

import com.allantoledo.biblioteca.model.Usuario;
import com.allantoledo.biblioteca.service.UsuarioService;

@RestController
@RequestMapping("/biblioteca/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping
	public List<Usuario> listAllUsers() {
		return usuarioService.getAllUsuarios();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUserBydId(@PathVariable Long id){
		try {
			Usuario usuario = usuarioService.getUsuario(id);
			return ResponseEntity.ok(usuario);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public Usuario cadastrarLivro(@RequestBody Usuario usuario) {
		return usuarioService.saveUsuario(usuario);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> updateLivro(@PathVariable Long id, @RequestBody Usuario usuario) {
		try {
			Usuario atualizado = usuarioService.updateUsuario(usuario, id);
			return ResponseEntity.ok(atualizado);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Usuario> deleteLivro(@PathVariable Long id){
		try {
			Usuario usuario = usuarioService.deleteUsuario(id);
			return ResponseEntity.ok(usuario);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}

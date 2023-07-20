package com.allantoledo.biblioteca.controller;

import java.util.List;

import com.allantoledo.biblioteca.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/biblioteca/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	PasswordEncoder passwordEncoder;
	
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
	public Usuario cadastrarUsuario(@RequestBody Usuario usuario) {
		return usuarioService.saveUsuario(usuario);
	}
	
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid Usuario data){
    	try {
    		usuarioService.findByLogin(data.getCorreioEletronico());
    		return ResponseEntity.badRequest().build();
    	} catch(NotFoundException ignored) {

    	}

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());

        Usuario usuario = new Usuario();
        usuario.setCorreioEletronico(data.getCorreioEletronico());
        usuario.setSenha(encryptedPassword);
        usuario.setRole(UserRole.USER);

        usuarioService.saveUsuario(usuario);

        return ResponseEntity.ok().build();
    }
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
		try {
			Usuario atualizado = usuarioService.updateUsuario(usuario, id);
			return ResponseEntity.ok(atualizado);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Usuario> deleteUsuario(@PathVariable Long id){
		try {
			Usuario usuario = usuarioService.deleteUsuario(id);
			return ResponseEntity.ok(usuario);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}

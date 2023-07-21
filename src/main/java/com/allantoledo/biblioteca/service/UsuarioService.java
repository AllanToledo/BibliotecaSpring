package com.allantoledo.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.allantoledo.biblioteca.model.Usuario;
import com.allantoledo.biblioteca.repository.UsuarioRepository;
import com.allantoledo.biblioteca.security.UserRole;

@Service
public class UsuarioService {
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public List<Usuario> getAllUsuarios(){
		return usuarioRepository.findAll();
	}
	
	public Usuario getUsuario(Long id) throws NotFoundException {
		return usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException());
	}
	
	public Usuario saveUsuario(Usuario usuario) {
		usuario.setRole(UserRole.ADMIN); //para fim de testes
		return usuarioRepository.save(usuario);
	}
	
	public Usuario updateUsuario(Usuario usuario, Long id) throws NotFoundException {
		Usuario atualizado = usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException());
		atualizado.setNome(usuario.getNome());
		if(usuario.getSenha() != null)
			atualizado.setSenha(usuario.getSenha());
		atualizado.setCorreioEletronico(usuario.getCorreioEletronico());
		atualizado.setTelefone(usuario.getTelefone());
		atualizado.setQuantidadeDeLivrosEmprestados(usuario.getQuantidadeDeLivrosEmprestados());
		
		usuarioRepository.save(atualizado);
		return atualizado;
	}
	
	public Usuario deleteUsuario(Long id) throws NotFoundException {
		Usuario deletado = usuarioRepository.findById(id).orElseThrow(()-> new NotFoundException());
		usuarioRepository.delete(deletado);
		return deletado;
	}

	
	public Usuario findByLogin(String login) throws NotFoundException {
		return usuarioRepository.findByLogin(login).orElseThrow(() -> new NotFoundException());
	}
	
}

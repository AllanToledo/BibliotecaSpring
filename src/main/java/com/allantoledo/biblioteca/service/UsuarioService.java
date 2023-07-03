package com.allantoledo.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.allantoledo.biblioteca.model.Usuario;
import com.allantoledo.biblioteca.repository.UsuarioRepository;

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
		return usuarioRepository.save(usuario);
	}
	
	public Usuario updateUsuario(Usuario usuario, Long id) throws NotFoundException {
		Usuario atualizado = usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException());
		atualizado.setNome(usuario.getNome());
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
	
}

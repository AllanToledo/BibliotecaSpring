package com.allantoledo.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.allantoledo.biblioteca.model.Livro;
import com.allantoledo.biblioteca.repository.LivroRepository;

@Service
public class LivroService {
	@Autowired
	LivroRepository livroRepository;
	
	public List<Livro> getAllLivros(){
		return livroRepository.findAll();
	}
	
	public Livro getLivro(Long id) throws NotFoundException {
		return livroRepository.findById(id).orElseThrow(() -> new NotFoundException());
	}
	
	public Livro saveLivro(Livro livro) {
		return livroRepository.save(livro);
	}
	
	public Livro updateLivro(Livro livro, Long id) throws NotFoundException {
		Livro atualizado = livroRepository.findById(id).orElseThrow(() -> new NotFoundException());
		atualizado.setTitulo(livro.getTitulo());
		atualizado.setDisponivel(livro.isDisponivel());
		atualizado.setAutor(livro.getAutor());
		atualizado.setAnoDePublicacao(livro.getAnoDePublicacao());
		livroRepository.save(atualizado);
		return atualizado;
	}
	
	public Livro deleteLivro(Long id) throws NotFoundException {
		Livro deletado = livroRepository.findById(id).orElseThrow(()-> new NotFoundException());
		livroRepository.delete(deletado);
		return deletado;
	}

}

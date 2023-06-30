package com.allantoledo.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

}

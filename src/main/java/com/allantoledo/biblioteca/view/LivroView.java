package com.allantoledo.biblioteca.view;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.allantoledo.biblioteca.model.Livro;
import com.allantoledo.biblioteca.service.LivroService;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Controller
@RequestMapping("/livro")
public class LivroView {
	@Autowired
	LivroService livroService;
	
	@Autowired
	private Validator validator;
	
	@GetMapping
	public ModelAndView listarLivros() {
		var view = new ModelAndView("livro/listarLivros");
		view.addObject("livros", livroService.getAllLivros());
		return view;
	}
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrarLivros() {
		var view = new ModelAndView("livro/cadastrarLivros");
		view.addObject("livro", new Livro());
		return view;
	}
	
	@GetMapping("/cadastrar/{id}")
	public ModelAndView cadastrarLivros(@PathVariable Long id) {
		var view = new ModelAndView("livro/cadastrarLivros");
		Livro livro;
		String erro = "";
		try {
			livro = livroService.getLivro(id);
		} catch (NotFoundException e) {
			livro = new Livro();
			erro = "Livro não encontrado.";
		}
		view.addObject("livro", livro);
		view.addObject("erro", erro);
		return view;
	}
	
	@PostMapping("/cadastrar")
	public ModelAndView cadastrarLivros(Livro livro) {
		Set<ConstraintViolation<Livro>> violations = validator.validate(livro);
		String problemas = "";
		String mensagem = "";
		if (!violations.isEmpty()) {
			problemas = violations.stream()
							.map(ConstraintViolation::getMessage)
							.collect(Collectors.joining("\n"));
		} else {
			livro.setDisponivel(true);
			livroService.saveLivro(livro);
			livro = new Livro();
			mensagem = "Cadastrado com sucesso!";
		}
		var view = new ModelAndView("livro/cadastrarLivros");
		view.addObject("mensagem", mensagem);
		view.addObject("erro", problemas);
		view.addObject("livro", livro);
		return view;
	}
}

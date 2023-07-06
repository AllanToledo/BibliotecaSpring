package com.allantoledo.biblioteca.view;

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

@Controller
@RequestMapping("/livro")
public class LivroView {
	@Autowired
	LivroService livroService;
	
	@GetMapping("/lista")
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
		try {
			livro = livroService.getLivro(id);
		} catch (NotFoundException e) {
			livro = new Livro();
		}
		view.addObject("livro", livro);
		return view;
	}
	
	@PostMapping("/cadastrar")
	public ModelAndView cadastrarLivros(Livro livro) {
		livro.setDisponivel(true);
		livroService.saveLivro(livro);
		var view = new ModelAndView("livro/cadastrarLivros");
		view.addObject("mensagem", "Sucesso!");
		view.addObject("livro", new Livro());
		return view;
	}
}

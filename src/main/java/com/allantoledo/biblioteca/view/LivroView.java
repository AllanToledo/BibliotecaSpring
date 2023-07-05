package com.allantoledo.biblioteca.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		var view = new ModelAndView("listarLivros");
		view.addObject("livros", livroService.getAllLivros());
		return view;
	}
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrarLivros() {
		var view = new ModelAndView("cadastrarLivros");
		view.addObject("livro", new Livro());
		return view;
	}
	
	@PostMapping("/cadastrar")
	public ModelAndView cadastrarLivros(Livro livro) {
		livro.setDisponivel(true);
		livroService.saveLivro(livro);
		var view = new ModelAndView("cadastrarLivros");
		view.addObject("mensagem", "Sucesso!");
		view.addObject("livro", new Livro());
		return view;
	}
}

package com.allantoledo.biblioteca.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.allantoledo.biblioteca.model.Emprestimo;
import com.allantoledo.biblioteca.model.Usuario;
import com.allantoledo.biblioteca.service.EmprestimoService;
import com.allantoledo.biblioteca.service.LivroService;
import com.allantoledo.biblioteca.service.UsuarioService;

@Controller
@RequestMapping("/emprestimo")
public class EmprestimoView {
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	EmprestimoService emprestimoService;
	
	@Autowired
	LivroService livroService;
	
	
	@GetMapping("/{usuarioId}")
	public ModelAndView listarEmprestimos(@PathVariable Long usuarioId) {
		String erro = "";
		Usuario usuario;
		try {
			usuario = usuarioService.getUsuario(usuarioId);
		} catch(NotFoundException e) {
			usuario = new Usuario();
			erro = "Usuário não encontrado.";
		}
		
		var view = new ModelAndView("emprestimo/listarEmprestimos");
		view.addObject("erro", erro);
		view.addObject("usuario", usuario);
		
		return view;
	}
	
	@GetMapping("/cadastrar/{usuarioId}")
	public ModelAndView cadastrarEmprestimos(@PathVariable Long usuarioId) {
		String erro = "";
		Usuario usuario;
		try {
			usuario = usuarioService.getUsuario(usuarioId);
		} catch(NotFoundException e) {
			usuario = new Usuario();
			erro = "Usuário não encontrado.";
			var view = new ModelAndView("erro");
			view.addObject("erro", erro);
			return view;
		}

		var emprestimo = new Emprestimo();
		emprestimo.setUsuario(usuario);
		
		var view = new ModelAndView("emprestimo/cadastrarEmprestimos");
		view.addObject("erro", erro);
		view.addObject("usuario", usuario);
		view.addObject("emprestimo", emprestimo);
		view.addObject("livroOpcoes", livroService.getAllLivros());
		
		return view;
	}
	
	@PostMapping("/cadastrar")
	public ModelAndView cadastrarEmprestimos(Emprestimo emprestimo) {
		String erro = "";
		Usuario usuario = emprestimo.getUsuario();
		try {
			emprestimoService.createEmprestimo(emprestimo);
		} catch(Exception e) {
			usuario = new Usuario();
			var view = new ModelAndView("erro");
			return view;
		}

		emprestimo = new Emprestimo();
		emprestimo.setUsuario(usuario);
		
		var view = new ModelAndView("emprestimo/cadastrarEmprestimos");
		view.addObject("erro", erro);
		view.addObject("usuario", usuario);
		view.addObject("emprestimo", emprestimo);
		view.addObject("livroOpcoes", livroService.getAllLivros());
		
		return view;
	}
}

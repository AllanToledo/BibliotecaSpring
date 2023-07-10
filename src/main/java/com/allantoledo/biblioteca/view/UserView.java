package com.allantoledo.biblioteca.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.allantoledo.biblioteca.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UserView {

	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping
	public ModelAndView listarUsuarios() {
		var view = new ModelAndView("usuario/listarUsuarios");
		view.addObject("usuarios", usuarioService.getAllUsuarios());
		return view;
	}
	
	
}

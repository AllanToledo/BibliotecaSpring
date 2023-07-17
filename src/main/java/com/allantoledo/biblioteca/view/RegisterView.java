//package com.allantoledo.biblioteca.view;
//
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.allantoledo.biblioteca.model.Usuario;
//import com.allantoledo.biblioteca.security.UserRole;
//
//@Controller
//@RequestMapping("/register")
//public class RegisterView {
//
//	@Autowired
//	private UserDetailsService userDetailsService;
//	@Autowired
//	PasswordEncoder passwordEncoder;
//	@Autowired
//	AuthenticationManager authenticationManager;
//
//	@GetMapping
//	public String register() {
//		return "register";
//	}
//
//	@PostMapping
//	public String createUser(@RequestBody Map<String, String> body) {
//		String usuario = body.get("user");
//		String passowrd = body.get("password");
//
//		Usuario user = new Usuario();
//		user.setCorreioEletronico(usuario);
//		user.setSenha(passwordEncoder.encode(passowrd));
//		user.setRole(UserRole.FUNCIONARIO);
//		
//		
//		return "redirect:/livro";
//	}
//
//}

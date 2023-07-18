//package com.allantoledo.biblioteca.view;
//
//import com.allantoledo.biblioteca.security.RegisterDTO;
//import com.allantoledo.biblioteca.security.UserRole;
//
//import jakarta.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//@RequestMapping("/login")
//public class LoginView {
//    @GetMapping
//    public ModelAndView login() {
//    	var view = new ModelAndView("login");
//        view.addObject("register", new RegisterDTO("", "", UserRole.USER));
//    	return view;
//    }
//    
//    @GetMapping("/erro")
//    public ModelAndView loginErro() {
//    	var view = new ModelAndView("login");
//        view.addObject("erro", "Usuário ou senha inccorretos.");
//        view.addObject("register", new RegisterDTO("", "", UserRole.USER));
//    	return view;
//    }
//
//    @GetMapping("/logout")
//    public String logout() {
//    	System.out.println(SecurityContextHolder.getContext().getAuthentication());
//    	SecurityContextHolder.getContext().setAuthentication(null);
//    	SecurityContextHolder.clearContext();
//    	return "redirect:/login";
//    }
//
//    
//    @Autowired
//    AuthenticationManager authManager;
//    
//    @PostMapping("/try")
//    public String loginTry(RegisterDTO data){
//    	System.out.println(data);
//        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
//        var auth =  authManager.authenticate(usernamePassword);
//        System.out.println(usernamePassword);
//        System.out.println(auth);
//        return "redirect:/livro";
//    }  
//}

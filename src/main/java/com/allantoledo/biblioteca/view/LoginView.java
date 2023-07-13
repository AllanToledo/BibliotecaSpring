package com.allantoledo.biblioteca.view;

import com.allantoledo.biblioteca.security.RegisterDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginView {
    @GetMapping
    public String login() {
        return "login";
    }

    @PostMapping
    public String logar(RegisterDTO registerDTO){

        return "redirect:/";
    }
}

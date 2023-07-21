package com.allantoledo.biblioteca.view;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.allantoledo.biblioteca.model.Usuario;
import com.allantoledo.biblioteca.security.UserRole;
import com.allantoledo.biblioteca.service.UsuarioService;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Controller
@RequestMapping("/novo")
public class RegistroView {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    Validator validator;

    @Autowired
    private PasswordEncoder passwordEncode;

    @GetMapping
    public ModelAndView cadastrarUsuarios() {
        var view = new ModelAndView("registrarUsuario");
        view.addObject("usuario", new Usuario());
        return view;
    }

    @PostMapping("/cadastrar")
    public ModelAndView cadastrarUsuarios(Usuario usuario) {
        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        String mensagem = "";
        String erro = "";
        if (!violations.isEmpty()) {
            erro = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\n"));

        } else {
        	usuario.setRole(UserRole.USER);
            usuario.setSenha(passwordEncode.encode(usuario.getSenha()));
            usuarioService.saveUsuario(usuario);
            usuario = new Usuario();
        }
        var view = new ModelAndView("registrarUsuario");
        view.addObject("mensagem", mensagem);
        view.addObject("erro", erro);
        view.addObject("usuario", usuario);
        return view;
    }
}

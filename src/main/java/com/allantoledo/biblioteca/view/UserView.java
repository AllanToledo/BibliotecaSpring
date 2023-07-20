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
import com.allantoledo.biblioteca.service.UsuarioService;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Controller
@RequestMapping("/usuario")
public class UserView {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    Validator validator;

    @Autowired
    private PasswordEncoder passwordEncode;

    @GetMapping
    public ModelAndView listarUsuarios() {
        var view = new ModelAndView("usuario/listarUsuarios");
        view.addObject("usuarios", usuarioService.getAllUsuarios());
        return view;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrarUsuarios() {
        var view = new ModelAndView("usuario/cadastrarUsuarios");
        view.addObject("usuario", new Usuario());
        return view;
    }

    @GetMapping("/cadastrar/{id}")
    public ModelAndView cadastrarUsuarios(@PathVariable long id) {
        var view = new ModelAndView("usuario/cadastrarUsuarios");
        Usuario usuario;
        String erro = "";
        try {
            usuario = usuarioService.getUsuario(id);
        } catch (NotFoundException e) {
            usuario = new Usuario();
            erro = "Usuário não encontrado.";
        }
        view.addObject("usuario", usuario);
        view.addObject("erro", erro);
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
            usuario.setSenha(passwordEncode.encode(usuario.getSenha()));
            try {
                if (usuario.getId() != null && usuario.getId() > 0) {
                    usuarioService.updateUsuario(usuario, usuario.getId());
                    mensagem = "Usuário atualizado com sucesso!";
                } else {
                    usuarioService.saveUsuario(usuario);
                    mensagem = "Usuário cadastrado com sucesso!";
                }
            } catch (NotFoundException e) {
                erro = "Usuário não encontrado.";
            }
            usuario = new Usuario();
        }
        var view = new ModelAndView("usuario/cadastrarUsuarios");
        view.addObject("mensagem", mensagem);
        view.addObject("erro", erro);
        view.addObject("usuario", usuario);
        return view;
    }
}

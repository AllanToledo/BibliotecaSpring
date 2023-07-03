package com.allantoledo.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.allantoledo.biblioteca.model.Usuario;

@EnableJpaRepositories
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}

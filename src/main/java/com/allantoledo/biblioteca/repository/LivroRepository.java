package com.allantoledo.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.allantoledo.biblioteca.model.Livro;

@EnableJpaRepositories
public interface LivroRepository extends JpaRepository<Livro, Long> {

}

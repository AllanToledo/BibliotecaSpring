package com.allantoledo.biblioteca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.allantoledo.biblioteca.model.Usuario;

@EnableJpaRepositories
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	 @Query(
		        value = "SELECT * FROM usuario u WHERE u.correio_eletronico = ?1",
		        nativeQuery = true
		    )
	Optional<Usuario> findByLogin(String login);

}

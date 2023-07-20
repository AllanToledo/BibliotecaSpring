package com.allantoledo.biblioteca.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.allantoledo.biblioteca.model.Usuario;
import com.allantoledo.biblioteca.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repo;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Usuario userDb; //Usuario do banco de dados
        User springUser; //Usuario do Spring Security, esse que ele usa para gerênciar as sessões

        userDb = repo.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        springUser = new User(
                userDb.getUsername(),
                userDb.getPassword(),
                userDb.getAuthorities());
        logger.error("User: " + springUser.toString());
        return springUser;

    }
}
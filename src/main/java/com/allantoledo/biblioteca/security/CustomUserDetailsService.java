package com.allantoledo.biblioteca.security;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Usuario user;
        org.springframework.security.core.userdetails.User springUser = null;

        user = repo.findByLogin(username).orElse(null);

        if (user != null) {

            springUser = new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    user.getAuthorities());
            return springUser;
        } else {
            //throw new UsernameNotFoundException(String.format("Username not found"));
            return null;

        }
        //return null;
    }
}
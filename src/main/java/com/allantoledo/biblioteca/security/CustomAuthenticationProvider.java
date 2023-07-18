package com.allantoledo.biblioteca.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final Log logger = LogFactory.getLog(getClass());

    
    @Autowired
    CustomUserDetailsService userService;


    @Override
    public Authentication authenticate(Authentication authentication) {

        UsernamePasswordAuthenticationToken authToken = null;
        SecurityContextHolder.clearContext();

        if (authentication == null) {
            return null;
        }
        String uname = String.valueOf(authentication.getName());
        String upassw = String.valueOf(authentication.getCredentials());

        logger.info("Username: " + uname + " Password: " + upassw);

        //User is an org.springframework.security.core.userdetails.User object
        User user = (User) userService.loadUserByUsername(uname);

        if (user == null) return null; //throw new UsernameNotFoundException(String.format("Username not found"));
    
        if (user.getPassword().equals(upassw)) {
            authToken = new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }    
        return authToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
}
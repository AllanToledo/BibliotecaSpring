package com.allantoledo.biblioteca.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
		.csrf(csrf -> csrf.disable())
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
		.authorizeHttpRequests(authorize -> authorize
				.requestMatchers(HttpMethod.GET, "/error").permitAll()
				.requestMatchers(HttpMethod.POST, "/novo/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/novo/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/livro").hasAuthority("USER")
				.requestMatchers(HttpMethod.GET, "/emprestimo").hasAuthority("FUNCIONARIO")
				.requestMatchers(HttpMethod.GET, "/usuario").hasAuthority("FUNCIONARIO")
				.requestMatchers(HttpMethod.POST, "/biblioteca/usuario/register").permitAll()
				.requestMatchers(HttpMethod.GET, "/biblioteca").hasAuthority("FUNCIONARIO")
				.requestMatchers(HttpMethod.DELETE, "/biblioteca").hasAuthority("ADMIN")
					.anyRequest().authenticated());
		
		httpSecurity.formLogin(form -> form
				.defaultSuccessUrl("/livro")
				.permitAll());
		httpSecurity.logout(logout -> logout
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.permitAll());
		return httpSecurity.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder);
	}

}

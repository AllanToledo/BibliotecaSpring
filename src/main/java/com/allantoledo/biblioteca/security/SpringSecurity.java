package com.allantoledo.biblioteca.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

	@Autowired
	private CustomAuthenticationProvider authProvider;

	// @Bean
	private AuthenticationManager authManager() throws Exception {
		// AuthenticationManager authManager = new ProviderManager(new
		// MyFirstCustomAuthenticationProvider());
		AuthenticationManager authManager = new ProviderManager(authProvider);
		return authManager;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.csrf()
				.disable()
				.exceptionHandling()
				.authenticationEntryPoint((request, response, authException) -> {
					response.setHeader("WWW-Authenticate", "Basic realm=SignIn");
				})
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeHttpRequests(authorize -> authorize
						// .requestMatchers(HttpMethod.POST, "/login/**").permitAll()
						// .requestMatchers(HttpMethod.GET, "/login/**").permitAll()
						.requestMatchers(HttpMethod.GET, "/login/**").permitAll()
						.requestMatchers(HttpMethod.GET, "/livro/**").hasRole("FUNCIONARIO")
						.requestMatchers(HttpMethod.GET, "/emprestimo/**").hasRole("FUNCIONARIO")
						.requestMatchers(HttpMethod.GET, "/usuario/**").hasRole("FUNCIONARIO")
						.requestMatchers(HttpMethod.GET, "/biblioteca/**").hasRole("FUNCIONARIO")
						.requestMatchers(HttpMethod.DELETE, "/biblioteca/**").hasRole("ADMIN").anyRequest()
						.authenticated())
				.httpBasic();

		httpSecurity.addFilter(new BasicAuthenticationFilter(authManager()));
		//httpSecurity.logout().logoutUrl("/livro").invalidateHttpSession(true).deleteCookies("JSESSIONID");
		return httpSecurity.build();
	}

}

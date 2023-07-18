package com.allantoledo.biblioteca.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.jaas.AuthorityGranter;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

	@Autowired
	private CustomAuthenticationProvider authProvider;

	@Bean
	public AuthenticationManager authManager() throws Exception {
		AuthenticationManager authManager = new ProviderManager(authProvider);
		return authManager;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
		.authorizeHttpRequests(authorize -> authorize
//				.requestMatchers(HttpMethod.POST, "/login/**").permitAll()
//				.requestMatchers(HttpMethod.GET, "/login/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/livro").hasAuthority("FUNCIONARIO")
				.requestMatchers(HttpMethod.GET, "/emprestimo").hasAuthority("FUNCIONARIO")
				.requestMatchers(HttpMethod.GET, "/usuario").hasAuthority("FUNCIONARIO")
				.requestMatchers(HttpMethod.GET, "/biblioteca").hasAuthority("FUNCIONARIO")
				.requestMatchers(HttpMethod.DELETE, "/biblioteca").hasAuthority("FUNCIONARIO")
					.anyRequest().authenticated());
		
		httpSecurity.formLogin().defaultSuccessUrl("/livro");
		httpSecurity.logout();
//				.securityContext((securityContext) -> securityContext
//						.securityContextRepository(new RequestAttributeSecurityContextRepository()));
		// .httpBasic();
		//httpSecurity.addFilter(new UsernamePasswordAuthenticationFilter(authManager()));   

		// httpSecurity.addFilter(new BasicAuthenticationFilter(authManager()));
		// httpSecurity.logout().logoutUrl("/livro").invalidateHttpSession(true).deleteCookies("JSESSIONID");
		return httpSecurity.build();
	}

}

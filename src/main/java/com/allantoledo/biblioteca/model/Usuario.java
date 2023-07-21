package com.allantoledo.biblioteca.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.allantoledo.biblioteca.security.UserRole;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Usuario implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Nome não pode ser vazio.")
	private String nome;

	private String telefone;

	@Size(min = 12, message = "A senha precisa ter no mínimo 12 caracteres.")
	@NotBlank(message = "Precisa conter uma senha.")
	private String senha;

	@Email(message = "É necessário um endereço de email válido.")
	@NotBlank(message = "É obrigatório um endereço de email.")
	private String correioEletronico;

	private UserRole role = UserRole.USER;

	private int quantidadeDeLivrosEmprestados;

	@OneToMany(mappedBy = "usuario")
	@ToString.Exclude
	private List<Emprestimo> livros;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		switch (this.role) {
		case ADMIN:
			return List.of(new SimpleGrantedAuthority("ADMIN"), new SimpleGrantedAuthority("FUNCIONARIO"), new SimpleGrantedAuthority("USER"));
		case FUNCIONARIO:
			return List.of(new SimpleGrantedAuthority("FUNCIONARIO"), new SimpleGrantedAuthority("USER"));
		default:
			return List.of(new SimpleGrantedAuthority("USER"));
		}
	}

	@Override
	public final boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		Class<?> oEffectiveClass = o instanceof HibernateProxy
				? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
				: o.getClass();
		Class<?> thisEffectiveClass = this instanceof HibernateProxy
				? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
				: this.getClass();
		if (thisEffectiveClass != oEffectiveClass)
			return false;
		Usuario usuario = (Usuario) o;
		return getId() != null && Objects.equals(getId(), usuario.getId());
	}

	@Override
	public final int hashCode() {
		return getClass().hashCode();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.correioEletronico;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}

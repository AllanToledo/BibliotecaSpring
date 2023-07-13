package com.allantoledo.biblioteca.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Nome não pode ser vazio.")
	private String nome;
	
	private String telefone;
	
	@Size(min=12, message="A senha precisa ter no mínimo 12 caracteres.")
	@NotBlank(message="Precisa conter uma senha.")
	private String senha;
	
	@Email(message="É necessário um endereço de email válido.")
	@NotBlank(message="É obrigatório um endereço de email.")
	private String correioEletronico;
	
	private int quantidadeDeLivrosEmprestados;
	
	@OneToMany(mappedBy="usuario")
	@ToString.Exclude
	private List<Emprestimo> livros;

	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
		Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
		if (thisEffectiveClass != oEffectiveClass) return false;
		Usuario usuario = (Usuario) o;
		return getId() != null && Objects.equals(getId(), usuario.getId());
	}

	@Override
	public final int hashCode() {
		return getClass().hashCode();
	}
}

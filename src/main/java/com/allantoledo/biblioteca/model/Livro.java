package com.allantoledo.biblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message="Qual o titulo do livro????")
	private String titulo;
	@NotBlank(message="Alguém deve ter escrito, se não sabe, coloca anônimo.")
	private String autor;
	@Max(value=2099, message="Para de inventar numeros.")
	@Min(value=1000, message="Obviamente está mentindo o ano, esse livro nem existe.")
	private int anoDePublicacao;
	private Boolean disponivel;

	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
		Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
		if (thisEffectiveClass != oEffectiveClass) return false;
		Livro livro = (Livro) o;
		return getId() != null && Objects.equals(getId(), livro.getId());
	}

	@Override
	public final int hashCode() {
		return getClass().hashCode();
	}
}
package com.allantoledo.biblioteca.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Emprestimo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name="id_usuario")
	@JsonIgnore
	@ToString.Exclude
	private Usuario usuario;
	@ManyToOne
	@JoinColumn(name="id_livro")
	private Livro livro;
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private LocalDate dataDeEmprestimo;
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private LocalDate dataPrevistaDeDevolucao;
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private LocalDate dataRealDeDevolucao;

	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
		Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
		if (thisEffectiveClass != oEffectiveClass) return false;
		Emprestimo that = (Emprestimo) o;
		return getId() != null && Objects.equals(getId(), that.getId());
	}

	@Override
	public final int hashCode() {
		return getClass().hashCode();
	}
}

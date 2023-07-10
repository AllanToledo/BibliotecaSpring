package com.allantoledo.biblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank(message="Qual o titulo do livro????")
	private String titulo;
	@NotBlank(message="Alguém deve ter escrito, se não sabe, coloca anônimo.")
	private String autor;
	@Max(value=2099, message="Para de inventar numeros.")
	@Min(value=1000, message="Obviamente está mentindo o ano, esse livro nem existe.")
	private int anoDePublicacao;
	private Boolean disponivel;
	
	
}
package com.allantoledo.biblioteca.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Data
@NoArgsConstructor
public class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
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
	private List<Emprestimo> livros;
	
}

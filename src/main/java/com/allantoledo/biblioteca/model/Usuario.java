package com.allantoledo.biblioteca.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Data
@NoArgsConstructor
public class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String nome;
	private String telefone;
	private String senha;
	private String correioEletronico;
	private int quantidadeDeLivrosEmprestados;
	@OneToMany(mappedBy="usuario")
	private List<Emprestimo> livros;
	
}

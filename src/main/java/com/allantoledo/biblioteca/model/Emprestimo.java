package com.allantoledo.biblioteca.model;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Emprestimo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_usuario")
	@JsonIgnore
	private Usuario usuario;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_livro")
	private Livro livro;
	private Calendar dataDeEmprestimo;
	private Calendar dataPrevistaDeDevolucao;
	private Calendar dataRealDeDevolucao;
	
	
}

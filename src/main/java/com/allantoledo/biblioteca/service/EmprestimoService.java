package com.allantoledo.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.allantoledo.biblioteca.model.Emprestimo;
import com.allantoledo.biblioteca.repository.EmprestimoRepository;

@Service
public class EmprestimoService {

	@Autowired
	EmprestimoRepository emprestimoRepository;

	public Emprestimo updateEmprestimo(Emprestimo emprestimo, Long id) throws NotFoundException {
		Emprestimo atualizado = emprestimoRepository.findById(id).orElseThrow(NotFoundException::new);
		atualizado.setDataDeEmprestimo(emprestimo.getDataDeEmprestimo());
		atualizado.setDataPrevistaDeDevolucao(emprestimo.getDataPrevistaDeDevolucao());
		atualizado.setDataRealDeDevolucao(emprestimo.getDataRealDeDevolucao());
		emprestimoRepository.save(atualizado);
		return atualizado;
		
	}

	public Emprestimo getEmprestimo(Long id) throws NotFoundException {
		return emprestimoRepository.findById(id).orElseThrow(NotFoundException::new);
	}

	public Emprestimo createEmprestimo(Emprestimo emprestimo) {
		return emprestimoRepository.save(emprestimo);
	}
	
	public void deleteEmprestimo(Emprestimo emprestimo) {
		emprestimoRepository.delete(emprestimo);
	}
}

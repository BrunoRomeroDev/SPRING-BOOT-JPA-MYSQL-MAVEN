package com.algaworks.algalog.domain.services;

import org.springframework.stereotype.Service;

import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import com.algaworks.algalog.exceptiohandler.EntidadeNaoEncontradaException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {
	
	private EntregaRepository entregarepository;

	public Entrega buscar(Long entregaId) {
		return entregarepository.findById(entregaId)
				.orElseThrow(()-> new EntidadeNaoEncontradaException("Entrega não encontrada"));
	}
}

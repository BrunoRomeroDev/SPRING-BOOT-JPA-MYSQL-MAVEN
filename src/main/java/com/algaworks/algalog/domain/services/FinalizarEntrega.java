package com.algaworks.algalog.domain.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FinalizarEntrega {
	
	private EntregaRepository entregarepository;
	private BuscaEntregaService buscaentregaservice;
	
	
	@Transactional
	public void finalizar(Long entregaid) {
		Entrega entrega = buscaentregaservice.buscar(entregaid);
		
		entrega.finalizar();
		
		entregarepository.save(entrega);
	}
	
	

}

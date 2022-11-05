package com.algaworks.algalog.domain.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.api.model.Ocorrencia;
import com.algaworks.algalog.domain.model.Entrega;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {
	
	private BuscaEntregaService buscaentregaservice;
	
	@Transactional
	public Ocorrencia registrar(Long entregaId, String descricao) {
	Entrega entrega = buscaentregaservice.buscar(entregaId);
		
		entrega.adicionarOcorrencia(descricao);
		return entrega.adicionarOcorrencia(descricao);
		
	}

}

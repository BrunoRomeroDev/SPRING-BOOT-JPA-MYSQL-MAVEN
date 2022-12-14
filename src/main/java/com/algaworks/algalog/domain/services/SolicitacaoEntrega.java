package com.algaworks.algalog.domain.services;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.StatusEntrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;

import lombok.Data;
@Data
@Service
public class SolicitacaoEntrega {
	
	@Autowired
	private EntregaRepository entregarepository;
	@Autowired
	private CatalogoClienteService catalogorepository;
	
	
	@Transactional
	public Entrega solicitar(Entrega entrega) {
		Cliente cliente = catalogorepository.buscar(entrega.getCliente().getId());
						
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());
		entrega.setDataFinalizacao(OffsetDateTime.now());
		
		return entregarepository.save(entrega);
		
	}

}

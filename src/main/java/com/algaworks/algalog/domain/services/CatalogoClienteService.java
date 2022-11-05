package com.algaworks.algalog.domain.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.domain.exceptio.NegocioException;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import com.algaworks.algalog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CatalogoClienteService {
	
	
	private ClienteRepository clienteRepository;
	
	
	public Cliente buscar(Long clienteId) {
	return clienteRepository.findById(clienteId)
			.orElseThrow(()-> new NegocioException("Cliente não encontrado"));
	}
	@Transactional
	public Cliente salvar(Cliente cliente) {
		boolean emailemuso = clienteRepository.findByEmail(cliente.getEmail())
				.stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		
		if(emailemuso) {
			
			throw new NegocioException("Já Existe esse email cadastrado");
			
		}
		
			return clienteRepository.save(cliente);
		
	}
	@Transactional
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
	
}

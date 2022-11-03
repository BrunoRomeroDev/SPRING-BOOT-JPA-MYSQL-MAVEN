package com.algaworks.algalog.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("/clientes")
	public List<Cliente> listar() {

		return clienteRepository.findAll();
	}
	
	@GetMapping("/clientes/{nome}")
	public Cliente finByNome(@PathVariable String nome) {

		return clienteRepository.findByNome(nome);
	}
	
	@GetMapping("/clientes/nomes/{nome}")
	public List<Cliente> finByNomes(@PathVariable String nome) {

		return clienteRepository.findByNomeContaining(nome);
	}
	
	@GetMapping("/clientes/email/{email}")
	public ResponseEntity<Cliente> findByEmail(@PathVariable String email) {
		
		return clienteRepository.findByEmail(email)
				.map(cliente -> ResponseEntity.ok(cliente))
				.orElse(ResponseEntity.notFound().build());
				
			/*Optional<Cliente> cliente = clienteRepository.findByEmail(email);
			
			if(cliente.isPresent()) {
				return ResponseEntity.ok(cliente.get());
			}
		
			return ResponseEntity.notFound().build(); */
	}
	
	@PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	@PutMapping("/clientes/atualizar/{clienteid}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteid, @RequestBody Cliente cliente){
		if(!clienteRepository.existsById(clienteid)) {
			return ResponseEntity.notFound().build(); 
		}
		cliente.setId(clienteid);
		cliente = clienteRepository.save(cliente);
		
		return ResponseEntity.ok(cliente);
		
	}
	@DeleteMapping("/clientes/delete/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId){
		if(!clienteRepository.existsById(clienteId)) {
			
		return ResponseEntity.notFound().build();
		}
		clienteRepository.deleteById(clienteId);
		return ResponseEntity.noContent().build();
	}
}

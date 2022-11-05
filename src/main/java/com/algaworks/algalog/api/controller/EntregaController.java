package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.api.model.EntregaModel;
import com.algaworks.algalog.api.model.assembler.EntregaAssembler;
import com.algaworks.algalog.api.model.input.EntregaInput;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import com.algaworks.algalog.domain.services.FinalizarEntrega;
import com.algaworks.algalog.domain.services.SolicitacaoEntrega;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entrega")
public class EntregaController {
	
	
	private SolicitacaoEntrega solicitacaoentregaservice;
	private EntregaRepository entregarepository;
	private EntregaAssembler entregaassembler;
	private FinalizarEntrega finalizacaoentregaservice;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Entrega solicitar(@Valid @RequestBody EntregaInput entregainput) {
		Entrega novaentrega = entregaassembler.toEntity(entregainput);
		Entrega entregaSolicitada = solicitacaoentregaservice.solicitar(novaentrega);
		
		return solicitacaoentregaservice.solicitar(entregaSolicitada);
	}
	
	@GetMapping
	public List<EntregaModel> listar(){
		return entregaassembler.toCollectionModel(entregarepository.findAll());
		
	}
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId){
		return entregarepository.findById(entregaId)
				.map(entrega -> ResponseEntity.ok(entregaassembler.toModel(entrega)))
				.orElse(ResponseEntity.notFound().build());
				
	}
	
	@PutMapping("/{entregaid}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar (@PathVariable Long entregaid) {
		finalizacaoentregaservice.finalizar(entregaid);
		
	}

}

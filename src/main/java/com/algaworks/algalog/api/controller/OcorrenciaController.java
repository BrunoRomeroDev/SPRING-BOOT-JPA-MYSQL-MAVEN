package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.api.model.Ocorrencia;
import com.algaworks.algalog.api.model.OcorrenciaModel;
import com.algaworks.algalog.api.model.assembler.OcorrenciaAssembler;
import com.algaworks.algalog.api.model.input.OcorrenciaInput;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.services.BuscaEntregaService;
import com.algaworks.algalog.domain.services.RegistroOcorrenciaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaid}/ocorrencias")
public class OcorrenciaController {
	
	private BuscaEntregaService buscaentregaservice;	
	private RegistroOcorrenciaService registroocorrenciaservice;
	private OcorrenciaAssembler ocorrenciaassembler;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaModel registrar(@PathVariable Long entregaid,
			@Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
				
		Ocorrencia ocorrencia = registroocorrenciaservice
				.registrar(entregaid,ocorrenciaInput.getDescricao());
		return ocorrenciaassembler.toModel(ocorrencia);
		
	}
	
	@GetMapping
	public List<OcorrenciaModel> listar(@PathVariable Long entregaid){
		Entrega entrega = buscaentregaservice.buscar(entregaid);
		return ocorrenciaassembler.toCollectionModel(entrega.getOcorrencias()) ;
		
	}
	
}

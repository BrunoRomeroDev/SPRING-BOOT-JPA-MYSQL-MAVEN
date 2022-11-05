package com.algaworks.algalog.api.model.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaworks.algalog.api.model.Ocorrencia;
import com.algaworks.algalog.api.model.OcorrenciaModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OcorrenciaAssembler {
	
	private ModelMapper modelmapper;
	
	public OcorrenciaModel toModel(Ocorrencia ocorrencia) {
		return modelmapper.map(ocorrencia, OcorrenciaModel.class);
	}

	public List<OcorrenciaModel> toCollectionModel(List<Ocorrencia> ocorrencias){
		return ocorrencias.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
						
	}
}

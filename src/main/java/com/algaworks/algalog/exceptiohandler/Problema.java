package com.algaworks.algalog.exceptiohandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
@Data
public class Problema {
	
	public Problema() {
		
	}

	private Integer status;
	private OffsetDateTime datahora;
	private String titulo;
	private List<Campo> campos;
	
	@AllArgsConstructor
	@Data 
	public static class Campo{
		private String nome;
		private String mensagem;
	}
}

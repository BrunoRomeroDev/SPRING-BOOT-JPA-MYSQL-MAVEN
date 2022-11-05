package com.algaworks.algalog.exceptiohandler;

import com.algaworks.algalog.domain.exceptio.NegocioException;

public class EntidadeNaoEncontradaException extends NegocioException {


	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String message) {
		super(message);
		
	}

}

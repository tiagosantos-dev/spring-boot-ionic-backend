package com.santostiago.cursomc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	List<FieldMessage> erros = new ArrayList<>();

	public ValidationError(Integer status, String msg, Long timestamp) {
		super(status, msg, timestamp);
		
		
	}

	public List<FieldMessage> getErros() {
		return erros;
	}

	public void addErros(String message , String field) {
		erros.add(new FieldMessage(message, field));
	}
	
	

}

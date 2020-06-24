package com.santostiago.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.santostiago.cursomc.domain.Cliente;
import com.santostiago.cursomc.domain.enuns.TipoCliente;
import com.santostiago.cursomc.dto.ClienteDTO;
import com.santostiago.cursomc.dto.ClienteNewDTO;
import com.santostiago.cursomc.repositories.ClienteRepository;
import com.santostiago.cursomc.resources.exceptions.FieldMessage;
import com.santostiago.cursomc.services.validation.utils.BR;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	@Autowired
	private HttpServletRequest request ;
	@Autowired
	private ClienteRepository clienteRepository;
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>(); 
		
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
			Integer UriId=  Integer.parseInt( map.get("id"));
		
		Cliente cli = clienteRepository.findByEmail(objDto.getEmail());
		if(cli != null && !cli.getId().equals(UriId)) {
			list.add(new FieldMessage("email","Email Ja existente"));
			
		}
		
		
		// inclua os testes aqui, inserindo erros na lista
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getField())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}

package com.santostiago.cursomc.services;

import java.util.Optional;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.santostiago.cursomc.domain.Cliente;
import com.santostiago.cursomc.repositories.ClienteRepository;
import com.santostiago.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public 	Cliente buscar(Integer id) {
	Optional<Cliente> obj = repo.findById(id);
	return obj.orElseThrow(()-> new ObjectNotFoundException(
			"Objeto não encontrado!! ID "+ id +"Tipo: "+Cliente.class.getName()));
			
	}
}

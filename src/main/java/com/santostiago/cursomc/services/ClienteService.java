package com.santostiago.cursomc.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.santostiago.cursomc.domain.Cidade;
import com.santostiago.cursomc.domain.Cliente;
import com.santostiago.cursomc.domain.Endereco;
import com.santostiago.cursomc.domain.enuns.TipoCliente;
import com.santostiago.cursomc.dto.ClienteDTO;
import com.santostiago.cursomc.dto.ClienteNewDTO;
import com.santostiago.cursomc.repositories.ClienteRepository;
import com.santostiago.cursomc.repositories.EnderecoRepository;
import com.santostiago.cursomc.services.exceptions.DataIntegrityException;
import com.santostiago.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public 	Cliente find(Integer id) {
	Optional<Cliente> obj = repo.findById(id);
	return obj.orElseThrow(()-> new ObjectNotFoundException(
			"Objeto não encontrado!! ID "+ id +"Tipo: "+Cliente.class.getName()));
			
	}
	
	public Cliente insert(Cliente cli) {
		cli =repo.save(cli);
		enderecoRepository.saveAll(cli.getEnderecos());
		return cli;
		
		
	}
	
	public Cliente fromDTO(ClienteNewDTO dto) {
		Cliente cli = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpfOuCnpj(),TipoCliente.toEnum(dto.getTipo()));
		Cidade cidade = new Cidade(dto.getCidade_id(), null,null);
		Endereco end = new Endereco(null, dto.getLogradouro(), dto.getNumero(), dto.getComplemento(), dto.getBairro(), dto.getCep(), cli, cidade);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(dto.getTelefone1());
		
		if(dto.getTelefone2() != null) {
			cli.getTelefones().add(dto.getTelefone2());
		}
		if(dto.getTelefone3() != null) {
			cli.getTelefones().add(dto.getTelefone3());
		}
		return cli;
		
	}
	
	@Transactional
	public Cliente update(Cliente obj) {
		
		Cliente newObj = find(obj.getId());
		updateCliente(newObj, obj);
		return repo.save(obj);

	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {

			throw new DataIntegrityException("Não é possivel exlcuir uma Cliente há entidades relacionadas");
		}

	}

	public List<Cliente> findByNome(String nomeCliente) {
		List<Cliente> lista = repo.findByNome(nomeCliente);
		return lista;

	}

	public List<Cliente> findAll() {

		return repo.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);

	}

	public Cliente fromCliente(ClienteDTO objDto) {
		return new Cliente(objDto.getId(),objDto.getNome(), objDto.getEmail(),null,null);
		
	
}
	
	private void updateCliente(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		
	}
}

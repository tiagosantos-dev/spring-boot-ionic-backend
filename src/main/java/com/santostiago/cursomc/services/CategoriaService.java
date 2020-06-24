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

import com.santostiago.cursomc.domain.Categoria;
import com.santostiago.cursomc.dto.CategoriaDTO;
import com.santostiago.cursomc.repositories.CategoriaRepository;
import com.santostiago.cursomc.services.exceptions.DataIntegrityException;
import com.santostiago.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado!! ID" + id + "Tipo:" + Categoria.class.getName()));

	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);

	}

	@Transactional
	public Categoria update(Categoria categoria) {
		Categoria newObject = find(categoria.getId());
		updateCliente(categoria, newObject);
		return repo.save(newObject);
		
	}
	

	private void updateCliente(Categoria categoria, Categoria newObject) {
		newObject.setNome(categoria.getNome());
	
		
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {

			throw new DataIntegrityException("Não é possivel exlcuir uma categoria com produtos");
		}

	}

	public List<Categoria> findByNome(String nomeCategoria) {
		List<Categoria> lista = repo.findByNome(nomeCategoria);
		return lista;

	}

	public List<Categoria> findAll() {

		return repo.findAll();
	}

	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);

	}

	public Categoria fromCategoria(CategoriaDTO objDto) {

			return new Categoria(objDto.getId(), objDto.getNome());
		
		
	}
}

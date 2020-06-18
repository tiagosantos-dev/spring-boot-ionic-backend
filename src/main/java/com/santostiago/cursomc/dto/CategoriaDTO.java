package com.santostiago.cursomc.dto;

import com.santostiago.cursomc.domain.Categoria;

public class CategoriaDTO {
	
	private Integer id;
	private String nome;
	

	
	
	public CategoriaDTO() {
		
		
	}
	
	public CategoriaDTO(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
		
		
	}
	
	
	public String getNome() {
		return nome;
		
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Integer getId() {
		return id;
		
	}
	
	public void setNome(Integer id) {
		this.id = id;
	}
	
}

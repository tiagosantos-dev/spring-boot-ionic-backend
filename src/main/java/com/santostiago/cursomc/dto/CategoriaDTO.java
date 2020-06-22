package com.santostiago.cursomc.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.santostiago.cursomc.domain.Categoria;


public class CategoriaDTO {
	
	private Integer id;
	
	@NotEmpty(message = "campo deve ser mais que 4 e menor que 80")
	@Length(min = 4, max = 80)
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

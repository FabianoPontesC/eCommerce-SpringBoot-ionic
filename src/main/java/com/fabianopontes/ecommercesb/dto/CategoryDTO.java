package com.fabianopontes.ecommercesb.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fabianopontes.ecommercesb.domain.Category;


public class CategoryDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	

	@NotEmpty(message="Empty is not allowed")
	@Size(min=5, max=80, message="You must fill in 5 to 80 characters ")
	private String nome;
	
	public CategoryDTO() {
	}
	
	public CategoryDTO(Category obj) {
		id = obj.getId();
		nome = obj.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}

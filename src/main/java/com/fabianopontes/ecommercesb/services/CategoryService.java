package com.fabianopontes.ecommercesb.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabianopontes.ecommercesb.domain.Category;
import com.fabianopontes.ecommercesb.repositories.CategoryRepository;
import com.fabianopontes.ecommercesb.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;

	public Category find(Integer id) {
		 Optional<Category> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Object not found! Id: " + id + ", Type: " + Category.class.getName()));
	} 
	
	public Category insert(Category obj) {
		obj.setId(null);
		return repo.save(obj);
	}
}

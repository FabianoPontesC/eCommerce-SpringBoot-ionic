package com.fabianopontes.ecommercesb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.fabianopontes.ecommercesb.domain.Category;
import com.fabianopontes.ecommercesb.dto.CategoryDTO;
import com.fabianopontes.ecommercesb.repositories.CategoryRepository;
import com.fabianopontes.ecommercesb.services.exceptions.DataIntegrityException;
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

	public Category update(Category obj) {
		Category newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("You can't delete a category with products");
		}
	}
	
	public List<Category> findAll() {
		return repo.findAll();
	}
	
	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Category fromDTO(CategoryDTO objDto) {
		return new Category(objDto.getId(), objDto.getNome());
	}

	private void updateData(Category newObj, Category obj) {
		newObj.setNome(obj.getNome());
	}	
}

package com.fabianopontes.ecommercesb.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabianopontes.ecommercesb.domain.Orderr;
import com.fabianopontes.ecommercesb.repositories.OrderRepository;
import com.fabianopontes.ecommercesb.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repo;

	public Orderr find(Integer id) {
		 Optional<Orderr> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Object not found! Id: " + id + ", Type: " + Orderr.class.getName()));
		} 	
}

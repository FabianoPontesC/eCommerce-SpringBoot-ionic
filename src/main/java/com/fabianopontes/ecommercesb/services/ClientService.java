package com.fabianopontes.ecommercesb.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabianopontes.ecommercesb.domain.Client;
import com.fabianopontes.ecommercesb.repositories.ClientRepository;
import com.fabianopontes.ecommercesb.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repo;

	public Client find(Integer id) {
		 Optional<Client> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Object not found! Id: " + id + ", Type: " + Client.class.getName()));
		} 	
}

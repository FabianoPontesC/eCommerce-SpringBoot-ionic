package com.fabianopontes.ecommercesb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabianopontes.ecommercesb.domain.Address;
import com.fabianopontes.ecommercesb.domain.City;
import com.fabianopontes.ecommercesb.domain.Client;
import com.fabianopontes.ecommercesb.domain.enums.ClientType;
import com.fabianopontes.ecommercesb.dto.ClientDTO;
import com.fabianopontes.ecommercesb.dto.ClientNewDTO;
import com.fabianopontes.ecommercesb.repositories.AddressRepository;
import com.fabianopontes.ecommercesb.repositories.ClientRepository;
import com.fabianopontes.ecommercesb.services.exceptions.DataIntegrityException;
import com.fabianopontes.ecommercesb.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repo;
	
	@Autowired
	private AddressRepository addressRepository;

	public Client find(Integer id) {
		 Optional<Client> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Object not found! Id: " + id + ", Type: " + Client.class.getName()));
	}
	
	@Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		obj = repo.save(obj);
		addressRepository.saveAll(obj.getAddresses());
		return obj;
	}	
	
	public Client update(Client obj) {
		Client newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("You can't delete because there are relationship entities");
		}
	}
	
	public List<Client> findAll() {
		return repo.findAll();
	}
	
	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Client fromDTO(ClientDTO objDto) {
		return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null);
	}
	
	public Client fromDTO(ClientNewDTO objDto) {
		Client cli = new Client(null, objDto.getName(), objDto.getEmail(), objDto.getCpfOrCnpj(), ClientType.toEnum(objDto.getType()));
		City cid = new City(objDto.getCidadeId(), null, null);
		Address end = new Address(null, objDto.getAddress(), objDto.getNumber(), objDto.getComplement(), objDto.getNeighborhood(), objDto.getZip(), cli, cid);
		cli.getAddresses().add(end);
		cli.getPhones().add(objDto.getTelefone1());
		if (objDto.getTelefone2()!=null) {
			cli.getPhones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3()!=null) {
			cli.getPhones().add(objDto.getTelefone3());
		}
		return cli;
	}
		
	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
}

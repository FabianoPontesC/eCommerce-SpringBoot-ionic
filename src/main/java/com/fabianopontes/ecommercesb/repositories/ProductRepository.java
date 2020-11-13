package com.fabianopontes.ecommercesb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fabianopontes.ecommercesb.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
}

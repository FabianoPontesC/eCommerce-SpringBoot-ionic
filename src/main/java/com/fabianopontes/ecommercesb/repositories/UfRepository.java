package com.fabianopontes.ecommercesb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fabianopontes.ecommercesb.domain.Uf;

@Repository
public interface UfRepository extends JpaRepository<Uf, Integer>{
	
}

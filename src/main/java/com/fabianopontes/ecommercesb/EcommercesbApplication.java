package com.fabianopontes.ecommercesb;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fabianopontes.ecommercesb.domain.Category;
import com.fabianopontes.ecommercesb.domain.City;
import com.fabianopontes.ecommercesb.domain.Product;
import com.fabianopontes.ecommercesb.domain.Uf;
import com.fabianopontes.ecommercesb.repositories.CategoryRepository;
import com.fabianopontes.ecommercesb.repositories.CityRepository;
import com.fabianopontes.ecommercesb.repositories.ProductRepository;
import com.fabianopontes.ecommercesb.repositories.UfRepository;

@SpringBootApplication
public class EcommercesbApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private UfRepository ufRepository;
	@Autowired
	private CityRepository cityRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(EcommercesbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");
		
		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
			
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		
		Uf uf1 = new Uf(null, "MG");
		Uf uf2 = new Uf(null, "SP");
		
		City c1 = new City(null, "Uberlândia", uf1);
		City c2 = new City(null, "São Paulo", uf2);
		City c3 = new City(null, "Campinas", uf2);
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));

		uf1.getCities().addAll(Arrays.asList(c1));
		uf2.getCities().addAll(Arrays.asList(c2, c3));
		
		ufRepository.saveAll(Arrays.asList(uf1, uf2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));
	}

}

package com.fabianopontes.ecommercesb;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fabianopontes.ecommercesb.domain.Address;
import com.fabianopontes.ecommercesb.domain.Category;
import com.fabianopontes.ecommercesb.domain.City;
import com.fabianopontes.ecommercesb.domain.Client;
import com.fabianopontes.ecommercesb.domain.OrderItem;
import com.fabianopontes.ecommercesb.domain.Orderr;
import com.fabianopontes.ecommercesb.domain.Payment;
import com.fabianopontes.ecommercesb.domain.PaymentWithBankSlip;
import com.fabianopontes.ecommercesb.domain.PaymentWithCreditCard;
import com.fabianopontes.ecommercesb.domain.Product;
import com.fabianopontes.ecommercesb.domain.Uf;
import com.fabianopontes.ecommercesb.domain.enums.ClientType;
import com.fabianopontes.ecommercesb.domain.enums.PaymentState;
import com.fabianopontes.ecommercesb.repositories.AddressRepository;
import com.fabianopontes.ecommercesb.repositories.CategoryRepository;
import com.fabianopontes.ecommercesb.repositories.CityRepository;
import com.fabianopontes.ecommercesb.repositories.ClientRepository;
import com.fabianopontes.ecommercesb.repositories.OrderItemRepository;
import com.fabianopontes.ecommercesb.repositories.OrderRepository;
import com.fabianopontes.ecommercesb.repositories.PaymentRepository;
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
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(EcommercesbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");
		Category cat3 = new Category(null, "Bath");
		Category cat4 = new Category(null, "Eletronics");
		Category cat5 = new Category(null, "Garden");
		Category cat6 = new Category(null, "Decoration");
		Category cat7 = new Category(null, "Perfumary");
		
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
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));

		uf1.getCities().addAll(Arrays.asList(c1));
		uf2.getCities().addAll(Arrays.asList(c2, c3));
		
		ufRepository.saveAll(Arrays.asList(uf1, uf2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "36378912377", ClientType.PESSOAFISICA);
		cli1.getPhones().addAll(Arrays.asList("27363323", "93838393"));
		
		Address addr1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Address addr2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getAddresses().addAll(Arrays.asList(addr1, addr2));
		
		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(addr1, addr2));
		
		
		  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm"); 
		  Orderr ord1 = new Orderr(null, sdf.parse("30/09/2017 10:32"), cli1, addr1); 
		  Orderr ord2 = new Orderr(null, sdf.parse("10/10/2017 19:35"), cli1, addr2);
		  
		  Payment pay1 = new PaymentWithCreditCard(null, PaymentState.PAID, ord1, 6);
		  ord1.setPayment(pay1);
		  
		  Payment pay2 = new PaymentWithBankSlip(null, PaymentState.PENDING, ord2, sdf.parse("20/10/2017 00:00"), null); 
		  ord2.setPayment(pay2);
		  
		  cli1.getOrders().addAll(Arrays.asList(ord1, ord2));
		  
		  orderRepository.saveAll(Arrays.asList(ord1, ord2));
		  paymentRepository.saveAll(Arrays.asList(pay1, pay2));
		  
		  OrderItem oi1 = new OrderItem(ord1, p1, 0.00, 1, 2000.00);
		  OrderItem oi2 = new OrderItem(ord1, p3, 0.00, 2, 80.00);
		  OrderItem oi3 = new OrderItem(ord2, p2, 100.00, 1, 800.00);
		  
		  ord1.getItems().addAll(Arrays.asList(oi1, oi2));
		  ord2.getItems().addAll(Arrays.asList(oi3));
		  
		  p1.getItems().addAll(Arrays.asList(oi1));
		  p2.getItems().addAll(Arrays.asList(oi3));
		  p3.getItems().addAll(Arrays.asList(oi2));
		  
		  orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3));
	}

}

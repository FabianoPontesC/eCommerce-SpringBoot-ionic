package com.fabianopontes.ecommercesb.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabianopontes.ecommercesb.domain.OrderItem;
import com.fabianopontes.ecommercesb.domain.Orderr;
import com.fabianopontes.ecommercesb.domain.PaymentWithBankSlip;
import com.fabianopontes.ecommercesb.domain.enums.PaymentState;
import com.fabianopontes.ecommercesb.repositories.OrderItemRepository;
import com.fabianopontes.ecommercesb.repositories.OrderRepository;
import com.fabianopontes.ecommercesb.repositories.PaymentRepository;
import com.fabianopontes.ecommercesb.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repo;
	
	@Autowired
	private BankSlipService bankSlipService;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	public Orderr find(Integer id) {
		 Optional<Orderr> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Object not found! Id: " + id + ", Type: " + Orderr.class.getName()));
		} 	
	
	@Transactional(readOnly=true)
	public Orderr insert(Orderr obj) {
		obj.setId(null);
		obj.setInstant(new Date());
		obj.getPayment().setState(PaymentState.PENDING);
		obj.getPayment().setOrder(obj);
		if (obj.getPayment() instanceof PaymentWithBankSlip) {
			PaymentWithBankSlip paymt = (PaymentWithBankSlip) obj.getPayment();
			bankSlipService.fillPaymentWithBankSlip(paymt, obj.getInstant());
		}
		obj = repo.save(obj);
		paymentRepository.save(obj.getPayment());
		for (OrderItem oi : obj.getItems()) {
			oi.setDiscount(0.0);
			oi.setPrice(productService.find(oi.getProduct().getId()).getPrice());
			oi.setOrderr(obj);
		}
		
		orderItemRepository.saveAll(obj.getItems());
		return obj;
	}
}

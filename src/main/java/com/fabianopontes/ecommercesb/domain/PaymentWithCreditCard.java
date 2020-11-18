package com.fabianopontes.ecommercesb.domain;

import javax.persistence.Entity;

import com.fabianopontes.ecommercesb.domain.enums.PaymentState;

@Entity
public class PaymentWithCreditCard extends Payment{
	private static final long serialVersionUID = 1L;	

	private Integer installmentsNumber;
	
	public PaymentWithCreditCard() {
	}

	public PaymentWithCreditCard(Integer id, PaymentState state, ClientOrder order, Integer installmentsNumber) {
		super(id, state, order);
		this.installmentsNumber = installmentsNumber;
	}

	public Integer getInstallmentsNumber() {
		return installmentsNumber;
	}

	public void setInstallmentsNumber(Integer installmentsNumber) {
		this.installmentsNumber = installmentsNumber;
	}
	
}

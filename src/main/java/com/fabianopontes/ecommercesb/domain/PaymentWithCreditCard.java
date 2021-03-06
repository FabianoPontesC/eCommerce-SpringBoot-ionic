package com.fabianopontes.ecommercesb.domain;

import javax.persistence.Entity;

import com.fabianopontes.ecommercesb.domain.enums.PaymentState;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("paymentWithCreditCard")
public class PaymentWithCreditCard extends Payment{
	private static final long serialVersionUID = 1L;	

	private Integer installmentsNumber;
	
	public PaymentWithCreditCard() {
	}

	public PaymentWithCreditCard(Integer id, PaymentState state, Orderr order, Integer installmentsNumber) {
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

package com.fabianopontes.ecommercesb.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fabianopontes.ecommercesb.domain.enums.PaymentState;

@Entity
public class PaymentWithBankSlip extends Payment {
	private static final long serialVersionUID = 1L;	

	private Date dueDate;
	private Date paymentDate;
	
	public PaymentWithBankSlip() {
	}

	public PaymentWithBankSlip(Integer id, PaymentState state, ClientOrder order, Date dueDate, Date paymentDate) {
		super(id, state, order);
		this.paymentDate = paymentDate;
		this.dueDate = dueDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
}

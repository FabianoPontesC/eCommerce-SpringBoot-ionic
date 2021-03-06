package com.fabianopontes.ecommercesb.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fabianopontes.ecommercesb.domain.enums.PaymentState;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("paymentWithBankSlip")
public class PaymentWithBankSlip extends Payment {
	private static final long serialVersionUID = 1L;	

	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dueDate;
	
	@JsonFormat(pattern="dd/MM/yyyy")	
	private Date paymentDate;
	
	public PaymentWithBankSlip() {
	}

	public PaymentWithBankSlip(Integer id, PaymentState state, Orderr order, Date dueDate, Date paymentDate) {
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

package com.fabianopontes.ecommercesb.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.fabianopontes.ecommercesb.domain.PaymentWithBankSlip;

@Service
public class BankSlipService {

	public void fillPaymentWithBankSlip(PaymentWithBankSlip paymt, Date orderInstant) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(orderInstant);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		paymt.setDueDate(cal.getTime());
	}
}

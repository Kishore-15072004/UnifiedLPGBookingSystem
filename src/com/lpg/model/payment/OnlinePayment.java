package com.lpg.model.payment;

public class OnlinePayment implements Payment {
	@Override
	public void pay(double amount) {
		if(amount <= 0) {
			System.out.println("Invalid Amount");
		}
		else {
			System.out.println("Payment Successful Through Online Payment");
			System.out.println("Amount Paid : Rs." +amount);
		}
	}
}
package com.lpg.model.payment;

public class CashOnDelivery implements Payment {
	@Override
	public void pay(double amount) {
		if(amount <= 0) {
			System.out.println("Invalid Amount");
		}
		else {
			System.out.println("Cash On Delivery Selected");
			System.out.println("Pay Rs." + amount +" During Delivery");
		}
	}
}
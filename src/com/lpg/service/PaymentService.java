package com.lpg.service;


import com.lpg.model.payment.*;
import com.lpg.util.Validator;
import com.lpg.exception.*;

public class PaymentService {

	public void processPayment(int choice,double amount,String cardNo,String cvv,String upi) throws InvalidCardException, InvalidCVVException, InvalidUPIException, InvalidPaymentException {

		if(amount <= 0) {
			throw new InvalidPaymentException("Payment amount must be greater than zero.");
		}

		Payment payment = null;

		if(choice == 1) {

			Validator.validateCardNumber(cardNo);
			Validator.validateCVV(cvv);

			System.out.println("Card Payment Selected");

			payment = new OnlinePayment();
		}

		else if(choice == 2) {

			Validator.validateUPI(upi);

			System.out.println("UPI Payment Selected");

			payment = new OnlinePayment();
		}

		else if(choice == 3) {

			System.out.println("Cash On Delivery Selected");

			payment = new CashOnDelivery();
		}

		else {

			throw new InvalidPaymentException("Invalid payment option selected. Please choose 1 for Card, 2 for UPI, or 3 for Cash on Delivery.");
		}

		payment.pay(amount);

		System.out.println("\n=======================================================");
		System.out.println("Payment Successful! Amount paid: \\u20B9" + amount);
	}
}
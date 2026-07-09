package com.lpg.service;

import com.lpg.model.user.Customer;
import com.lpg.util.ConsoleUI;

public class SubsidyService {

	public double applySubsidy(Customer customer, double amount) {
		if (customer.isSubsidyEligible()) {
			ConsoleUI.drawDataRow("Subsidy Deduction", "- Rs.200.00");
			amount -= 200;
		}
		return amount;
	}

	public double applyMembershipDiscount(Customer customer, double amount) {
		if (customer.getMembershipType().equalsIgnoreCase("Premium")) {
			double discount = amount * 0.10;
			ConsoleUI.drawDataRow("Premium Discount (10%)", "- Rs." + String.format("%.2f", discount));
			amount -= discount;
		}
		return amount;
	}

	public double applyFestivalOffer(double amount) {
		double discount = amount * 0.05;
		ConsoleUI.drawDataRow("Festival Offer (5%)", "- Rs." + String.format("%.2f", discount));
		return amount - discount;
	}

	public double applyCashback(double amount) {
		if (amount >= 1500) {
			ConsoleUI.drawDataRow("Cashback (Order >= Rs.1500)", "- Rs.100.00");
			amount -= 100;
		}
		return amount;
	}

	public double calculateFinalBill(Customer customer, double amount) {
		ConsoleUI.drawHeaderTitle("BILL CALCULATION BREAKDOWN");
		ConsoleUI.drawDataRow("Original Amount", "Rs." + String.format("%.2f", amount));
		ConsoleUI.drawPanelDivider();
		amount = applySubsidy(customer, amount);
		amount = applyMembershipDiscount(customer, amount);
		amount = applyFestivalOffer(amount);
		amount = applyCashback(amount);
		ConsoleUI.drawPanelDivider();
		ConsoleUI.drawDataRow("FINAL PAYABLE AMOUNT", "Rs." + String.format("%.2f", amount));
		ConsoleUI.drawPanelBottom();
		return amount;
	}
}

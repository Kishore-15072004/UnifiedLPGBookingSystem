package com.lpg.model.booking;

import com.lpg.model.cylinder.*;
import com.lpg.model.user.*;
import com.lpg.util.ConsoleUI;

public class Booking {
	private int    bookingId;
	private Customer customer;
	private Cylinder cylinder;
	private int    quantity;
	private double totalAmount;
	private String bookingStatus;

	public Booking(int bookingId, Customer customer, Cylinder cylinder,
			int quantity, double totalAmount, String bookingStatus) {
		this.bookingId     = bookingId;
		this.customer      = customer;
		this.cylinder      = cylinder;
		this.quantity      = quantity;
		this.totalAmount   = totalAmount;
		this.bookingStatus = bookingStatus;
	}

	public int      getBookingId()     { return bookingId; }
	public Customer getCustomer()      { return customer; }
	public Cylinder getCylinder()      { return cylinder; }
	public int      getQuantity()      { return quantity; }
	public double   getTotalAmount()   { return totalAmount; }
	public String   getBookingStatus() { return bookingStatus; }
	public synchronized void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
		notifyAll();
	}

	public synchronized void waitForConfirmation() throws InterruptedException {
		while ("Pending".equalsIgnoreCase(bookingStatus)) {
			wait();
		}
	}

	public void displayBookingDetails() {
		ConsoleUI.drawDataRow("Booking ID",     String.valueOf(bookingId));
		ConsoleUI.drawDataRow("Customer",       customer.getName());
		ConsoleUI.drawDataRow("Cylinder Type",  cylinder.getCylinderType());
		ConsoleUI.drawDataRow("Brand",          cylinder.getBrandName());
		ConsoleUI.drawDataRow("Quantity",       String.valueOf(quantity));
		ConsoleUI.drawDataRow("Total Amount",   "Rs." + totalAmount);
		ConsoleUI.drawDataRow("Status",         bookingStatus);
		ConsoleUI.drawPanelDivider();
	}
}

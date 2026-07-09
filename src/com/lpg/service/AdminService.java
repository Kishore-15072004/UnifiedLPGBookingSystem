package com.lpg.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.lpg.model.booking.Booking;
import com.lpg.model.cylinder.Cylinder;
import com.lpg.model.user.Customer;
import com.lpg.exception.*;
import com.lpg.util.ConsoleUI;

public class AdminService {

	public void addCylinderStock(int cylinderId, int stock)
			throws CylinderNotFoundException, InvalidInputException {
		if (stock <= 0) throw new InvalidInputException("Stock to add must be greater than zero.");
		ArrayList<Cylinder> cylinders = CylinderService.getCylinders();
		boolean found = false;
		for (int i = 0; i < cylinders.size(); i++) {
			Cylinder cylinder = cylinders.get(i);
			if (cylinder.getCylinderId() == cylinderId) {
				int updated = cylinder.getStock() + stock;
				cylinder.setStock(updated);
				ConsoleUI.successBox("Stock Added! Updated Stock for Cylinder #" + cylinderId + ": " + updated);
				found = true;
				break;
			}
		}
		if (!found) throw new CylinderNotFoundException("Cylinder ID " + cylinderId + " not found.");
	}

	public void viewAllCustomers() {
		ArrayList<Customer> customers = RegistrationService.getCustomers();
		if (customers.isEmpty()) { ConsoleUI.errorBox("No customers registered yet."); return; }
		ConsoleUI.drawHeaderTitle("REGISTERED CUSTOMERS");
		for (int i = 0; i < customers.size(); i++) customers.get(i).displayCustomerDetails();
		ConsoleUI.drawPanelBottom();
	}

	public void viewAllBookings() {
		List<Booking> bookings = BookingService.getBookings();
		if (bookings.isEmpty()) { ConsoleUI.errorBox("No bookings found."); return; }
		ConsoleUI.drawHeaderTitle("ALL BOOKINGS");
		for (int i = 0; i < bookings.size(); i++) bookings.get(i).displayBookingDetails();
		ConsoleUI.drawPanelBottom();
	}

	public void viewPendingBookings() {
		List<Booking> bookings = BookingService.getBookings();
		boolean found = false;
		for (Booking booking : bookings) {
			if ("Pending".equalsIgnoreCase(booking.getBookingStatus())) {
				if (!found) {
					ConsoleUI.drawHeaderTitle("PENDING BOOKINGS");
					found = true;
				}
				booking.displayBookingDetails();
			}
		}
		if (!found) ConsoleUI.errorBox("No pending bookings found.");
	}

	public void confirmPendingBooking(int bookingId) {
		BookingService bookingService = new BookingService();
		try {
			bookingService.confirmBooking(bookingId);
		} catch (Exception e) {
			ConsoleUI.errorBox("Booking confirmation failed: " + e.getMessage());
		}
	}

	public void generateBookingReport() {
		List<Booking> bookings = BookingService.getBookings();
		if (bookings.isEmpty()) { ConsoleUI.errorBox("No bookings available to generate report."); return; }

		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		ConsoleUI.drawHeaderTitle("BOOKING REPORT");
		ConsoleUI.drawDataRow("Generated On", LocalDateTime.now().format(fmt));
		ConsoleUI.drawPanelDivider();

		double grandTotal = 0;
		for (int i = 0; i < bookings.size(); i++) {
			bookings.get(i).displayBookingDetails();
			grandTotal += bookings.get(i).getTotalAmount();
		}
		ConsoleUI.drawDataRow("GRAND TOTAL REVENUE", "Rs." + grandTotal);
		ConsoleUI.drawPanelBottom();
		ConsoleUI.successBox("Booking Report Generated Successfully!");
	}
}

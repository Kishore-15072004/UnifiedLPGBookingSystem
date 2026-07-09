package com.lpg.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import com.lpg.model.booking.Booking;
import com.lpg.model.cylinder.Cylinder;
import com.lpg.model.user.Customer;
import com.lpg.exception.*;
import com.lpg.util.ConsoleUI;

public class BookingService {
	private static final List<Booking> bookings = Collections.synchronizedList(new ArrayList<>());
	private static final Random RANDOM = new Random();

	private synchronized int generateUniqueBookingId() {
		int bookingId;
		while (true) {
			bookingId = 1000 + RANDOM.nextInt(9000);
			boolean exists = false;
			for (Booking booking : bookings) {
				if (booking.getBookingId() == bookingId) {
					exists = true;
					break;
				}
			}
			if (!exists) {
				return bookingId;
			}
		}
	}

	public Booking requestBooking(Customer customer, Cylinder cylinder, int quantity)
			throws InvalidBookingException, CylinderNotFoundException, InsufficientStockException {

		if (customer == null)
			throw new InvalidBookingException("Customer details not found. Please log in again.");
		if (cylinder == null)
			throw new CylinderNotFoundException("Cylinder not found. Please select a valid cylinder.");
		if (quantity <= 0)
			throw new InvalidBookingException("Quantity must be greater than zero.");

		CylinderService cylinderService = new CylinderService();
		if (!cylinderService.isStockAvailable(cylinder.getCylinderId(), quantity))
			throw new InsufficientStockException("Requested quantity unavailable. Available stock: " + cylinder.getStock());

		ConsoleUI.successBox("Booking request submitted and is now pending admin confirmation.");

		double baseAmount = cylinder.getPrice() * quantity;
		ConsoleUI.drawDataRow("Base Amount", "Rs." + baseAmount);

		SubsidyService subsidyService = new SubsidyService();
		double totalAmount = subsidyService.calculateFinalBill(customer, baseAmount);

		int bookingId = generateUniqueBookingId();
		Booking booking = new Booking(bookingId, customer, cylinder, quantity, totalAmount, "Pending");
		bookings.add(booking);
		booking.displayBookingDetails();

		Thread confirmationThread = new Thread(() -> {
			try {
				booking.waitForConfirmation();
				if ("Confirmed".equalsIgnoreCase(booking.getBookingStatus())) {
					ConsoleUI.successBox("Admin has confirmed Booking #" + booking.getBookingId() + ". Please complete payment in the customer portal.");
				} else if ("Cancelled".equalsIgnoreCase(booking.getBookingStatus())) {
					ConsoleUI.errorBox("Booking #" + booking.getBookingId() + " was cancelled by admin.");
				} else {
					ConsoleUI.errorBox("Booking #" + booking.getBookingId() + " status changed to " + booking.getBookingStatus() + ".");
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}, "BookingConfirmation-" + bookingId);
		confirmationThread.setDaemon(true);
		confirmationThread.start();

		return booking;
	}

	public synchronized void confirmBooking(int bookingId)
			throws InvalidBookingException, CylinderNotFoundException, InsufficientStockException {

		Booking booking = findBookingById(bookingId);
		if (!"Pending".equalsIgnoreCase(booking.getBookingStatus()))
			throw new InvalidBookingException("Only pending bookings can be confirmed.");

		CylinderService cylinderService = new CylinderService();
		if (!cylinderService.isStockAvailable(booking.getCylinder().getCylinderId(), booking.getQuantity()))
			throw new InsufficientStockException("Cannot confirm booking. Stock unavailable for cylinder: "
				+ booking.getCylinder().getBrandName());

		cylinderService.updateStock(booking.getCylinder().getCylinderId(), booking.getQuantity());
		booking.setBookingStatus("Confirmed");
		ConsoleUI.successBox("Booking #" + bookingId + " confirmed successfully.");
	}

	public synchronized void payConfirmedBooking(Customer customer, int bookingId, int paymentChoice,
			String cardNo, String cvv, String upi)
			throws InvalidBookingException, CylinderNotFoundException, InsufficientStockException {

		Booking booking = findBookingByIdAndCustomer(bookingId, customer);
		if (!"Confirmed".equalsIgnoreCase(booking.getBookingStatus()))
			throw new InvalidBookingException("Only confirmed bookings may be paid.");

		PaymentService paymentService = new PaymentService();
		paymentService.processPayment(paymentChoice, booking.getTotalAmount(), cardNo, cvv, upi);
		booking.setBookingStatus("Completed");
		ConsoleUI.successBox("Payment completed for Booking #" + bookingId + ".");
	}

	public void cancelBooking(Customer customer, int bookingId) {
		for (int i = 0; i < bookings.size(); i++) {
			Booking booking = bookings.get(i);
			if (booking.getBookingId() == bookingId &&
				booking.getCustomer().getUserId() == customer.getUserId()) {
				if ("Completed".equalsIgnoreCase(booking.getBookingStatus())) {
					ConsoleUI.errorBox("Completed bookings cannot be cancelled.");
					return;
				}

				if ("Confirmed".equalsIgnoreCase(booking.getBookingStatus())) {
					int restoredStock = booking.getCylinder().getStock() + booking.getQuantity();
					booking.getCylinder().setStock(restoredStock);
					ConsoleUI.successBox("Stock restored after cancellation. Current stock: " + restoredStock);
				}

				booking.setBookingStatus("Cancelled");
				ConsoleUI.successBox("Booking #" + bookingId + " Cancelled Successfully.");
				return;
			}
		}
		ConsoleUI.errorBox("Booking ID #" + bookingId + " Not Found for your account.");
	}

	public void displayAllBookings() {
		if (bookings.isEmpty()) { ConsoleUI.errorBox("No Bookings Available."); return; }
		ConsoleUI.drawHeaderTitle("ALL BOOKINGS");
		synchronized (bookings) {
			for (Booking booking : bookings) {
				booking.displayBookingDetails();
			}
		}
	}

	public void displayPendingBookings() {
		boolean found = false;
		synchronized (bookings) {
			for (Booking booking : bookings) {
				if ("Pending".equalsIgnoreCase(booking.getBookingStatus())) {
					if (!found) {
						ConsoleUI.drawHeaderTitle("PENDING BOOKINGS");
						found = true;
					}
					booking.displayBookingDetails();
				}
			}
		}

		if (!found) ConsoleUI.errorBox("No pending bookings available.");
	}

	public synchronized void displayCustomerBookings(Customer customer) {
		if (customer == null) { ConsoleUI.errorBox("Customer Details Not Found."); return; }
		boolean found = false;
		ConsoleUI.drawPanelTop();
		ConsoleUI.drawPanelDivider();
		for (Booking booking : bookings) {
			if (booking.getCustomer().getUserId() == customer.getUserId()) {
				booking.displayBookingDetails();
				found = true;
			}
		}
		if (!found) ConsoleUI.errorBox("No Bookings Found For This Customer.");
	}

	public static List<Booking> getBookings() { return bookings; }

	public synchronized Booking getBookingById(int bookingId) throws InvalidBookingException {
		return findBookingById(bookingId);
	}

	private synchronized Booking findBookingById(int bookingId) throws InvalidBookingException {
		for (Booking booking : bookings) {
			if (booking.getBookingId() == bookingId) return booking;
		}
		throw new InvalidBookingException("Booking ID #" + bookingId + " not found.");
	}

	private synchronized Booking findBookingByIdAndCustomer(int bookingId, Customer customer)
			throws InvalidBookingException {
		Booking booking = findBookingById(bookingId);
		if (booking.getCustomer().getUserId() != customer.getUserId())
			throw new InvalidBookingException("Booking ID #" + bookingId + " does not belong to this customer.");
		return booking;
	}
}

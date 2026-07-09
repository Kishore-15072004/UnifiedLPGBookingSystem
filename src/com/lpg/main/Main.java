package com.lpg.main;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.lpg.model.booking.*;
import com.lpg.model.cylinder.*;
import com.lpg.model.payment.*;
import com.lpg.model.user.*;
import com.lpg.service.*;
import com.lpg.util.*;

public class Main {

	// ── Input helpers ────────────────────────────────────────────────────────────

	public static int getValidInt(Scanner sc) {
		while (true) {
			try {
				int value = sc.nextInt();
				sc.nextLine();
				return value;
			} catch (Exception e) {
			ConsoleUI.errorBox("Invalid Input - Enter Numbers Only");
				sc.nextLine();
				ConsoleUI.drawInputFieldPrompt("Retry");
			}
		}
	}

	public static String getValidMobile(Scanner sc) {
		while (true) {
			try {
				String mobile = sc.nextLine();
				Validator.validateMobile(mobile);
				return mobile;
			} catch (Exception e) {
				ConsoleUI.errorBox(e.getMessage());
				ConsoleUI.drawInputFieldPrompt("Retry Mobile");
			}
		}
	}

	public static String getValidPassword(Scanner sc) {
		while (true) {
			try {
				String password = sc.nextLine();
				Validator.validatePassword(password);
				return password;
			} catch (Exception e) {
				ConsoleUI.errorBox(e.getMessage());
				ConsoleUI.drawInputFieldPrompt("Retry Password");
			}
		}
	}

	public static String getValidCard(Scanner sc) {
		while (true) {
			try {
				String card = sc.nextLine();
				Validator.validateCardNumber(card);
				return card;
			} catch (Exception e) {
				ConsoleUI.errorBox(e.getMessage());
				ConsoleUI.drawInputFieldPrompt("Retry Card Number");
			}
		}
	}

	public static String getValidCVV(Scanner sc) {
		while (true) {
			try {
				String cvv = sc.nextLine();
				Validator.validateCVV(cvv);
				return cvv;
			} catch (Exception e) {
				ConsoleUI.errorBox(e.getMessage());
				ConsoleUI.drawInputFieldPrompt("Retry CVV");
			}
		}
	}

	public static String getValidUPI(Scanner sc) {
		while (true) {
			try {
				String upi = sc.nextLine();
				Validator.validateUPI(upi);
				return upi;
			} catch (Exception e) {
				ConsoleUI.errorBox(e.getMessage());
				ConsoleUI.drawInputFieldPrompt("Retry UPI ID");
			}
		}
	}

	// ── Main ─────────────────────────────────────────────────────────────────────

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		RegistrationService registrationService = new RegistrationService();
		LoginService        loginService        = new LoginService();
		CylinderService     cylinderService     = new CylinderService();
		BookingService      bookingService      = new BookingService();
		PaymentService      paymentService      = new PaymentService();
		AdminService        adminService        = new AdminService();

		cylinderService.addCylinder(new DomesticCylinder(1,  "Domestic",   14.2,  950,  50, "Indane",    true));
		cylinderService.addCylinder(new CommercialCylinder(2, "Commercial", 19.0, 1800,  30, "HPGas",     "Hotel"));
		cylinderService.addCylinder(new DomesticCylinder(3,  "Domestic",   14.2,  980,  40, "BharatGas", true));
		cylinderService.addCylinder(new CommercialCylinder(4, "Commercial", 19.0, 1900,  25, "Indane",    "Restaurant"));
		cylinderService.addCylinder(new DomesticCylinder(5,  "Domestic",   14.2,  940,  45, "HPGas",     true));
		cylinderService.addCylinder(new CommercialCylinder(6, "Commercial", 19.0, 1850,  20, "BharatGas", "Restaurant"));

		Message message = msg -> ConsoleUI.successBox(msg);

		// Splash + boot
		ConsoleUI.showCombinedLandingScreen();
		ConsoleUI.bootAnimation();

		// ── Main loop ────────────────────────────────────────────────────────────
		while (true) {

			// Main menu box
			ConsoleUI.starSectionHeader("LPG BOOKING SYSTEM - MAIN MENU", ConsoleUI.CYAN);
			ConsoleUI.starMenuTop();
			ConsoleUI.starMenuEmpty();
			ConsoleUI.starMenuItem(1, "Customer Register");
			ConsoleUI.starMenuItem(2, "Customer Login");
			ConsoleUI.starMenuItem(3, "Admin Login");
			ConsoleUI.starMenuItem(4, "Register Admin");
			ConsoleUI.starMenuItem(5, "Exit System");
			ConsoleUI.starMenuEmpty();
			ConsoleUI.starMenuBottom();

			ConsoleUI.drawInputFieldPrompt("Enter Choice (1-5)");
			int choice = getValidInt(sc);
			System.out.print(ConsoleUI.RESET);

			switch (choice) {

			// ── 1. Customer Register ────────────────────────────────────────────
			case 1:
				try {
					ConsoleUI.showRegisterPattern();
					ConsoleUI.drawHeaderTitle("CUSTOMER REGISTRATION");

					ConsoleUI.drawDataRow("User ID", "(Automatically generated after registration)");

					ConsoleUI.drawInputFieldPrompt("Enter Full Name");
					String name = sc.nextLine();
					System.out.print(ConsoleUI.RESET);

					ConsoleUI.drawInputFieldPrompt("Enter Mobile (10 digits)");
					String mobile = getValidMobile(sc);
					System.out.print(ConsoleUI.RESET);

					ConsoleUI.drawInputFieldPrompt("Enter Password (8-13 chars, upper+lower+digit+special)");
					String password = getValidPassword(sc);
					System.out.print(ConsoleUI.RESET);

					ConsoleUI.drawInputFieldPrompt("Enter Address");
					String address = sc.nextLine();
					System.out.print(ConsoleUI.RESET);

					ConsoleUI.drawInputFieldPrompt("Subsidy Eligible? (true / false)");
					boolean subsidy = sc.nextBoolean();
					sc.nextLine();
					System.out.print(ConsoleUI.RESET);

					ConsoleUI.drawInputFieldPrompt("Enter Membership Type  (e.g. Premium / Regular)");
					String membership = sc.nextLine();
					System.out.print(ConsoleUI.RESET);

					int userId = registrationService.generateUniqueUserId();
					registrationService.registerCustomer(userId, name, mobile, password,
						"Customer", address, subsidy, membership);
					ConsoleUI.drawDataRow("Generated User ID", String.valueOf(userId));
				} catch (Exception e) {
					ConsoleUI.errorBox("Registration Failed: " + e.getMessage());
				}
				ConsoleUI.sleep(1500);
				break;

			case 2:
				try {
					ConsoleUI.showLoginPattern();
					ConsoleUI.drawHeaderTitle("CUSTOMER LOGIN");

					ConsoleUI.drawInputFieldPrompt("Enter Mobile");
					String loginMobile = getValidMobile(sc);
					System.out.print(ConsoleUI.RESET);

					ConsoleUI.drawInputFieldPrompt("Enter Password");
					String loginPassword = getValidPassword(sc);
					System.out.print(ConsoleUI.RESET);

					User user = loginService.login(loginMobile, loginPassword);

					if (user != null) {
						Customer customer = (Customer) user;

						// ── Customer menu loop ───────────────────────────────────
						while (true) {
							ConsoleUI.clearScreen();
					ConsoleUI.starSectionHeader("CUSTOMER PORTAL - Welcome, " + customer.getName(), ConsoleUI.GREEN);
							ConsoleUI.starMenuTop();
							ConsoleUI.starMenuEmpty();
							ConsoleUI.starMenuItem(1, "View Available Cylinders");
							ConsoleUI.starMenuItem(2, "Request Booking");
							ConsoleUI.starMenuItem(3, "View My Bookings");
							ConsoleUI.starMenuItem(4, "Pay for Confirmed Booking");
							ConsoleUI.starMenuItem(5, "Cancel a Booking");
							ConsoleUI.starMenuItem(6, "Logout");
							ConsoleUI.starMenuEmpty();
							ConsoleUI.starMenuBottom();

							ConsoleUI.drawInputFieldPrompt("Enter Choice (1-6)");
							int customerChoice = getValidInt(sc);
							System.out.print(ConsoleUI.RESET);

							switch (customerChoice) {

							case 1:
								ConsoleUI.clearScreen();
								ConsoleUI.drawHeaderTitle("AVAILABLE LPG CYLINDERS");
								cylinderService.displayAllCylinders();
								ConsoleUI.drawPanelBottom();
								ConsoleUI.sleep(500);
								System.out.println(ConsoleUI.YELLOW + ConsoleUI.BOLD + "\n   Press ENTER to continue..." + ConsoleUI.RESET);
								sc.nextLine();
								break;

							case 2:
								try {
									ConsoleUI.clearScreen();
									ConsoleUI.drawHeaderTitle("REQUEST A CYLINDER BOOKING");
									cylinderService.displayAllCylinders();
									ConsoleUI.drawPanelBottom();

									ConsoleUI.drawInputFieldPrompt("Enter Cylinder ID");
									int cylinderId = getValidInt(sc);
									System.out.print(ConsoleUI.RESET);

									ConsoleUI.drawInputFieldPrompt("Enter Quantity");
									int quantity = getValidInt(sc);
									System.out.print(ConsoleUI.RESET);

									Cylinder cylinder = cylinderService.searchCylinderById(cylinderId);
									Booking booking = bookingService.requestBooking(customer, cylinder, quantity);

									if (booking != null) {
										ConsoleUI.successBox("Your booking request has been placed and remains pending admin approval.");
									}
								} catch (Exception e) {
									ConsoleUI.errorBox("Booking Request Failed: " + e.getMessage());
								}
								ConsoleUI.sleep(1000);
								System.out.println(ConsoleUI.YELLOW + ConsoleUI.BOLD + "\n   Press ENTER to continue..." + ConsoleUI.RESET);
								sc.nextLine();
								break;

							case 4:
								try {
									ConsoleUI.clearScreen();
									ConsoleUI.drawHeaderTitle("PAY FOR CONFIRMED BOOKING");
									ConsoleUI.drawInputFieldPrompt("Enter Booking ID");
									int payBookingId = getValidInt(sc);
									System.out.print(ConsoleUI.RESET);

									Booking booking = bookingService.getBookingById(payBookingId);
									if (booking.getCustomer().getUserId() != customer.getUserId()) {
										throw new Exception("This booking does not belong to you.");
									}
									if (!"Confirmed".equalsIgnoreCase(booking.getBookingStatus())) {
										throw new Exception("Only confirmed bookings can be paid.");
									}

									ConsoleUI.starSectionHeader("SELECT PAYMENT METHOD", ConsoleUI.YELLOW);
									ConsoleUI.starMenuTop();
									ConsoleUI.starMenuEmpty();
									ConsoleUI.starMenuItem(1, "Card Payment  (Debit / Credit)");
									ConsoleUI.starMenuItem(2, "UPI Payment   (GPay / PhonePe / Paytm)");
									ConsoleUI.starMenuItem(3, "Cash On Delivery");
									ConsoleUI.starMenuEmpty();
									ConsoleUI.starMenuBottom();

									ConsoleUI.drawInputFieldPrompt("Enter Payment Choice (1-3)");
									int paymentChoice = getValidInt(sc);
									System.out.print(ConsoleUI.RESET);

									String cardNo = "";
									String cvv    = "";
									String upi    = "";

									if (paymentChoice == 1) {
										ConsoleUI.drawInputFieldPrompt("Enter 16-digit Card Number");
										cardNo = getValidCard(sc);
										System.out.print(ConsoleUI.RESET);
										ConsoleUI.drawInputFieldPrompt("Enter CVV (3 digits)");
										cvv = getValidCVV(sc);
										System.out.print(ConsoleUI.RESET);
									} else if (paymentChoice == 2) {
										ConsoleUI.drawInputFieldPrompt("Enter UPI ID  (e.g. name@upi)");
										upi = getValidUPI(sc);
										System.out.print(ConsoleUI.RESET);
									}

									ConsoleUI.paymentAnimation();
									bookingService.payConfirmedBooking(customer, payBookingId, paymentChoice, cardNo, cvv, upi);

									message.showMessage("Payment Successful! Amount Paid: \\u20B9" + booking.getTotalAmount());

									ConsoleUI.clearScreen();
									ConsoleUI.receiptHeader();
									DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
									DateTimeFormatter fmtT = DateTimeFormatter.ofPattern("HH:mm:ss");
									ConsoleUI.drawDataRow("Booking ID",    String.valueOf(booking.getBookingId()));
									ConsoleUI.drawDataRow("Customer Name", booking.getCustomer().getName());
									ConsoleUI.drawDataRow("Cylinder Type", booking.getCylinder().getCylinderType());
									ConsoleUI.drawDataRow("Brand",         booking.getCylinder().getBrandName());
									ConsoleUI.drawDataRow("Quantity",      String.valueOf(booking.getQuantity()));
					ConsoleUI.drawDataRow("Total Amount",  "Rs." + booking.getTotalAmount());
									ConsoleUI.drawDataRow("Booking Status",booking.getBookingStatus());
									ConsoleUI.drawPanelBottom();
								} catch (Exception e) {
									ConsoleUI.errorBox("Payment Failed: " + e.getMessage());
								}
								ConsoleUI.sleep(1000);
								System.out.println(ConsoleUI.YELLOW + ConsoleUI.BOLD + "\n   Press ENTER to continue..." + ConsoleUI.RESET);
								sc.nextLine();
								break;

							case 3:
								ConsoleUI.clearScreen();
								ConsoleUI.drawHeaderTitle("MY BOOKINGS");
								bookingService.displayCustomerBookings(customer);
								ConsoleUI.drawPanelBottom();
								System.out.println(ConsoleUI.YELLOW + ConsoleUI.BOLD + "\n   Press ENTER to continue..." + ConsoleUI.RESET);
								sc.nextLine();
								break;

							case 5:
								ConsoleUI.clearScreen();
								ConsoleUI.drawHeaderTitle("CANCEL A BOOKING");
								ConsoleUI.drawInputFieldPrompt("Enter Booking ID to Cancel");
								int bookingId = getValidInt(sc);
								System.out.print(ConsoleUI.RESET);
								bookingService.cancelBooking(customer, bookingId);
								ConsoleUI.sleep(1000);
								System.out.println(ConsoleUI.YELLOW + ConsoleUI.BOLD + "\n   Press ENTER to continue..." + ConsoleUI.RESET);
								sc.nextLine();
								break;

							case 6:
								ConsoleUI.successBox("Logged Out Successfully. Goodbye, " + customer.getName() + "!");
								ConsoleUI.sleep(1000);
								break;

							default:
						ConsoleUI.errorBox("Invalid Choice - Please select 1 to 6.");
							}

							if (customerChoice == 6) break;
						}
					}
				} catch (Exception e) {
					ConsoleUI.errorBox("Login Failed: " + e.getMessage());
					ConsoleUI.sleep(1500);
				}
				break;

			// ── 3. Admin Login ──────────────────────────────────────────────────
			case 3:
				try {
					ConsoleUI.showAdminLoginPattern();
					ConsoleUI.drawHeaderTitle("ADMIN LOGIN");

					ConsoleUI.drawInputFieldPrompt("Enter Admin Mobile");
					String adminMobile = getValidMobile(sc);
					System.out.print(ConsoleUI.RESET);

					ConsoleUI.drawInputFieldPrompt("Enter Admin Password");
					String adminPassword = getValidPassword(sc);
					System.out.print(ConsoleUI.RESET);

					User adminUser = loginService.loginAdmin(adminMobile, adminPassword);

					if (adminUser != null) {
						Admin admin = (Admin) adminUser;

						// ── Admin menu loop ──────────────────────────────────────
						while (true) {
							ConsoleUI.clearScreen();
					ConsoleUI.starSectionHeader("ADMIN CONTROL PANEL - " + admin.getName(), ConsoleUI.PURPLE);
							ConsoleUI.starMenuTop();
							ConsoleUI.starMenuEmpty();
							ConsoleUI.starMenuItem(1, "Add Cylinder Stock");
							ConsoleUI.starMenuItem(2, "View All Customers");
							ConsoleUI.starMenuItem(3, "View All Bookings");
								ConsoleUI.starMenuItem(4, "View Pending Bookings");
								ConsoleUI.starMenuItem(5, "Confirm Pending Booking");
								ConsoleUI.starMenuItem(6, "Generate Booking Report");
								ConsoleUI.starMenuItem(7, "Logout");
								ConsoleUI.starMenuEmpty();
								ConsoleUI.starMenuBottom();

								ConsoleUI.drawInputFieldPrompt("Enter Choice (1-7)");
							int adminChoice = getValidInt(sc);
							System.out.print(ConsoleUI.RESET);

							switch (adminChoice) {

							case 1:
								try {
									ConsoleUI.clearScreen();
									ConsoleUI.drawHeaderTitle("ADD CYLINDER STOCK");
									cylinderService.displayAllCylinders();
									ConsoleUI.drawPanelBottom();
									ConsoleUI.drawInputFieldPrompt("Enter Cylinder ID");
									int cylinderId = getValidInt(sc);
									System.out.print(ConsoleUI.RESET);
									ConsoleUI.drawInputFieldPrompt("Enter Stock Quantity to Add");
									int stock = getValidInt(sc);
									System.out.print(ConsoleUI.RESET);
									adminService.addCylinderStock(cylinderId, stock);
								} catch (Exception e) {
									ConsoleUI.errorBox("Failed to Add Stock: " + e.getMessage());
								}
								ConsoleUI.sleep(1000);
								System.out.println(ConsoleUI.YELLOW + ConsoleUI.BOLD + "\n   Press ENTER to continue..." + ConsoleUI.RESET);
								sc.nextLine();
								break;

							case 2:
								ConsoleUI.clearScreen();
								adminService.viewAllCustomers();
								System.out.println(ConsoleUI.YELLOW + ConsoleUI.BOLD + "\n   Press ENTER to continue..." + ConsoleUI.RESET);
								sc.nextLine();
								break;

							case 3:
								ConsoleUI.clearScreen();
								adminService.viewAllBookings();
								System.out.println(ConsoleUI.YELLOW + ConsoleUI.BOLD + "\n   Press ENTER to continue..." + ConsoleUI.RESET);
								sc.nextLine();
								break;

							case 4:
								ConsoleUI.clearScreen();
								adminService.viewPendingBookings();
								System.out.println(ConsoleUI.YELLOW + ConsoleUI.BOLD + "\n   Press ENTER to continue..." + ConsoleUI.RESET);
								sc.nextLine();
								break;

							case 5:
								try {
									ConsoleUI.clearScreen();
									ConsoleUI.drawHeaderTitle("CONFIRM PENDING BOOKING");
									ConsoleUI.drawInputFieldPrompt("Enter Pending Booking ID");
									int confirmBookingId = getValidInt(sc);
									System.out.print(ConsoleUI.RESET);
									adminService.confirmPendingBooking(confirmBookingId);
								} catch (Exception e) {
									ConsoleUI.errorBox("Booking confirmation failed: " + e.getMessage());
								}
								System.out.println(ConsoleUI.YELLOW + ConsoleUI.BOLD + "\n   Press ENTER to continue..." + ConsoleUI.RESET);
								sc.nextLine();
								break;

							case 6:
								ConsoleUI.clearScreen();
								try {
									adminService.generateBookingReport();
								} catch (Exception e) {
									ConsoleUI.errorBox("Report Generation Failed: " + e.getMessage());
								}
								System.out.println(ConsoleUI.YELLOW + ConsoleUI.BOLD + "\n   Press ENTER to continue..." + ConsoleUI.RESET);
								sc.nextLine();
								break;

							case 7:
								ConsoleUI.successBox("Admin Logged Out. Session Terminated.");
								ConsoleUI.sleep(1000);
								break;

							default:
						ConsoleUI.errorBox("Invalid Choice - Please select 1 to 7.");
							}

							if (adminChoice == 7) break;
						}
					}
				} catch (Exception e) {
					ConsoleUI.errorBox("Admin Login Failed: " + e.getMessage());
					ConsoleUI.sleep(1500);
				}
				break;

			// ── 4. Register Admin ───────────────────────────────────────────────
			case 4:
				try {
					ConsoleUI.showAdminRegisterPattern();
					ConsoleUI.drawHeaderTitle("ADMIN REGISTRATION");

					ConsoleUI.drawDataRow("Admin ID", "(Automatically generated after registration)");

					ConsoleUI.drawInputFieldPrompt("Enter Admin Name");
					String adminName = sc.nextLine();
					System.out.print(ConsoleUI.RESET);

					ConsoleUI.drawInputFieldPrompt("Enter Admin Mobile (10 digits)");
					String adminMob = getValidMobile(sc);
					System.out.print(ConsoleUI.RESET);

					ConsoleUI.drawInputFieldPrompt("Enter Admin Password");
					String adminPwd = getValidPassword(sc);
					System.out.print(ConsoleUI.RESET);

					int adminId = registrationService.generateUniqueUserId();
					registrationService.registerAdmin(adminId, adminName, adminMob, adminPwd);
					ConsoleUI.drawDataRow("Generated Admin ID", String.valueOf(adminId));

				} catch (Exception e) {
					ConsoleUI.errorBox("Admin Registration Failed: " + e.getMessage());
				}
				ConsoleUI.sleep(1500);
				break;

			// ── 5. Exit ─────────────────────────────────────────────────────────
			case 5:
				ConsoleUI.clearScreen();
				System.out.println();
				ConsoleUI.showExitPattern();
				System.out.println();
				sc.close();
				System.exit(0);
				break;
			default:
				ConsoleUI.errorBox("Invalid Choice - Please select 1 to 5.");
				ConsoleUI.sleep(1000);
			}
		}
	}
}

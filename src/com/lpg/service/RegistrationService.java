package com.lpg.service;

import java.util.*;
import com.lpg.model.user.*;
import com.lpg.util.ConsoleUI;
import com.lpg.util.Validator;
import com.lpg.exception.*;

public class RegistrationService {
	private static final Random RANDOM = new Random();
	private static ArrayList<Customer> customers = new ArrayList<>();
	private static ArrayList<Admin>    admins    = new ArrayList<>();

	public synchronized int generateUniqueUserId() {
		int userId;
		while (true) {
			userId = 1000 + RANDOM.nextInt(9000);
			if (!isUserIdExists(userId)) {
				return userId;
			}
		}
	}

	private synchronized boolean isUserIdExists(int userId) {
		for (Customer customer : customers) {
			if (customer.getUserId() == userId) return true;
		}
		for (Admin admin : admins) {
			if (admin.getUserId() == userId) return true;
		}
		return false;
	}

	public void registerCustomer(int userId, String name, String mobile, String password,
			String role, String address, boolean subsidyEligible, String membershipType)
			throws InvalidMobileException, InvalidPasswordException, InvalidInputException {
		Validator.validateMobile(mobile);
		Validator.validatePassword(password);
		if (isMobileExists(mobile))
			throw new InvalidMobileException("Mobile number already registered. Please use a different number.");
		if (name == null || name.trim().isEmpty())
			throw new InvalidInputException("Name cannot be empty.");
		if (address == null || address.trim().isEmpty())
			throw new InvalidInputException("Address cannot be empty.");

		customers.add(new Customer(userId, name, mobile, password, role, address, subsidyEligible, membershipType));
		ConsoleUI.successBox("Registration Successful! Welcome to the LPG Booking System, " + name + ".");
	}

	public void registerAdmin(int userId, String name, String mobile, String password)
			throws InvalidMobileException, InvalidPasswordException, InvalidInputException {
		Validator.validateMobile(mobile);
		Validator.validatePassword(password);
		if (isMobileExists(mobile))
			throw new InvalidMobileException("Mobile number already registered.");
		if (name == null || name.trim().isEmpty())
			throw new InvalidInputException("Name cannot be empty.");

		admins.add(new Admin(userId, name, mobile, password, "Admin"));
		ConsoleUI.successBox("Admin Registration Successful! Welcome, " + name + ".");
	}

	public boolean isMobileExists(String mobile) {
		for (int i = 0; i < customers.size(); i++)
			if (customers.get(i).getMobile().equals(mobile)) return true;
		for (int i = 0; i < admins.size(); i++)
			if (admins.get(i).getMobile().equals(mobile)) return true;
		return false;
	}

	public static ArrayList<Customer> getCustomers() { return customers; }
	public static ArrayList<Admin>    getAdmins()    { return admins; }
}

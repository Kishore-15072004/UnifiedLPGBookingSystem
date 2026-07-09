package com.lpg.service;

import java.util.ArrayList;
import com.lpg.exception.*;
import com.lpg.model.user.*;
import com.lpg.util.ConsoleUI;
import com.lpg.util.Validator;

public class LoginService {
	private static User currentUser;

	public static User getCurrentUser() { return currentUser; }

	public User login(String mobile, String password)
			throws InvalidMobileException, InvalidPasswordException, InvalidLoginException {
		Validator.validateMobile(mobile);
		Validator.validatePassword(password);

		ArrayList<Customer> customers = RegistrationService.getCustomers();
		for (int i = 0; i < customers.size(); i++) {
			Customer customer = customers.get(i);
			if (customer.getMobile().equals(mobile) && customer.getPassword().equals(password)) {
				currentUser = customer;
				ConsoleUI.successBox("Login Successful! Welcome back, " + customer.getName() + ".");
				return customer;
			}
		}
		throw new InvalidLoginException("Invalid mobile or password. Please check your credentials.");
	}

	public User loginAdmin(String mobile, String password)
			throws InvalidMobileException, InvalidPasswordException, InvalidLoginException {
		Validator.validateMobile(mobile);
		Validator.validatePassword(password);

		ArrayList<Admin> admins = RegistrationService.getAdmins();
		for (int i = 0; i < admins.size(); i++) {
			Admin admin = admins.get(i);
			if (admin.getMobile().equals(mobile) && admin.getPassword().equals(password)) {
				currentUser = admin;
				ConsoleUI.successBox("Admin Login Successful! Welcome back, " + admin.getName() + ".");
				return admin;
			}
		}
		throw new InvalidLoginException("Invalid admin credentials. Please try again.");
	}

	public void logout() {
		currentUser = null;
		ConsoleUI.successBox("Logout Successful. Session Closed.");
	}
}

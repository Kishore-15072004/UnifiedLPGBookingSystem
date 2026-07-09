package com.lpg.model.user;

public class Admin extends User {

	public Admin(int userId, String name, String mobile, String password, String role) {
		super(userId, name, mobile, password, role);
	}
	public void displayAdminDetails() {
		System.out.println("\n=======Admin=======");
		super.displayProfile();
	}
}

package com.lpg.model.user;

import com.lpg.util.ConsoleUI;

public abstract class User {
	protected int    userId;
	protected String name;
	protected String mobile;
	protected String password;
	protected String role;

	public User(int userId, String name, String mobile, String password, String role) {
		this.userId   = userId;
		this.name     = name;
		this.mobile   = mobile;
		this.password = password;
		this.role     = role;
	}

	public int    getUserId()  { return userId; }
	public String getName()    { return name; }
	public void   setName(String name)       { this.name = name; }
	public String getMobile()  { return mobile; }
	public void   setMobile(String mobile)   { this.mobile = mobile; }
	public String getPassword(){ return password; }
	public void   setPassword(String password){ this.password = password; }
	public String getRole()    { return role; }

	public void displayProfile() {
		ConsoleUI.drawDataRow("User ID",   String.valueOf(userId));
		ConsoleUI.drawDataRow("Name",      name);
		ConsoleUI.drawDataRow("Mobile",    mobile);
		ConsoleUI.drawDataRow("Role",      role);
	}
}

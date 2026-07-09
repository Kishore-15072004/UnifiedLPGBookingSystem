package com.lpg.model.user;

import com.lpg.util.ConsoleUI;

public class Customer extends User {
	private String  address;
	private boolean subsidyEligible;
	private String  membershipType;

	public Customer(int userId, String name, String mobile, String password,
			String role, String address, boolean subsidyEligible, String membershipType) {
		super(userId, name, mobile, password, role);
		this.address         = address;
		this.subsidyEligible = subsidyEligible;
		this.membershipType  = membershipType;
	}

	public String  getAddress()          { return address; }
	public void    setAddress(String a)  { this.address = a; }
	public boolean isSubsidyEligible()   { return subsidyEligible; }
	public void    setSubsidyEligible(boolean s) { this.subsidyEligible = s; }
	public String  getMembershipType()   { return membershipType; }
	public void    setMembershipType(String m)   { this.membershipType = m; }

	public void displayCustomerDetails() {
		super.displayProfile();
		ConsoleUI.drawDataRow("Address",    address);
		ConsoleUI.drawDataRow("Subsidy",    subsidyEligible ? "Eligible" : "Not Eligible");
		ConsoleUI.drawDataRow("Membership", membershipType);
		ConsoleUI.drawPanelDivider();
	}
}

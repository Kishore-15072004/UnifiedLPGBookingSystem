package com.lpg.model.cylinder;

import com.lpg.util.ConsoleUI;

public class DomesticCylinder extends Cylinder {
	private boolean subsidyAvailable;

	public boolean isSubsidyAvailable() { return subsidyAvailable; }

	public DomesticCylinder(int cylinderId, String cylinderType, double weight,
			double price, int stock, String brandName, boolean subsidyAvailable) {
		super(cylinderId, cylinderType, weight, price, stock, brandName);
		this.subsidyAvailable = subsidyAvailable;
	}

	@Override
	public void displayCylinderDetails() {
		ConsoleUI.drawDataRow("ID  | Type",     cylinderId + "  |  " + cylinderType + "  [DOMESTIC]");
		ConsoleUI.drawDataRow("Brand",          brandName);
		ConsoleUI.drawDataRow("Weight (kg)",    String.valueOf(weight));
		ConsoleUI.drawDataRow("Price (Rs.)",      String.valueOf(price));
		ConsoleUI.drawDataRow("Stock",          String.valueOf(stock));
		ConsoleUI.drawDataRow("Subsidy",        subsidyAvailable ? "YES - Eligible" : "NO");
		ConsoleUI.drawPanelDivider();
	}
}

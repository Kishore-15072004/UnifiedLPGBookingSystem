package com.lpg.model.cylinder;

import com.lpg.util.ConsoleUI;

public class CommercialCylinder extends Cylinder {
	private String businessType;

	public CommercialCylinder(int cylinderId, String cylinderType, double weight,
			double price, int stock, String brandName, String businessType) {
		super(cylinderId, cylinderType, weight, price, stock, brandName);
		this.businessType = businessType;
	}

	public String getBusinessType() { return businessType; }

	@Override
	public void displayCylinderDetails() {
		ConsoleUI.drawDataRow("ID  | Type",     cylinderId + "  |  " + cylinderType + "  [COMMERCIAL]");
		ConsoleUI.drawDataRow("Brand",          brandName);
		ConsoleUI.drawDataRow("Weight (kg)",    String.valueOf(weight));
		ConsoleUI.drawDataRow("Price (Rs.)",      String.valueOf(price));
		ConsoleUI.drawDataRow("Stock",          String.valueOf(stock));
		ConsoleUI.drawDataRow("Business Type",  businessType);
		ConsoleUI.drawPanelDivider();
	}
}

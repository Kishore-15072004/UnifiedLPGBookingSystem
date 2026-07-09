package com.lpg.model.cylinder;

public abstract class Cylinder {
	protected int cylinderId;
	protected String cylinderType;
	protected double weight;
	protected double price;
	protected int stock;
	protected String brandName;
	
	public Cylinder(int cylinderId, String cylinderType, double weight, double price, int stock, String brandName) {
		this.cylinderId = cylinderId;
		this.cylinderType = cylinderType;
		this.weight = weight;
		this.price = price;
		this.stock = stock;
		this.brandName = brandName;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	public double getPrice() {
		return price;
	}
	public int getStock() {
		return stock;
	}
	
	public int getCylinderId() {
		return cylinderId;
	}

	public String getCylinderType() {
		return cylinderType;
	}

	public double getWeight() {
		return weight;
	}

	public String getBrandName() {
		return brandName;
	}

	public abstract void displayCylinderDetails();

}

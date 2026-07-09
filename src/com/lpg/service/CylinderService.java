package com.lpg.service;

import java.util.ArrayList;
import com.lpg.exception.CylinderNotFoundException;
import com.lpg.exception.InsufficientStockException;
import com.lpg.model.cylinder.Cylinder;
import com.lpg.util.ConsoleUI;

public class CylinderService {
	private static ArrayList<Cylinder> cylinders = new ArrayList<>();

	public void addCylinder(Cylinder cylinder) {
		if (cylinder == null) throw new IllegalArgumentException("Cylinder cannot be null.");
		cylinders.add(cylinder);
	}

	public void displayAllCylinders() {
		if (cylinders.isEmpty()) {
			ConsoleUI.errorBox("No cylinders available at the moment.");
			return;
		}
		for (int i = 0; i < cylinders.size(); i++) {
			cylinders.get(i).displayCylinderDetails();
		}
	}

	public Cylinder searchCylinderById(int cylinderId) throws CylinderNotFoundException {
		for (int i = 0; i < cylinders.size(); i++) {
			if (cylinders.get(i).getCylinderId() == cylinderId) return cylinders.get(i);
		}
		throw new CylinderNotFoundException("Cylinder ID " + cylinderId + " not found. Please select a valid ID.");
	}

	public boolean isStockAvailable(int cylinderId, int quantity) {
		try {
			return searchCylinderById(cylinderId).getStock() >= quantity;
		} catch (CylinderNotFoundException e) { return false; }
	}

	public void updateStock(int cylinderId, int quantity) throws CylinderNotFoundException, InsufficientStockException {
		Cylinder cylinder = searchCylinderById(cylinderId);
		if (cylinder.getStock() < quantity)
			throw new InsufficientStockException("Insufficient stock. Available: " + cylinder.getStock());
		cylinder.setStock(cylinder.getStock() - quantity);
		ConsoleUI.successBox("Stock Updated. Remaining: " + cylinder.getStock());
	}

	public static ArrayList<Cylinder> getCylinders() { return cylinders; }
}

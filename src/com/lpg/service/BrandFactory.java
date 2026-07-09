package com.lpg.service;


import com.lpg.model.brand.Bharat;
import com.lpg.model.brand.HP;
import com.lpg.model.brand.Indane;
import com.lpg.model.brand.LPGBrand;

public class BrandFactory {

	public static LPGBrand getBrand(String brandName) {
		if(brandName.equalsIgnoreCase("Indane")) {
			return new Indane();
		}
		else if(brandName.equalsIgnoreCase("HP")) {
			return new HP();
		}
		else if(brandName.equalsIgnoreCase("Bharat")) {
			return new Bharat();
		}
		return null;
	}
}
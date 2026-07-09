package com.lpg.model.brand;

public abstract class LPGBrand {
	protected  String brandName;
	
	public LPGBrand(String brandName)
	{
		 this.brandName = brandName;
	}
	public String getbrandName() {
		return brandName;
	}
	public abstract double getDomesticPrice();
	
	public abstract double getCommercialPrice();
}

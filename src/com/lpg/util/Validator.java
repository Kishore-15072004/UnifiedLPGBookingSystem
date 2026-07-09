package com.lpg.util;
import com.lpg.exception.*;
public class Validator {
	//Mobile Validation
	public static void validateMobile(String mobile) {
		if(mobile.length()==10) {
			for(int i=0;i<mobile.length();i++) {
				if(mobile.charAt(i)<'0' || mobile.charAt(i)>'9') {
					throw new InvalidMobileException("Mobile number should have only digits");
				}
			}
		}
		else {
			throw new InvalidMobileException("Mobile number should have exactly 10 digits");
		}
	}
	
	//Password Validation
	public static void validatePassword(String password) {
		if(password.length()>=8 && password.length()<=13) {
			boolean upper = false;
			boolean lower = false;
			boolean digit = false;
			boolean special = false;
			for(int i=0;i<password.length();i++) {
				char ch = password.charAt(i);
				if(ch == ' ') {
					throw new InvalidPasswordException("Password  should not contain spaces.");
				}
				if(ch>='A' && ch<='Z') {
					upper = true;
					continue;
				}
				if(ch>='a' && ch<='z') {
					lower = true;
					continue;
				}
				if(ch>='0' && ch<='9') {
					digit = true;
					continue;
				}
				if(!(ch>='A' && ch<='Z') && !(ch>='a' && ch<='z') && !(ch>='0' && ch<='9')) {
							special = true;
				}
			}
			if(upper == false) {
				throw new InvalidPasswordException("Password should contain atleast 1 uppercase letter");
			}
			if(lower == false) {
				throw new InvalidPasswordException("Password should contain atleast 1 lowercase letter");
			}
			if(digit == false) {
				throw new InvalidPasswordException("Password should contain atleast 1 digit");
			}
			if(special == false) {
				throw new InvalidPasswordException("Password should contain atleast 1 special character");
			}
		}
		else {
			throw new InvalidPasswordException("Password length should be minimum 8 characters and maximum 13 characters.");
		}
	}
	
	//Card Validation
	public static void validateCardNumber(String cardNumber)
	{
		if(cardNumber.length()==16) {
			for(int i=0;i<cardNumber.length();i++) {
				if(cardNumber.charAt(i)<'0' || cardNumber.charAt(i)>'9') {
					throw new InvalidCardException("Card Number should contain only digits.");
				}
			}
		}
		else {
			throw new InvalidCardException("Card Number should be 16 digits.");
		}
	}
	
	//CVV Validation
	public static void validateCVV(String cvv) {
		if(cvv.length()==3) {
			for(int i=0;i<cvv.length();i++) {
				if(cvv.charAt(i)<'0' || cvv.charAt(i)>'9') {
					throw new InvalidCVVException("CVV should only contain digits");
				}
			}
		}
		else {
			throw new InvalidCVVException("CVV must be only 3 digits");
		}
	}
	
	//UPI Validation
	public static void validateUPI(String upi) {
		int count = 0;
		for(int i=0;i<upi.length();i++) {
			char ch = upi.charAt(i);
			if(ch == ' ') {
				throw new InvalidUPIException("UPI ID should not contain spaces");
			}
			if(ch == '@') {
				count++;
			}
		}
		if(count != 1) {
			throw new InvalidUPIException("UPI ID should contain exactly one @");
		}
		if(upi.charAt(0) == '@' ||
		   upi.charAt(upi.length()-1) == '@') {
			throw new InvalidUPIException("Invalid UPI ID format");
		}
	}
}

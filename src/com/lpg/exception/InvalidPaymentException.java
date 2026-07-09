package com.lpg.exception;

public class InvalidPaymentException extends RuntimeException {
	public InvalidPaymentException(String msg) {
		super(msg);
	}
}
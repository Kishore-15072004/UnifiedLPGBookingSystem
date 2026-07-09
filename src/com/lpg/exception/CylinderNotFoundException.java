package com.lpg.exception;

public class CylinderNotFoundException extends RuntimeException {
    public CylinderNotFoundException(String msg) {
        super(msg);
    }
}
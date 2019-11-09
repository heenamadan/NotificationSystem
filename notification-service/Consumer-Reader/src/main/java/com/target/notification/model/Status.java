package com.target.notification.model;
/**
 * Enum for status success and failure
 * 
 */
public enum Status {

	success,failure;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}

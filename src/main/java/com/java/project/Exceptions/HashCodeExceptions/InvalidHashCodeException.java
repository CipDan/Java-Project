package com.java.project.Exceptions.HashCodeExceptions;

/**
 * Custom exception for when the given hashcode does not correspond with the user's hashcode.
 */
public class InvalidHashCodeException extends Exception {

    public InvalidHashCodeException(String userEmail, String hashCode) {
        super(String.format("Hash code %s invalid for email %s!", hashCode, userEmail));
    }
}

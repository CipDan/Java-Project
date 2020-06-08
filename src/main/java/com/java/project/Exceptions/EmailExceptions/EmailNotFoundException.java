package com.java.project.Exceptions.EmailExceptions;

/**
 * Custom exception for when the given email cannot be found.
 */
public class EmailNotFoundException extends Exception {

    public EmailNotFoundException(String email) {
        super(String.format("The email %s is not present!", email));
    }
}

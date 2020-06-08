package com.java.project.Exceptions.EmailExceptions;

/**
 * Custom exception for when the given email is null.
 */
public class EmailNullException extends Exception {

    public EmailNullException() {
        super("Email must not be null!");
    }
}

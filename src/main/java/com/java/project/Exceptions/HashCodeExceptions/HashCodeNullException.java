package com.java.project.Exceptions.HashCodeExceptions;

/**
 * Custom exception for when the given hashcode is null.
 */
public class HashCodeNullException extends Exception {

    public HashCodeNullException() {
        super("Hashcode must not be null!");
    }
}

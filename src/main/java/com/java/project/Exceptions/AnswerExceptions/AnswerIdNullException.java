package com.java.project.Exceptions.AnswerExceptions;

/**
 * Custom exception for when the given answer id is null.
 */
public class AnswerIdNullException extends Exception {

    public AnswerIdNullException() {
        super("Answer id must not be null!");
    }
}

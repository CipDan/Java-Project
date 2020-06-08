package com.java.project.Exceptions.QuestionExceptions;

/**
 * Custom exception for when the given question's id is null.
 */
public class QuestionIdNullException extends Exception {

    public QuestionIdNullException() {
        super("Question id must not be null!");
    }
}

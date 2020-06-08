package com.java.project.Exceptions.QuestionExceptions;

/**
 * Custom exception for when there is no question with the given id.
 */
public class QuestionNotFoundException extends Exception {

    public QuestionNotFoundException(Integer questionId) {
        super(String.format("There is no question with id %d!", questionId));
    }
}

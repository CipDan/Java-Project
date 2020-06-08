package com.java.project.Exceptions.AnswerExceptions;

/**
 * Custom exception for when there is no answer with the given id.
 */
public class AnswerNotFoundException extends Exception {

    public AnswerNotFoundException(Integer answerId) {
        super(String.format("There is no answer with id %d!", answerId));
    }
}

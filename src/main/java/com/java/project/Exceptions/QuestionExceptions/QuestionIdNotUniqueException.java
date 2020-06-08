package com.java.project.Exceptions.QuestionExceptions;

/**
 * Custom exception for when the question that is to be added does not have an unique id.
 */
public class QuestionIdNotUniqueException extends Exception {

    public QuestionIdNotUniqueException(Integer questionId) {
        super(String.format("There is already an answer with the id %d!", questionId));
    }
}

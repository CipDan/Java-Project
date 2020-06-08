package com.java.project.Exceptions.AnswerExceptions;

/**
 * Custom exception for when the answer that is to be added does not have an unique id.
 */
public class AnswerIdNotUniqueException extends Exception {

    public AnswerIdNotUniqueException(Integer answerId) {
        super(String.format("There is already an answer with the id %d!", answerId));
    }
}

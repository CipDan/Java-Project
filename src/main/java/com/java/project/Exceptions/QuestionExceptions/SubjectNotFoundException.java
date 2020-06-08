package com.java.project.Exceptions.QuestionExceptions;

/**
 * Custom exception for when the given subject is not found.
 */
public class SubjectNotFoundException extends Exception {

    public SubjectNotFoundException(String subjectCode) {
        super(String.format("Subject with code %s not found!", subjectCode));
    }
}

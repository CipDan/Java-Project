package com.java.project.Services;

import com.java.project.Exceptions.AnswerExceptions.AnswerIdNotUniqueException;
import com.java.project.Exceptions.AnswerExceptions.AnswerIdNullException;
import com.java.project.Exceptions.AnswerExceptions.AnswerNotFoundException;
import com.java.project.Exceptions.EmailExceptions.EmailNotFoundException;
import com.java.project.Exceptions.EmailExceptions.EmailNullException;
import com.java.project.Exceptions.HashCodeExceptions.HashCodeNullException;
import com.java.project.Exceptions.HashCodeExceptions.InvalidHashCodeException;
import com.java.project.Exceptions.QuestionExceptions.QuestionIdNotUniqueException;
import com.java.project.Exceptions.QuestionExceptions.QuestionIdNullException;
import com.java.project.Exceptions.QuestionExceptions.QuestionNotFoundException;
import com.java.project.Exceptions.QuestionExceptions.SubjectNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * A Rest Controller for error handling.
 */
@RestControllerAdvice
public class ExceptionAdvisor {

    @ExceptionHandler(value = QuestionNotFoundException.class)
    public String questionNotFoundException(QuestionNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(value = AnswerNotFoundException.class)
    public String answerNotFoundException(AnswerNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(value = SubjectNotFoundException.class)
    public String subjectNotFoundException(SubjectNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(value = AnswerIdNotUniqueException.class)
    public String answerIdMustBeUniqueException(AnswerIdNotUniqueException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(value = AnswerIdNullException.class)
    public String answerIdMustNotBeNullException(AnswerIdNullException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(value = QuestionIdNullException.class)
    public String questionIdMustNotBeNullException(QuestionIdNullException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(value = EmailNotFoundException.class)
    public String emailNotFoundException(EmailNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(value = InvalidHashCodeException.class)
    public String invalidHashCodeException(InvalidHashCodeException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(value = QuestionIdNotUniqueException.class)
    public String questionIdNotUniqueException(QuestionIdNotUniqueException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(value = EmailNullException.class)
    public String emailNullException(EmailNullException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(value = HashCodeNullException.class)
    public String hashCodeNullException(HashCodeNullException ex) {
        return ex.getMessage();
    }
}

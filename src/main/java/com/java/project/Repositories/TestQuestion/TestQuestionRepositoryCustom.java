package com.java.project.Repositories.TestQuestion;

/**
 * Custom Repository for implementing SQL stored functions.
 */
public interface TestQuestionRepositoryCustom {

    String returnNextQuestionOrScoring(String userEmail, String answers);
}

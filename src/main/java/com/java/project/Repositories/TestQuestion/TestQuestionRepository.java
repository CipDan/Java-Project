package com.java.project.Repositories.TestQuestion;

import com.java.project.Entities.TestQuestion;
import com.java.project.Services.CompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * JPA Repository for database management.
 */
@Repository
public interface TestQuestionRepository extends JpaRepository<TestQuestion, CompositeKey>, TestQuestionRepositoryCustom {

    Optional<List<TestQuestion>> findAllQuestionsByUserEmail(String userEmail);

    Integer getOrderNumberOfGivenQuestion(String email, Integer q_id);

    @Modifying
    @Transactional
    @Query("DELETE FROM TestQuestion testQuestion WHERE testQuestion.userEmail = ?1")
    void deleteUserTestQuestions(String email);
}

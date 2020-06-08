package com.java.project.Repositories;

import com.java.project.Entities.Answer;
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
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    Optional<List<Answer>> findAllAnswersByQuestionId(Integer qId);

    Integer checkIfAnswerIdAlreadyPresent(Integer id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Answer answer WHERE answer.questionId = ?1")
    void deleteAnswersByQuestionId(Integer qId);
}

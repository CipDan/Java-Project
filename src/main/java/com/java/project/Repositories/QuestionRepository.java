package com.java.project.Repositories;

import com.java.project.Entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * JPA Repository for database management.
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    Optional<List<Question>> getQuestionsBySubjectCode(String SubjectCode);

    Integer checkIfQuestionIdPresent(Integer id);
}

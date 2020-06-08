package com.java.project.Repositories;

import com.java.project.Entities.EmailHashing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA Repository for database management.
 */
@Repository
public interface EmailHashingRepository extends JpaRepository<EmailHashing, String> {

    String getHashCodeforGivenEmail(String email);

    Integer checkIfEmailExists(String email);
}

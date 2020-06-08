package com.java.project.Controllers;

import com.java.project.Entities.TestQuestion;
import com.java.project.Exceptions.EmailExceptions.EmailNotFoundException;
import com.java.project.Exceptions.EmailExceptions.EmailNullException;
import com.java.project.Exceptions.HashCodeExceptions.HashCodeNullException;
import com.java.project.Exceptions.HashCodeExceptions.InvalidHashCodeException;
import com.java.project.Repositories.EmailHashingRepository;
import com.java.project.Repositories.TestQuestion.TestQuestionRepository;
import com.java.project.Services.RequestData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Rest controller for handling requests regarding a user's test.
 */
@Api(value = "Test Management API", description = "Handle test-question-related tasks")
@RestController
@RequestMapping("/api/v1")
public class TestQuestionController {

    @Autowired
    private TestQuestionRepository testQuestionRepository;

    @Autowired
    private EmailHashingRepository emailHashingRepository;

    //Get user test
    @ApiOperation(value = "View an user's test", response = List.class)
    @GetMapping("/get_all_user_questions/{userEmail}")
    public List<TestQuestion> getAllQuestionsForGivenEmail(@ApiParam(value = "user email for which we try to see the test",
            required = true, example = "anon.canon@dom.com")
                                                           @PathVariable(value = "userEmail") String userEmail)
            throws EmailNotFoundException {
        return testQuestionRepository.findAllQuestionsByUserEmail(userEmail)
                .orElseThrow(() -> new EmailNotFoundException(userEmail));
    }

    //Obtain next question or score for an identified user
    @ApiOperation(value = "Obtain the next question or the final score for an identified user", response = String.class)
    @PostMapping("/obtain_next_question_or_score")
    public String getNextQuestionOrScore(@ApiParam(value = "the request's data", required = true,
            example = "{\"email\":\"anon@dom.com\",\"hashCode\":\"dddb5b87da1f025e3379d3e76ded06f3bd70a0edec1f949622f248415f4303c1\"}")
                                         @Valid @RequestBody RequestData data)
            throws EmailNullException, HashCodeNullException,
            InvalidHashCodeException, EmailNotFoundException {
        String email = data.getEmail();
        if (email == null) {
            throw new EmailNullException();
        } else {
            if (emailHashingRepository.checkIfEmailExists(email) == 0) {
                throw new EmailNotFoundException(email);
            } else {
                String hashCode = data.getHashCode();
                if (hashCode == null) {
                    throw new HashCodeNullException();
                } else {
                    if (!emailHashingRepository.getHashCodeforGivenEmail(email).equals(hashCode)) {
                        throw new InvalidHashCodeException(email, hashCode);
                    } else {
                        return testQuestionRepository.returnNextQuestionOrScoring(email, data.getAnswers());
                    }
                }
            }
        }
    }

    //Delete user test
    @ApiOperation(value = "delete an user's test", response = ResponseEntity.class)
    @DeleteMapping("/delete_user_test/{email}")
    public ResponseEntity<?> deleteUserTest(@ApiParam(value = "user email for which we try to delete a test",
            required = true, example = "anon.canon@dom.com")
                                            @PathVariable(value = "email") String email) {
        testQuestionRepository.deleteUserTestQuestions(email);

        return ResponseEntity.ok().build();
    }
}

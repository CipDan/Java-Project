package com.java.project.Controllers;

import com.java.project.Entities.Answer;
import com.java.project.Exceptions.AnswerExceptions.AnswerIdNotUniqueException;
import com.java.project.Exceptions.AnswerExceptions.AnswerIdNullException;
import com.java.project.Exceptions.AnswerExceptions.AnswerNotFoundException;
import com.java.project.Exceptions.QuestionExceptions.QuestionIdNullException;
import com.java.project.Exceptions.QuestionExceptions.QuestionNotFoundException;
import com.java.project.Repositories.AnswerRepository;
import com.java.project.Repositories.QuestionRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Rest controller for handling requests regarding an answer.
 */
@Api(value = "Answer Management API", description = "Handle answer-related tasks")
@RestController
@RequestMapping("/api/v1")
public class AnswerController {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    //Get all answers
    @ApiOperation(value = "View all stored answers", response = List.class)
    @GetMapping("/get_all_answers")
    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    //Get all answers of a question
    @ApiOperation(value = "View all answers of a specific question", response = List.class)
    @GetMapping("/get_question_answers/{qId}")
    public List<Answer> getQuestionAnswers(@ApiParam(value = "question id for which we try to get all answers",
            required = true, example = "20")
                                           @PathVariable(value = "qId") Integer qId)
            throws QuestionNotFoundException {
        return answerRepository.findAllAnswersByQuestionId(qId).orElseThrow(() -> new QuestionNotFoundException(qId));
    }

    //Get a specific answer
    @ApiOperation(value = "View a specific answer", response = Answer.class)
    @GetMapping("/get_answer/{id}")
    public Answer getAnswerById(@ApiParam(value = "id for which we try to get the corresponding answer",
            required = true, example = "100")
                                @PathVariable(value = "id") Integer id)
            throws AnswerNotFoundException {
        return answerRepository.findById(id).orElseThrow(() -> new AnswerNotFoundException(id));
    }

    //Create new answer for a specific question
    @ApiOperation(value = "Add a new answer for a specific question", response = Answer.class)
    @PostMapping("/add_answer_to_question/{qId}")
    public Answer addAnswerToQuestion(@ApiParam(value = "question id for which we want to add the answer",
            required = true, example = "20")
                                      @PathVariable(value = "qId") Integer qId,
                                      @ApiParam(value = "the answer to be added",
                                              required = true, example = "{\"id\":300,\"questionId\":\"20\",\"answerBody\":\"I enjoy\",\"isCorrect\":\"0\"}")
                                      @Valid @RequestBody Answer answer)
            throws QuestionNotFoundException, AnswerIdNotUniqueException,
            AnswerIdNullException {
        if (questionRepository.checkIfQuestionIdPresent(qId) == 0) {
            throw new QuestionNotFoundException(qId);
        } else {
            if (answer.getId() == null) {
                throw new AnswerIdNullException();
            } else {
                if (answerRepository.checkIfAnswerIdAlreadyPresent(answer.getId()) != 0)
                    throw new AnswerIdNotUniqueException(answer.getId());
                else {
                    answer.setQuestionId(qId);
                    return answerRepository.save(answer);
                }
            }
        }
    }

    //Update an existing answer
    @ApiOperation(value = "Update a specific answer", response = Answer.class)
    @PutMapping("/update_answer/{id}")
    public Answer updateAnswer(@ApiParam(value = "id of the question which we want to update",
            required = true, example = "100")
                               @PathVariable(value = "id") Integer id,
                               @ApiParam(value = "the new answer information",
                                       required = true, example = "{\"questionId\":\"20\",\"answerBody\":\"I enjoy\",\"isCorrect\":\"0\"}")
                               @Valid @RequestBody Answer answerDetails)
            throws AnswerNotFoundException, QuestionNotFoundException,
            QuestionIdNullException {
        Answer answer = answerRepository.findById(id).orElseThrow(() -> new AnswerNotFoundException(id));
        if (answerDetails.getQuestionId() == null) {
            throw new QuestionIdNullException();
        } else {
            if (questionRepository.checkIfQuestionIdPresent(answerDetails.getQuestionId()) == 0) {
                throw new QuestionNotFoundException(answerDetails.getQuestionId());
            } else {
                answer.setQuestionId(answerDetails.getQuestionId());
                answer.setAnswerBody(answerDetails.getAnswerBody());
                answer.setIsCorrect(answerDetails.getIsCorrect());

                return answerRepository.save(answer);
            }
        }
    }

    //Delete all answers of a question
    @ApiOperation(value = "Delete all answers of a question", response = ResponseEntity.class)
    @DeleteMapping("/delete_question_answers/{qId}")
    public ResponseEntity<?> deleteQuestionAnswers(@ApiParam(value = "id of the question for which we want to delete its answers",
            required = true, example = "20")
                                                   @PathVariable(value = "qId") Integer qId) {
        answerRepository.deleteAnswersByQuestionId(qId);
        return ResponseEntity.ok().build();
    }

    //Delete an answer
    @ApiOperation(value = "Delete a specific answer", response = ResponseEntity.class)
    @DeleteMapping("/delete_answer/{id}")
    public ResponseEntity<?> deleteAnswer(@ApiParam(value = "id of the question which we want to delete",
            required = true, example = "300")
                                          @PathVariable(value = "id") Integer id)
            throws AnswerNotFoundException {
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new AnswerNotFoundException(id));

        answerRepository.delete(answer);

        return ResponseEntity.ok().build();
    }
}

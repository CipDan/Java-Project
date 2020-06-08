package com.java.project.Controllers;

import com.java.project.Entities.Question;
import com.java.project.Exceptions.QuestionExceptions.QuestionIdNotUniqueException;
import com.java.project.Exceptions.QuestionExceptions.QuestionIdNullException;
import com.java.project.Exceptions.QuestionExceptions.QuestionNotFoundException;
import com.java.project.Exceptions.QuestionExceptions.SubjectNotFoundException;
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
 * Rest controller for handling requests regarding a question.
 */
@Api(value = "Question Management API", description = "Handle question-related tasks")
@RestController
@RequestMapping("/api/v1")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    /**
     * Removes all answers of a deleted question.
     *
     * @param id the id of the deleted question.
     */
    private void removeAnswersOfDeletedQuestion(Integer id) {
        answerRepository.deleteAnswersByQuestionId(id);
    }

    //Get all questions
    @ApiOperation(value = "View all stored questions", response = List.class)
    @GetMapping("/get_questions")
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    //Get all questions with a given subject code
    @ApiOperation(value = "View all questions with a specific subject code", response = List.class)
    @GetMapping("/get_questions_by_subject_code/{subjectCode}")
    public List<Question> getQuestionFromGivenSubject(@ApiParam(value = "a subject code for which we try to see all questions",
            required = true, example = "D1")
                                                      @PathVariable(value = "subjectCode") String subjectCode)
            throws SubjectNotFoundException {
        return questionRepository.getQuestionsBySubjectCode(subjectCode).orElseThrow(() -> new SubjectNotFoundException(subjectCode));
    }

    //Get a question with a given id
    @ApiOperation(value = "View a question with a specific id", response = Question.class)
    @GetMapping("/get_question/{id}")
    public Question getQuestionById(@ApiParam(value = "id for which we try to see the corresponding question",
            required = true, example = "1")
                                    @PathVariable(value = "id") Integer id)
            throws QuestionNotFoundException {
        return questionRepository.findById(id).orElseThrow(() -> new QuestionNotFoundException(id));
    }

    //Create a new question
    @ApiOperation(value = "Add a new question entry", response = Question.class)
    @PostMapping("/add_question")
    public Question createQuestion(@ApiParam(value = "the question we try to add",
            required = true, example = "{\"id\":40,\"subject\":\"D20\",\"questionBody\":\"I enjoy\"}")
                                   @Valid @RequestBody Question question)
            throws QuestionIdNotUniqueException, QuestionIdNullException {
        if (question.getId() == null) {
            throw new QuestionIdNullException();
        } else {
            if (questionRepository.checkIfQuestionIdPresent(question.getId()) != 0) {
                throw new QuestionIdNotUniqueException(question.getId());
            } else {
                return questionRepository.save(question);
            }
        }
    }

    //Update an existing question
    @ApiOperation(value = "Update a specific question", response = Question.class)
    @PutMapping("/update_question/{id}")
    public Question updateQuestion(@ApiParam(value = "id of the question we want to update",
            required = true, example = "40")
                                   @PathVariable(value = "id") Integer id,
                                   @ApiParam(value = "the new question information",
                                           required = true, example = "{\"subject\":\"D20\",\"questionBody\":\"I enjoy\"}")
                                   @Valid @RequestBody Question questionDetails)
            throws QuestionNotFoundException {
        Question question = questionRepository.findById(id).orElseThrow(() -> new QuestionNotFoundException(id));

        question.setSubject(questionDetails.getSubject());
        question.setQuestionBody(questionDetails.getQuestionBody());

        return questionRepository.save(question);
    }

    //Delete a question
    @ApiOperation(value = "Delete a specific question", response = ResponseEntity.class)
    @DeleteMapping("/delete_question/{id}")
    public ResponseEntity<?> deleteQuestion(@ApiParam(value = "id of the question we want to delete",
            required = true, example = "40")
                                            @PathVariable(value = "id") Integer id)
            throws QuestionNotFoundException {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException(id));

        questionRepository.delete(question);
        removeAnswersOfDeletedQuestion(id);

        return ResponseEntity.ok().build();
    }
}

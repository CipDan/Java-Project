package com.java.project.Entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * An entity representation of an answer.
 */
@ApiModel(description = "Details about an answer")
@Entity
@Table(name = "raspunsuri")
@NamedQuery(name = "Answer.findAllAnswersByQuestionId", query = "SELECT answer FROM Answer answer WHERE answer.questionId = ?1")
@NamedQuery(name = "Answer.checkIfAnswerIdAlreadyPresent", query = "SELECT COUNT(answer) FROM Answer answer WHERE answer.id = ?1")
public class Answer {

    @ApiModelProperty(notes = "the answer's id")
    @Id
    @Column(name = "id")
    private Integer id;

    @ApiModelProperty(notes = "the answer's question's id")
    @NotNull
    @Column(name = "q_id")
    private Integer questionId;

    @ApiModelProperty(notes = "the answer's text")
    @NotEmpty
    @Column(name = "text_raspuns")
    private String answerBody;

    @ApiModelProperty(notes = "the answer's correctness")
    @Column(name = "corect")
    private String isCorrect;

    /**
     * Creates a new instance.
     */
    public Answer() {
        super();
    }

    /**
     * Creates a new instance.
     *
     * @param id         the answer's id.
     * @param questionId the answer's question's id.
     * @param answerBody the answer's text.
     * @param isCorrect  the answer's correctness.
     */
    public Answer(Integer id, Integer questionId, String answerBody, String isCorrect) {
        super();
        this.id = id;
        this.questionId = questionId;
        this.answerBody = answerBody;
        this.isCorrect = isCorrect;
    }

    /**
     * Returns the answer's id.
     *
     * @return an <code>Integer</code>.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the answer's id.
     *
     * @param id the id to be set.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the answer's question's id.
     *
     * @return an <code>Integer</code>.
     */
    public Integer getQuestionId() {
        return questionId;
    }

    /**
     * Sets the answer's question's id.
     *
     * @param questionId the question id to be set.
     */
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    /**
     * Returns the answer's text.
     *
     * @return a <code>String</code>.
     */
    public String getAnswerBody() {
        return answerBody;
    }

    /**
     * Sets the answer's text.
     *
     * @param answerBody the text to be set.
     */
    public void setAnswerBody(String answerBody) {
        this.answerBody = answerBody;
    }

    /**
     * Returns the answer's correctness.
     *
     * @return a <code>String</code>.
     */
    public String getIsCorrect() {
        return isCorrect;
    }

    /**
     * Sets the answer's correctness.
     *
     * @param isCorrect the correctness to be set.
     */
    public void setIsCorrect(String isCorrect) {
        this.isCorrect = isCorrect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return id.equals(answer.id) &&
                questionId.equals(answer.questionId) &&
                answerBody.equals(answer.answerBody) &&
                isCorrect.equals(answer.isCorrect);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionId, answerBody, isCorrect);
    }
}

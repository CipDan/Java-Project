package com.java.project.Entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

/**
 * An entity representation of a question.
 */
@ApiModel(description = "Details about a question")
@Entity
@Table(name = "intrebari")
@NamedQuery(name = "Question.getQuestionsBySubjectCode", query = "SELECT question from Question question WHERE question.subject = ?1")
@NamedQuery(name = "Question.checkIfQuestionIdPresent", query = "SELECT COUNT(question) FROM Question question WHERE question.id = ?1")
public class Question {

    @ApiModelProperty(notes = "the question's id")
    @Id
    @Column(name = "id")
    private Integer id;

    @ApiModelProperty(notes = "the question's subject")
    @Column(name = "domeniu")
    private String subject;

    @ApiModelProperty(notes = "the question's text")
    @NotEmpty
    @Column(name = "text_intrebare")
    private String questionBody;

    /**
     * Creates a new instance.
     */
    public Question() {
        super();
    }

    /**
     * Creates a new instance.
     *
     * @param id           the question's id.
     * @param subject      the question's subject.
     * @param questionBody the question's text.
     */
    public Question(Integer id, String subject, String questionBody) {
        super();
        this.id = id;
        this.subject = subject;
        this.questionBody = questionBody;
    }

    /**
     * Returns the question's id.
     *
     * @return an <code>Integer</code>.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the question's id.
     *
     * @param id the id to be set.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the question's subject.
     *
     * @return a <code>String</code>.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the question's subject.
     *
     * @param subject the subject to be set.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Returns the question's text.
     *
     * @return a <code>String</code>.
     */
    public String getQuestionBody() {
        return questionBody;
    }

    /**
     * Sets the question's text.
     *
     * @param questionBody the text to be set.
     */
    public void setQuestionBody(String questionBody) {
        this.questionBody = questionBody;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id.equals(question.id) &&
                subject.equals(question.subject) &&
                questionBody.equals(question.questionBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subject, questionBody);
    }
}

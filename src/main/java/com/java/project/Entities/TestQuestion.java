package com.java.project.Entities;


import com.java.project.Services.CompositeKey;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * An entity representation of a test question.
 */
@ApiModel(description = "Details about a test question")
@Entity
@Table(name = "teste_utilizatori")
@IdClass(CompositeKey.class)
@NamedQuery(name = "TestQuestion.findAllQuestionsByUserEmail", query = "SELECT testQuestion FROM TestQuestion testQuestion WHERE testQuestion.userEmail = ?1")
@NamedQuery(name = "TestQuestion.getOrderNumberOfGivenQuestion", query = "SELECT testQuestion.questionOrderNumber FROM TestQuestion testQuestion WHERE testQuestion.userEmail = ?1 and testQuestion.questionId = ?2")
public class TestQuestion {

    @ApiModelProperty(notes = "the user's email")
    @Id
    @Column(name = "email_utilizator")
    private String userEmail;

    @ApiModelProperty(notes = "the question's order number")
    @Id
    @Column(name = "nr_ord_q")
    private Integer questionOrderNumber;

    @ApiModelProperty(notes = "the question's id")
    @NotNull
    @Column(name = "id_intrebare")
    private Integer questionId;

    @ApiModelProperty(notes = "the first answer's id")
    @NotNull
    @Column(name = "id_raspuns_1")
    private Integer answer1Id;

    @ApiModelProperty(notes = "the second answer's id")
    @NotNull
    @Column(name = "id_raspuns_2")
    private Integer answer2Id;

    @ApiModelProperty(notes = "the third answer's id")
    @NotNull
    @Column(name = "id_raspuns_3")
    private Integer answer3Id;

    @ApiModelProperty(notes = "the fourth answer's id")
    @NotNull
    @Column(name = "id_raspuns_4")
    private Integer answer4Id;

    @ApiModelProperty(notes = "the fifth answer's id")
    @NotNull
    @Column(name = "id_raspuns_5")
    private Integer answer5Id;

    @ApiModelProperty(notes = "the sixth answer's id")
    @NotNull
    @Column(name = "id_raspuns_6")
    private Integer answer6Id;

    @ApiModelProperty(notes = "the ids of the correct answers")
    @NotEmpty
    @Column(name = "id_raspunsuri_corecte")
    private String correctAnswersIds;

    @ApiModelProperty(notes = "the ids of the given answers")
    @Column(name = "id_raspunsuri_date")
    private String givenAnswersIds = "";

    /**
     * Creates a new instance.
     */
    public TestQuestion() {
        super();
    }

    /**
     * Creates a new instance.
     *
     * @param userEmail           the question's user email.
     * @param questionOrderNumber the question's order number.
     * @param questionId          the question's id.
     * @param answer1Id           the question's first answer's id.
     * @param answer2Id           the question's second answer's id.
     * @param answer3Id           the question's third answer's id.
     * @param answer4Id           the question's fourth answer's id.
     * @param answer5Id           the question's fifth answer's id.
     * @param answer6Id           the question's sixth answer's id.
     * @param correctAnswersIds   the question's correct answers' ids.
     * @param givenAnswersIds     the question's given answers's ids.
     */
    public TestQuestion(String userEmail, Integer questionOrderNumber,
                        Integer questionId, Integer answer1Id, Integer answer2Id,
                        Integer answer3Id, Integer answer4Id, Integer answer5Id,
                        Integer answer6Id, String correctAnswersIds, String givenAnswersIds) {
        super();
        this.userEmail = userEmail;
        this.questionOrderNumber = questionOrderNumber;
        this.questionId = questionId;
        this.answer1Id = answer1Id;
        this.answer2Id = answer2Id;
        this.answer3Id = answer3Id;
        this.answer4Id = answer4Id;
        this.answer5Id = answer5Id;
        this.answer6Id = answer6Id;
        this.correctAnswersIds = correctAnswersIds;
        this.givenAnswersIds = givenAnswersIds;
    }

    /**
     * Returns the test question's user email.
     *
     * @return a <code>String</code>.
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Sets the test question's user email.
     *
     * @param userEmail the user email to be set.
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * Returns the test question's order number.
     *
     * @return an <code>Integer</code>.
     */
    public Integer getQuestionOrderNumber() {
        return questionOrderNumber;
    }

    /**
     * Sets the test question's order number.
     *
     * @param questionOrderNumber the order number to be set.
     */
    public void setQuestionOrderNumber(Integer questionOrderNumber) {
        this.questionOrderNumber = questionOrderNumber;
    }

    /**
     * Returns the test question's id.
     *
     * @return an <code>Integer</code>.
     */
    public Integer getQuestionId() {
        return questionId;
    }

    /**
     * Sets the test question's id.
     *
     * @param questionId the question id to be set.
     */
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    /**
     * Returns the test question's first answer's id.
     *
     * @return an <code>Integer</code>.
     */
    public Integer getAnswer1Id() {
        return answer1Id;
    }

    /**
     * Sets the test question's first answer's id.
     *
     * @param answer1Id the answer id to be set.
     */
    public void setAnswer1Id(Integer answer1Id) {
        this.answer1Id = answer1Id;
    }

    /**
     * Returns the test question's second answer's id.
     *
     * @return an <code>Integer</code>.
     */
    public Integer getAnswer2Id() {
        return answer2Id;
    }

    /**
     * Sets the test question's second answer's id.
     *
     * @param answer2Id the answer id to be set.
     */
    public void setAnswer2Id(Integer answer2Id) {
        this.answer2Id = answer2Id;
    }

    /**
     * Returns the test question's third answer's id.
     *
     * @return an <code>Integer</code>.
     */
    public Integer getAnswer3Id() {
        return answer3Id;
    }

    /**
     * Sets the test question's third answer's id.
     *
     * @param answer3Id the answer id to be set.
     */
    public void setAnswer3Id(Integer answer3Id) {
        this.answer3Id = answer3Id;
    }

    /**
     * Returns the test question's fourth answer's id.
     *
     * @return an <code>Integer</code>.
     */
    public Integer getAnswer4Id() {
        return answer4Id;
    }

    /**
     * Sets the test question's fourth answer's id.
     *
     * @param answer4Id the answer id to be set.
     */
    public void setAnswer4Id(Integer answer4Id) {
        this.answer4Id = answer4Id;
    }

    /**
     * Returns the test question's fifth answer's id.
     *
     * @return an <code>Integer</code>.
     */
    public Integer getAnswer5Id() {
        return answer5Id;
    }

    /**
     * Sets the test question's fifth answer's id.
     *
     * @param answer5Id the answer id to be set.
     */
    public void setAnswer5Id(Integer answer5Id) {
        this.answer5Id = answer5Id;
    }

    /**
     * Returns the test question's sixth answer's id.
     *
     * @return an <code>Integer</code>.
     */
    public Integer getAnswer6Id() {
        return answer6Id;
    }

    /**
     * Sets the test question's sixth answer's id.
     *
     * @param answer6Id the answer id to be set.
     */
    public void setAnswer6Id(Integer answer6Id) {
        this.answer6Id = answer6Id;
    }

    /**
     * Returns the test question's correct answers' ids.
     *
     * @return a <code>String</code>.
     */
    public String getCorrectAnswersIds() {
        return correctAnswersIds;
    }

    /**
     * Sets the test question's correct answers' ids.
     *
     * @param correctAnswersIds the answer ids to be set.
     */
    public void setCorrectAnswersIds(String correctAnswersIds) {
        this.correctAnswersIds = correctAnswersIds;
    }

    /**
     * Returns the test question's given answers' ids.
     *
     * @return a <code>String</code>.
     */
    public String getGivenAnswersIds() {
        return givenAnswersIds;
    }

    /**
     * Sets the test question's given answers' ids.
     *
     * @param givenAnswersIds the answer ids to be set.
     */
    public void setGivenAnswersIds(String givenAnswersIds) {
        this.givenAnswersIds = givenAnswersIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestQuestion that = (TestQuestion) o;
        return userEmail.equals(that.userEmail) &&
                questionOrderNumber.equals(that.questionOrderNumber) &&
                questionId.equals(that.questionId) &&
                answer1Id.equals(that.answer1Id) &&
                answer2Id.equals(that.answer2Id) &&
                answer3Id.equals(that.answer3Id) &&
                answer4Id.equals(that.answer4Id) &&
                answer5Id.equals(that.answer5Id) &&
                answer6Id.equals(that.answer6Id) &&
                correctAnswersIds.equals(that.correctAnswersIds) &&
                Objects.equals(givenAnswersIds, that.givenAnswersIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userEmail, questionOrderNumber, questionId,
                answer1Id, answer2Id, answer3Id, answer4Id, answer5Id,
                answer6Id, correctAnswersIds, givenAnswersIds);
    }
}

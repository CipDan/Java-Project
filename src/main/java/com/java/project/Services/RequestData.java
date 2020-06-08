package com.java.project.Services;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Entity representation of <code>TestQuestionController</code>'s POST method's request body.
 */
@ApiModel(description = "Entity representation of test-question-controller's POST method's request body")
public class RequestData {

    @ApiModelProperty(notes = "the user's email")
    private String email;

    @ApiModelProperty(notes = "the user's corresponding hashcode")
    private String hashCode;

    @ApiModelProperty(notes = "the user's answers")
    private String answers;

    /**
     * Creates a new instance.
     */
    public RequestData() {
        super();
    }

    /**
     * Returns the sent email.
     *
     * @return the email sent in the request.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the sent hashcode.
     *
     * @return the hashcode sent in the request.
     */
    public String getHashCode() {
        return hashCode;
    }

    /**
     * Returns the sent answers.
     *
     * @return the answers sent in the request.
     */
    public String getAnswers() {
        return answers;
    }

    /**
     * Sets the request's email.
     *
     * @param email the email to be set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the request's hashcode.
     *
     * @param hashCode the hashcode to be set.
     */
    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    /**
     * Sets the request's answers.
     *
     * @param answers the answers to be set.
     */
    public void setAnswers(String answers) {
        this.answers = answers;
    }
}

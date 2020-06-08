package com.java.project.Services;

import java.io.Serializable;
import java.util.Objects;

/**
 * Composite key implementation for the <code>TestQuestion</code> entity.
 */
public class CompositeKey implements Serializable {

    private String userEmail;
    private Integer questionOrderNumber;

    /**
     * Creates a new instance.
     */
    public CompositeKey() {
        super();
    }

    /**
     * Creates a new instance.
     *
     * @param userEmail           the composite key's first member.
     * @param questionOrderNumber the composite key's second member.
     */
    public CompositeKey(String userEmail, Integer questionOrderNumber) {
        super();
        this.userEmail = userEmail;
        this.questionOrderNumber = questionOrderNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeKey that = (CompositeKey) o;
        return userEmail.equals(that.userEmail) &&
                questionOrderNumber.equals(that.questionOrderNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userEmail, questionOrderNumber);
    }
}

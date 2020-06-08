package com.java.project.Entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

/**
 * An entity representation of an email hashing.
 */
@ApiModel(description = "Details about an email hashing")
@Entity
@Table(name = "email_hashings")
@NamedQuery(name = "EmailHashing.getHashCodeforGivenEmail", query = "SELECT emailHashing.hashCode FROM EmailHashing emailHashing WHERE emailHashing.email = ?1")
@NamedQuery(name = "EmailHashing.checkIfEmailExists", query = "SELECT COUNT(emailHashing) FROM EmailHashing emailHashing WHERE emailHashing.email = ?1")
public class EmailHashing {

    @ApiModelProperty(notes = "the entry's email")
    @Id
    private String email;

    @ApiModelProperty(notes = "the entry's hashcode")
    @NotEmpty
    private String hashCode;

    /**
     * Creates a new instance.
     */
    public EmailHashing() {
        super();
    }

    /**
     * Creates a new instance.
     *
     * @param email    the entry's email.
     * @param hashCode the entry's hashcode.
     */
    public EmailHashing(String email, String hashCode) {
        super();
        this.email = email;
        this.hashCode = hashCode;
    }

    /**
     * Returns the email.
     *
     * @return a <code>String</code>.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the email to be set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the hashcode.
     *
     * @return a <code>String</code>.
     */
    public String getHashCode() {
        return hashCode;
    }

    /**
     * Sets the hashcode.
     *
     * @param hashCode the hashcode to be set.
     */
    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailHashing that = (EmailHashing) o;
        return email.equals(that.email) &&
                hashCode.equals(that.hashCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, hashCode);
    }
}

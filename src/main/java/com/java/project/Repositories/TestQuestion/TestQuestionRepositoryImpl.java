package com.java.project.Repositories.TestQuestion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

/**
 * Implementation of a custom repository handling SQL stored functions.
 */
@Repository
public class TestQuestionRepositoryImpl implements TestQuestionRepositoryCustom {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Implementation of a SQL stored function.
     *
     * @param userEmail first parameter of the function.
     * @param answers   second parameter of the function.
     * @return a JSON <code>String</code> representing the function's result.
     */
    @Override
    public String returnNextQuestionOrScoring(String userEmail, String answers) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("URMATOAREA_INTREBARE");
        SqlParameterSource paramMap = new MapSqlParameterSource()
                .addValue("p_email", userEmail).addValue("p_raspuns", answers);
        return jdbcCall.executeFunction(String.class, paramMap);
    }
}

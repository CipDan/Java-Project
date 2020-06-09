package com.java.project;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)//This annotation was required to run it successfully
@SpringBootTest
@AutoConfigureMockMvc
public class TestQuestionControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    void testGetAllQuestionsForGivenEmail1() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/get_all_user_questions/{userEmail}", "anon.canon@dom.com")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(
                        content().string(equalTo("The email anon.canon@dom.com is not present!"))
                );
    }

    @Test
    void testGetAllQuestionsForGivenEmail2() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/get_all_user_questions/{userEmail}", "ion@info.uaic.ro")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetNextQuestionOrScore1() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/obtain_next_question_or_score")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"ion@info.uaic.ro\",\"hashCode\":\"4527b4afcfc5a9f88a2d135da2b8dc7cd2feb2609828a70730db1714cfbbee2e\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetNextQuestionOrScore2() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/obtain_next_question_or_score")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"hashCode\":\"4527b4afcfc5a9f88a2d135da2b8dc7cd2feb2609828a70730db1714cfbbee2e\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(
                        content().string(equalTo("Email must not be null!"))
                );
    }

    @Test
    void testGetNextQuestionOrScore3() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/obtain_next_question_or_score")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"anon@canon.com\",\"hashCode\":\"4527b4afcfc5a9f88a2d135da2b8dc7cd2feb2609828a70730db1714cfbbee2e\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(
                        content().string(equalTo("The email anon@canon.com is not present!"))
                );
    }

    @Test
    void testGetNextQuestionOrScore4() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/obtain_next_question_or_score")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"sava.ioan@gmail.com\",\"hashCode\":\"4527b4afcfc5a9f88a2d135da2b8dc7cd2feb2609828a70730db1714cfbbee2e\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(
                        content().string(equalTo("Hash code 4527b4afcfc5a9f88a2d135da2b8dc7cd2feb2609828a70730db1714cfbbee2e invalid for email sava.ioan@gmail.com!"))
                );
    }

    @Test
    void testGetNextQuestionOrScore5() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/obtain_next_question_or_score")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"ion@info.uaic.ro\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(
                        content().string(equalTo("Hashcode must not be null!"))
                );
    }

    @Test
    void testDeleteUserTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/delete_user_test/{email}", "cip.danis@gmail.com")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

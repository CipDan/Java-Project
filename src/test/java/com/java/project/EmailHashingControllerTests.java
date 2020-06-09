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
public class EmailHashingControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testGetAllHashings() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/get_hashings")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetHashCodeForEmail1() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/obtain_hashcode_for_email")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"email\":\"ciprian.danis@info.uaic.ro\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetHashCodeForEmail2() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/obtain_hashcode_for_email")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"email\":\"ion@info.uaic.ro\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(
                        content().string(equalTo("4527b4afcfc5a9f88a2d135da2b8dc7cd2feb2609828a70730db1714cfbbee2e"))
                );
    }

    @Test
    public void testDeleteHashCode() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/delete_hashcode/{email}", "ciprian.danis@info.uaic.ro")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

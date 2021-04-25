package com.example.demo;

import com.example.demo.config.DatabaseConfig;
import com.example.demo.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {EmployeeRepository.class, DatabaseConfig.class})
@SpringBootTest
@AutoConfigureMockMvc
class EmployeeIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private EmployeeRepository repository;

    @Test
    void registrationWorksThroughAllLayers() throws Exception {

        ResultActions mvcResult = mockMvc.perform(get("/employee")
                .contentType("application/json"))
                .andExpect(status().isOk());
        String responseBody = mvcResult.toString();
        Assert.assertEquals(responseBody, "Risos");
//        mockMvc.perform(get("/emplok", 42L)
//                .contentType("application/json")
//                .param("sendWelcomeMail", "true")
//                .content(objectMapper.writeValueAsString(user)))
//                .andExpect(status().isOk());
    }

}
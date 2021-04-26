package com.example.demo;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class EmployeeIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private EmployeeRepository repository;

    @Test
    void getEmployeeShouldReturnListWithAllEmployees() throws Exception {

        ResultActions mvcResult = mockMvc.perform(get("/employee")
                .contentType("application/json"))
                .andExpect(status().isOk());
        MockHttpServletResponse response = mvcResult.andReturn().getResponse();
        String contentAsString = response.getContentAsString();
        List<Employee> list = Arrays.asList(objectMapper.readValue(contentAsString, Employee[].class));
        Assert.assertEquals(8, list.size());
    }

    @Test
    void patchEmployeeShouldUpdateEmployeeFields() throws Exception {
        Employee employee = repository.findById(1L).get();
        Double salaryBeforeUpdate = employee.getSalary();
        ResultActions mvcResult = mockMvc.perform(patch("/employee").content("{\"id\": "+employee.getId()+", \"salary\": "+(salaryBeforeUpdate+10000)+"}")
                .contentType("application/json"))
                .andExpect(status().isOk());
        MockHttpServletResponse response = mvcResult.andReturn().getResponse();
        String contentAsString = response.getContentAsString();
        Employee updatedEmployee = objectMapper.readValue(contentAsString, Employee.class);
        Assert.assertEquals(updatedEmployee.getSalary(), Double.valueOf(salaryBeforeUpdate+10000));
    }

    @Test
    void shouldDeleteEmployee() throws Exception {
        Employee e = registerEmployee();
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/employee/"+e.getId())
                .contentType("application/json"))
                .andExpect(status().isOk());
        Optional<Employee> deletedEmployee = repository.findById(e.getId());
        Assert.assertFalse(deletedEmployee.isPresent());
    }

    @Test
    void shouldFindEmployeeById() throws Exception {
        Employee e = registerEmployee();
        ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/employee/"+e.getId())
                .contentType("application/json"))
                .andExpect(status().isOk());
        MockHttpServletResponse response = mvcResult.andReturn().getResponse();
        String contentAsString = response.getContentAsString();
        Employee employee = objectMapper.readValue(contentAsString, Employee.class);
        Assert.assertNotNull(employee);
        Assert.assertEquals(e.getSalary(), employee.getSalary());
    }

    private Employee registerEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setSalary(10000.0);
        employee.setAddress("Random Address");
        employee.setName("Employee");
        employee.setSurname("Johnson");
        employee.setCompanyId(1L);
        employee.setEmail("test@test.com");
        String jsonString = objectMapper.writeValueAsString(employee);
        ResultActions mvcResult = mockMvc.perform(post("/employee").content(jsonString)
                .contentType("application/json"));
        MockHttpServletResponse response = mvcResult.andReturn().getResponse();
        String contentAsString = response.getContentAsString();
        return objectMapper.readValue(contentAsString, Employee.class);
    }

}
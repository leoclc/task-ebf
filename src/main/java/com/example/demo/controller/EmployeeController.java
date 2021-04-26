package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeRegisterRequest;
import com.example.demo.model.EmployeeUpdateRequest;
import com.example.demo.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService service;
    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(method = GET, path = "/employee")
    public List<Employee> listAll() {
        return service.findAll();
    }

    @RequestMapping(method = GET, path = "/employee/{id}")
    public Optional<Employee> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @RequestMapping(method = POST, path = "/employee")
    public Employee registerEmployee(@Valid @RequestBody EmployeeRegisterRequest registerRequest, HttpServletResponse response) {
        Employee e = modelMapper.map(registerRequest, Employee.class);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return service.save(e);
    }

    @RequestMapping(method = DELETE, path = "/employee/{id}")
    public void deleteEmployee(@PathVariable Long id , HttpServletResponse response) {
        service.delete(id);
    }


    @RequestMapping(method = PATCH, path = "/employee")
    public Employee updateEmployee(@Valid @RequestBody EmployeeUpdateRequest updateRequest) {
        Employee e = modelMapper.map(updateRequest, Employee.class);
        return service.save(e);
    }


}

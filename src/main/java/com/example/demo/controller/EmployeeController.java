package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeRegisterRequest;
import com.example.demo.model.EmployeeUpdateRequest;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @RequestMapping(method = GET, path = "/employee")
    public List<Employee> listAll() {
        return service.listAll();
    }

    @RequestMapping(method = POST, path = "/employee")
    public void registerEmployee(@Valid @RequestBody EmployeeRegisterRequest employee, HttpServletResponse response) {
        System.out.println("Employee: "+employee.getName());
        service.register(employee);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @RequestMapping(method = DELETE, path = "/employee/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        service.delete(id);
    }


    @RequestMapping(method = PATCH, path = "/employee/{id}")
    public void updateEmployee(@PathVariable Long id, @RequestBody EmployeeUpdateRequest updateRequest) {
        System.out.println("EMPLOYEE ID: "+id);
        System.out.println("EMPLOYEE BODY: "+updateRequest.getSalary());
        service.update(id ,updateRequest);
    }


}

package com.example.demo.service;

import com.example.demo.model.Company;
import com.example.demo.model.CompanyDetailedResponse;
import com.example.demo.model.Employee;
import com.example.demo.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository repository;
    @Autowired
    private EmployeeService employeeService;

    public List<Company> listAll() {
        return repository.findAll();
    }

    public CompanyDetailedResponse findById(Long id) {
        Optional<Company> c = repository.findById(id);
        if(c.isPresent() == false) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Company with id "+id+" not found"
            );
        }
        List<Employee> employees = employeeService.findByCompanyId(id);
        return new CompanyDetailedResponse(c.get().getName(), c.get().getId(), employees);
    }
}

package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeRegisterRequest;
import com.example.demo.model.EmployeeUpdateRequest;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public List<Employee> listAll() {
        return repository.listAll();
    }

    public void register(EmployeeRegisterRequest employee) {
        repository.register(employee);
    }

    public void delete(Long id) {
        repository.delete(id);
    }

    public List<Employee> findByCompanyId(Integer id) {
        return repository.findByCompanyId(id);
    }

    public void update(Long employeeId, EmployeeUpdateRequest updateRequest) {
        repository.update(employeeId, updateRequest);
    }
}

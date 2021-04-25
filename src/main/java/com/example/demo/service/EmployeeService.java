package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.util.BeansUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;
    private BeansUtil<Employee> util = new BeansUtil<Employee>();

    public List<Employee> findAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Employee> findByCompanyId(Long id) {
        return repository.findByCompanyId(id);
    }

    public void save(Employee e) {
        Optional<Employee> toPersist = Optional.empty();
        if(e.getId() != null) {
            toPersist = repository.findById(e.getId());
        }
        if(toPersist.isPresent()) {
            toPersist = Optional.of(util.copyNonNullProperties(toPersist.get(), e));
        } else {
            toPersist = Optional.of(e);
        }
        repository.save(toPersist.get());
    }

    public Optional<Employee> findById(Long id) {
        return repository.findById(id);
    }
}

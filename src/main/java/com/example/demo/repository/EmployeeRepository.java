package com.example.demo.repository;

import com.example.demo.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findAll();

    Employee save(Employee employee);

    void deleteById(Long id);

    List<Employee> findByCompanyId(Long id);

}

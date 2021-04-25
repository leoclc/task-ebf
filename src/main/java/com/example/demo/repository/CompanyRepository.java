package com.example.demo.repository;

import com.example.demo.model.Company;
import com.example.demo.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends CrudRepository<Company, Long> {

    List<Company> findAll();

    Optional<Company> findById(Long id);
}

package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.List;

public class CompanyDetailedResponse {

    private Long id;
    private String name;
    private List<Employee> employees;

    public CompanyDetailedResponse(String name, Long id, List<Employee> employees) {
        this.id = id;
        this.name = name;
        this.employees = employees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @JsonGetter("averageSalary")
    public Double getAverageSalary() {
        return this.employees.stream()
                .mapToDouble(Employee::getSalary).average().orElse(Double.NaN);
    }
}

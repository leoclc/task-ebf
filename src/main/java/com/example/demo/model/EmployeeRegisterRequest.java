package com.example.demo.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EmployeeRegisterRequest {

    @NotNull(message = "companyId is mandatory")
    private Long companyId;
    @NotBlank(message = "name is mandatory")
    private String name;
    @NotBlank(message = "surname is mandatory")
    private String surname;
    @NotBlank(message = "email is mandatory")
    private String email;
    @NotBlank(message = "address is mandatory")
    private String address;
    @NotNull(message = "salary is mandatory")
    private Double salary;

    public EmployeeRegisterRequest() {
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}

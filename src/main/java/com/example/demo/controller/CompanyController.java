package com.example.demo.controller;

import com.example.demo.model.Company;
import com.example.demo.model.CompanyDetailedResponse;
import com.example.demo.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService service;

    @RequestMapping(method = GET, path = "/company")
    public List<Company> listAll() {
        return service.listAll();
    }


    @RequestMapping(method = GET, path = "/company/{id}")
    public CompanyDetailedResponse findCompany(@PathVariable Long id) {
        return service.findById(id);
    }


}

package com.example.demo.repository;

import com.example.demo.model.Company;
import com.example.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyRepository {

    @Autowired
    private JdbcTemplate jtm;

    public List<Company> listAll() {
        String sql = "SELECT * from TBL_COMPANY";
        return jtm.query(sql, new BeanPropertyRowMapper<>(Company.class));
    }

    public Optional<Company> findById(Integer id) {
        String sql = "SELECT * from TBL_COMPANY where id = ?";
        return jtm.query(sql, new BeanPropertyRowMapper<>(Company.class), id).stream().findFirst();
    }
}

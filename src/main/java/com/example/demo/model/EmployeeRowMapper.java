package com.example.demo.model;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee e = new Employee();
        e.setId(rs.getLong("id"));
        e.setCompanyId(rs.getInt("companyId"));
        e.setName(rs.getString("name"));
        e.setSurname(rs.getString("surname"));
        e.setEmail(rs.getString("email"));
        e.setAddress(rs.getString("address"));
        e.setSalary(rs.getDouble("salary"));
        return e;
    }

}
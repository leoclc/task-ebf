package com.example.demo.repository;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeRegisterRequest;
import com.example.demo.model.EmployeeRowMapper;
import com.example.demo.model.EmployeeUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@Service
public class EmployeeRepository {

    @Autowired
    private JdbcTemplate jtm;
    @Autowired
    private NamedParameterJdbcTemplate namedParamJdbc;

    public List<Employee> listAll() {
        String sql = "SELECT * from TBL_EMPLOYEE";
        return jtm.query(sql, new BeanPropertyRowMapper<>(Employee.class));
    }

    public void register(EmployeeRegisterRequest employee) {
        jtm.update(
                "INSERT INTO TBL_EMPLOYEE (companyId, name, surname, email, address, salary) VALUES (?, ?, ?, ? ,? ,?)",
                employee.getCompanyId(), employee.getName(), employee.getSurname(), employee.getEmail(), employee.getAddress(), employee.getSalary());
    }

    public void delete(Long id) {
        jtm.update(
                "DELETE FROM TBL_EMPLOYEE WHERE id = (?)",
                id);
    }

    public List<Employee> findByCompanyId(Integer id) {
            String sql = "SELECT * from TBL_EMPLOYEE where companyId = ?";
            return jtm.query(sql, new EmployeeRowMapper(), id);
    }

    public void update(long employeeId, EmployeeUpdateRequest updateRequest) {
        StringJoiner columns = new StringJoiner(" ,").setEmptyValue("");
        Map<String, Object> params = new HashMap<>();
        if (updateRequest.getCompanyId() != null) {
            columns.add("companyId = :companyId");
            params.put("companyId", updateRequest.getCompanyId());
        }
        if (updateRequest.getName() != null) {
            columns.add("name = :name");
            params.put("name", updateRequest.getName());
        }
        if (updateRequest.getSurname() != null) {
            columns.add("surname = :surname");
            params.put("surname", updateRequest.getSurname());
        }
        if (updateRequest.getEmail() != null) {
            columns.add("email = :email");
            params.put("email", updateRequest.getEmail());
        }
        if (updateRequest.getAddress() != null) {
            columns.add("address = :address");
            params.put("address", updateRequest.getAddress());
        }
        if (updateRequest.getSalary() != null) {
            columns.add("salary = :salary");
            params.put("salary", updateRequest.getSalary());
        }
        if(columns.length() == 0) {
            return;
        }
        String sql = "UPDATE TBL_EMPLOYEE SET "+columns+" WHERE id = :employeeId";
        params.put("employeeId", employeeId);
        namedParamJdbc.update(sql,
                params);
    }
}

package com.study.service;

import com.study.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmpoyeeMapperImpl implements EmployeeMapper {

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public void addEmployee(Map<String, Object> map) {

    }

    @Override
    public List<Map<String, Object>> getAll() {
        return employeeMapper.getAll();
    }

    @Override
    public List<Map<String, Object>> getEmployeeById(Integer id) {
        return employeeMapper.getEmployeeById(id);
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeMapper.deleteEmployee(id);
    }

    @Override
    public void updateEmployee(Map<String, Object> map) {

    }
}

package com.study.service;

import com.study.mapper.DepartmentMapper;
import com.study.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentMapperImpl implements DepartmentMapper {

    @Autowired
    DepartmentMapper departmentMapper;
    @Override
    public List<Department> getDepartments() {
        return departmentMapper.getDepartments();
    }

    @Override
    public Department getDepartment(Integer id) {
        return departmentMapper.getDepartment(id);
    }

    @Override
    public void addDepartment(Department department) {
        departmentMapper.addDepartment(department);
    }

    @Override
    public void delDepartment(Integer id) {
        departmentMapper.delDepartment(id);
    }
}

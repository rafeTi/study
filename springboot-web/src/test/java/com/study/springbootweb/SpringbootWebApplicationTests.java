package com.study.springbootweb;

import com.study.mapper.AdminMapper;
import com.study.mapper.DepartmentMapper;
import com.study.mapper.EmployeeMapper;
import com.study.pojo.Admin;
import com.study.pojo.Department;
import com.study.pojo.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SpringBootTest
class SpringbootWebApplicationTests {


    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    AdminMapper adminMapper;

    @Test
    void contextLoads() {
        Department department;
        List<Map<String, Object>> list = employeeMapper.getEmployeeById(1);
        System.out.println(list);
        List<Employee> employees=new ArrayList<>();
        for (Map<String, Object> map : list) {
            Employee employee=new Employee();
            employee.setId((Integer) map.get("id"));
            employee.setLastName((String) map.get("lastName"));
            employee.setGender((Integer) map.get("gender"));
            employee.setEmail((String) map.get("email"));
            employee.setBirth((Date) map.get("birth"));
            department = departmentMapper.getDepartment((Integer) map.get("departmentId"));
            employee.setDepartment(department);
            employees.add(employee);
        }

        System.out.println(employees);
    }

    @Test
    public void Admin(){

        Admin admin = adminMapper.selectByPrimaryKey(1);
        System.out.println(admin.toString());
    }

}

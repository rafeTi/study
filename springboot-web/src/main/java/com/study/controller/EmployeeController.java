package com.study.controller;

import com.study.mapper.DepartmentMapper;
import com.study.mapper.EmployeeMapper;
import com.study.pojo.Department;
import com.study.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private DepartmentMapper departmentMapper;


    @RequestMapping("/emps")
    public String list(Model model){
        Department department;
        List<Map<String, Object>> list = employeeMapper.getAll();
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


        model.addAttribute("emps",employees);
        return "emp/list";
    }

    @RequestMapping("/empsUser")
    public String listUer(Model model){
        Department department;
        List<Map<String, Object>> list = employeeMapper.getAll();
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


        model.addAttribute("emps",employees);
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddPage(Model model){
        //???????????????????????????
        Collection<Department> departments = departmentMapper.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/add";
    }

    @PostMapping("/emp")
    public String addEmp(Employee employee){
        System.out.println("employee="+employee);
        Map<String , Object> map = new HashMap<>();
        map.put("lastName",employee.getLastName());
        map.put("gender",employee.getGender());
        map.put("email",employee.getEmail());
        map.put("birth",employee.getBirth());
        map.put("departmentId",employee.getDepartment().getId());
        employeeMapper.addEmployee(map);//??????????????????????????????????????????
        return "redirect:/empsUser";
    }

    @GetMapping("/emp/{id}")
    public String ToUpdateEmp(@PathVariable("id") Integer id,Model model){

        Employee employee=new Employee();
        Department department;
        List<Map<String, Object>> list = employeeMapper.getEmployeeById(id);
        List<Employee> employees=new ArrayList<>();
        for (Map<String, Object> map : list) {
            employee.setId((Integer) map.get("id"));
            employee.setLastName((String) map.get("lastName"));
            employee.setGender((Integer) map.get("gender"));
            employee.setEmail((String) map.get("email"));
            employee.setBirth((Date) map.get("birth"));
            department = departmentMapper.getDepartment((Integer) map.get("departmentId"));
            employee.setDepartment(department);
            employees.add(employee);
        }
        //?????????????????????
        model.addAttribute("emp",employees.get(0));

        //???????????????????????????
        Collection<Department> departments = departmentMapper.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/update";
    }

    @PostMapping("/updateEmp")
    public String UpdateEmp(Employee employee){
        Map<String , Object> map = new HashMap<>();
        map.put("id",employee.getId());
        map.put("lastName",employee.getLastName());
        map.put("gender",employee.getGender());
        map.put("email",employee.getEmail());
        map.put("birth",employee.getBirth());
        map.put("departmentId",employee.getDepartment().getId());
        employeeMapper.updateEmployee(map);
        return "redirect:/empsUser";
    }

    @GetMapping("/delemp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        employeeMapper.deleteEmployee(id);
        return "redirect:/empsUser";
    }
}

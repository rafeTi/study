package com.study.controller;


import com.study.mapper.DepartmentMapper;
import com.study.mapper.EmployeeMapper;
import com.study.pojo.Department;
import com.study.uitl.excel.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class ExportExcelController {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @RequestMapping("/export")
    public void Excel(HttpServletResponse response){

        List<Object> head = Arrays.asList("id","名字","邮箱","性别","部门","生日");
        Department department;
        List<Map<String, Object>> list = employeeMapper.getAll();
        List<List<Object>> sheetDataList = new ArrayList<>();
        sheetDataList.add(head);
        for (Map<String, Object> map : list) {
            List<Object> employees=new ArrayList<>();
            employees.add(map.get("id"));
            employees.add(map.get("lastName"));
            employees.add( map.get("email"));
            employees.add( ((Integer)map.get("gender")==0?"女":"男"));
            department = departmentMapper.getDepartment((Integer) map.get("departmentId"));
            employees.add(department.getDepartmentName());
            employees.add( map.get("birth"));
            sheetDataList.add(employees);
        }

        // 导出数据
        ExcelUtils.export(response,"用户表", sheetDataList);
    }

}

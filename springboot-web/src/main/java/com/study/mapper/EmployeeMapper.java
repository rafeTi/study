package com.study.mapper;

import com.study.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

//员工Mapper
@Mapper
@Repository
public interface EmployeeMapper {
    /*//模拟数据库中的数据
    private static Map<Integer, Employee> employees = null;

    //员工有所属的部门
    @Autowired
    private DepartmentMapper departmentMapper;

    static {
        employees=new HashMap<Integer, Employee>();//创建一个员工表

        employees.put(1001,new Employee(1001,"AA","A791815782@qq.com",0,new Department(101,"教学部"),new Date()));
        employees.put(1002,new Employee(1002,"BB","B791815782@qq.com",1,new Department(102,"市场部"),new Date()));
        employees.put(1003,new Employee(1003,"CC","C791815782@qq.com",0,new Department(103,"教研部"),new Date()));
        employees.put(1004,new Employee(1004,"DD","D791815782@qq.com",1,new Department(104,"运营部"),new Date()));
        employees.put(1005,new Employee(1005,"EE","E791815782@qq.com",0,new Department(105,"后勤部"),new Date()));

    }*/

    //增加一个员工
    void addEmployee(Map<String,Object> map);


    //查询全部员工
    List<Map<String,Object>> getAll();

    //通过id查询员工
    List<Map<String,Object>> getEmployeeById(Integer id);

    //通过id删除员工
    void deleteEmployee(Integer id);

    void updateEmployee(Map<String,Object> map);
}

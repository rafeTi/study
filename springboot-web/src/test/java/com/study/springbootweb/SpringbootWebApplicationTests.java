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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
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

    @Autowired
    JavaMailSenderImpl javaMailSender;

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

    @Test
    public void TestMail(){

        //一个简单的邮件
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("hello,world");
        simpleMailMessage.setText("123456789");
        simpleMailMessage.setTo("791815782@qq.com");
        simpleMailMessage.setFrom("791815782@qq.com");
        javaMailSender.send(simpleMailMessage);

    }

    @Test
    public void TestMail_2() throws MessagingException {

        //一个复杂的邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        //组装
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setSubject("hello,world");
        mimeMessageHelper.setText("<p style='color:red'>hello!world<p/>",true);

        //附件
        mimeMessageHelper.addAttachment("1.jpg",new File("C:\\Users\\Rafe\\Desktop\\1.jpg"));
        mimeMessageHelper.addAttachment("2.jpg",new File("C:\\Users\\Rafe\\Desktop\\1.jpg"));
        mimeMessageHelper.setTo("791815782@qq.com");
        mimeMessageHelper.setFrom("791815782@qq.com");
        javaMailSender.send(mimeMessage);

    }

}

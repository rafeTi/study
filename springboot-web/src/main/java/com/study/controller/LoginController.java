package com.study.controller;

import com.study.mapper.AdminMapper;
import com.study.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    AdminMapper adminMapper;

    @RequestMapping("/user/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model, HttpSession session){

        Admin admin=adminMapper.selectByPrimaryKey(1);
        if(StringUtils.hasLength(username)&&admin.getName().equals(username)&&admin.getPwd().equals(password)){
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }else {
            model.addAttribute("msg","用户名和密码错误");
            return "index";
        }
    }


    @RequestMapping("/user/logout")
    public String logOut(HttpSession session){
        session.invalidate();
        return "redirect:/index.html";
    }
}

package com.study.config;

import com.study.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsImpl userDetailsImpl;


    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //请求授权的规则
        http.authorizeHttpRequests().antMatchers("/").permitAll()
                .antMatchers("/emps/**").hasRole("admin");

        //没有权限会默认到登录页，需要开启登录的页面
        http.formLogin();

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    //认证
    //密码编码，在SpringSecurity5.x+ 新增了很多的加密方法
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("admin").password(new BCryptPasswordEncoder().encode("123456")).roles("admin")
//                .and()
//                .withUser("tian").password(new BCryptPasswordEncoder().encode("123456")).roles("tourist");

        auth.userDetailsService(userDetailsImpl).passwordEncoder(bCryptPasswordEncoder());
    }
}
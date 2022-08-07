package com.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringbootWebApplication extends SpringBootServletInitializer {

    @Override  //这个表示使用外部的tomcat容器
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的启动类
        return builder.sources(SpringbootWebApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebApplication.class,args);
    }
}

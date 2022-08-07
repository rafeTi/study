package com.study.service;

import com.study.mapper.AdminMapper;
import com.study.mapper.UserService;
import com.study.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsImpl implements UserService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Admin admin = adminMapper.selectByUsername(username);
        if(admin==null){
            return null;
        }

        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        //路径权限规则匹配中配置的是:ADMIN 这里程序猿不可以配置ROLE_开头的角色 不然直接报BUG
        //自定义权限验证中就要配置用户的权限:ROLE_ADMIN 需要加上ROLE_开头
        authorities.add(new SimpleGrantedAuthority("ROLE_admin"));
        UserDetails userDetails=new User(username,new BCryptPasswordEncoder().encode(admin.getPwd()),authorities);

        return userDetails;
    }
}

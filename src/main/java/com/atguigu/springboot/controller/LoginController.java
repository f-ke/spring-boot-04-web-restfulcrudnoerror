package com.atguigu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller

public class LoginController {
    @PostMapping(value = "/user/login")
//    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session){
        if(!StringUtils.isEmpty(username) && "654321".equals(password)){
            //login sucees
            //为了表单重复提交可以重定向到主页
            session.setAttribute("loginuser",username);
            return "redirect:/main.html";
//            return "dashboard";
        }else{
            map.put("msg", "error for password");

        return"/login";
        }
    }
}

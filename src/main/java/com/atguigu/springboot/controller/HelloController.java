package com.atguigu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloController {
//    @ResponseBody
//    @RequestMapping("/hello")
//    public  String hello(){
//    return "hello world";
//    }
    @RequestMapping("/index.html")
    public String index(){
        return("/login");
   }
    @RequestMapping("/test")
    public String test(Map<String,Object> map){
        map.put("test", "hello");
        map.put("test2", "lADY");
        map.put("user", Arrays.asList("zhangsan","lisi"));
        return "/test";
    }
}

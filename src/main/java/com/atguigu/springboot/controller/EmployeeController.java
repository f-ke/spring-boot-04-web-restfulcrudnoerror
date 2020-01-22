package com.atguigu.springboot.controller;

import com.atguigu.springboot.dao.DepartmentDao;
import com.atguigu.springboot.dao.EmployeeDao;
import com.atguigu.springboot.entities.Department;
import com.atguigu.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@Controller
public class EmployeeController {
    //查询所有员工返回列表页面
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;
    @GetMapping("/emps")
    public  String list(Model model){
      Collection<Employee>  employees = employeeDao.getAll();
      //放在请求域中获得共享
        model.addAttribute("emps", employees);
        return"emp/list";
    }
    //来到员工添加路径
    @GetMapping("/emp")
    public String toAddPage(Model model){
        //来到页面前先查出部分
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }
    //员工添加
    @PostMapping("/emp")
    public String addEMployee(Employee employee){
        System.out.println("imformation" + employee);

        //save
        employeeDao.save(employee);

        return "redirect:/emps";//redirect:重定向到某个定制

    }
    //来到修改页面
    @GetMapping("/emp/{id}")
    public String toEditpage(@PathVariable("id") int id, Model model){
        Employee employee = employeeDao.get(id);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("emp", employee);
        model.addAttribute("depts",departments);
        //回到修改页面,修改添加二合一页面

        return "emp/add";
    }
    //员工修改
    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        System.out.println("employee = " + employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    //deleted
    @DeleteMapping("/emp/{id}")
    public  String deleteEmployee(@PathVariable("id") int id){
        employeeDao.delete(id);
        return"redirect:/emps";

    }
}

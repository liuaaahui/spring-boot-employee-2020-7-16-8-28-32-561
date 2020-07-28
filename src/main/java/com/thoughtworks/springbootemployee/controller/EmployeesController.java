package com.thoughtworks.springbootemployee.controller;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    @GetMapping
    public List<Employee> getAllEmployees(){
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(4,"alibaba1",20,"male",6000));
        employees.add(new Employee(11,"tengxun2",19,"female",7000));
        employees.add(new Employee(6,"alibaba3",19,"male",8000));
        employees.add(new Employee(13,"alibaba4",20,"female",9000));
        return employees;
    }
}

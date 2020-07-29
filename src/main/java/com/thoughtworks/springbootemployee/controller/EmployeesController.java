package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    List<Employee> employees = createNewEmployees();

    @GetMapping
    public List<Employee> getEmployees(@RequestParam(value = "page",required = false) Integer page, @RequestParam(value = "pageSize",required = false) Integer pageSize, @RequestParam(value = "gender",required = false) String gender){
        if (page == null&&pageSize == null&&gender == null){
            return employees;
        }else{
            if (gender == null)
                return employees.stream().skip((page-1)*pageSize).limit(pageSize).collect(Collectors.toList());
            List<Employee> displayEmployees = new ArrayList<>();
            for (Employee employee : employees) {
                if (employee.getGender().equals(gender)) {
                    displayEmployees.add(employee);
                }
            }
            return displayEmployees;
        }
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return new Employee(employee.getId(), employee.getName(), employee.getAge(), employee.getGender(), employee.getSalary());
    }

    @PutMapping("/{id}")
    public Employee updateEmployeeById(@PathVariable int id, @RequestBody Employee updateEmployee) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employee.setGender(updateEmployee.getGender());
                employee.setAge(updateEmployee.getAge());
                employee.setName(updateEmployee.getName());
                employee.setSalary(updateEmployee.getSalary());
                return employee;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Employee deleteEmployeeById(@PathVariable int id) {
        for (int index = 0; index < employees.size(); index++) {
            if (employees.get(index).getId() == id) {
                return employees.remove(index);
            }
        }
        return null;
    }

    private List<Employee> createNewEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(4, "alibaba1", 20, "male", 6000));
        employees.add(new Employee(11, "tengxun2", 19, "female", 7000));
        employees.add(new Employee(6, "alibaba3", 19, "male", 8000));
        employees.add(new Employee(13, "alibaba4", 20, "female", 9000));
        employees.add(new Employee(15, "alibaba5", 19, "male", 5000));
        employees.add(new Employee(17, "alibaba6", 20, "female", 4000));
        return employees;
    }
}

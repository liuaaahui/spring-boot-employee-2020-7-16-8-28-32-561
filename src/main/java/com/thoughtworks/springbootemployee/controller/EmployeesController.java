package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
//    @GetMapping
//    public List<Employee> getAllEmployees(){
//        return createNewEmployees();
//    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id){
        List<Employee> employees = createNewEmployees();
        for (int index = 0;index<employees.size();index++){
            if(employees.get(index).getId()==id){
                return employees.get(index);
            }
        }
        return null;
    }

//    @GetMapping
//    public List<Employee> getEmployeesByPage(@RequestParam int page, @RequestParam int pageSize){
//        List<Employee> employees = createNewEmployees();
//        int beginIndex = (page-1)*pageSize;
//        int endIndex = page*pageSize-1;
//        List<Employee> displayEmployees = new ArrayList<>();
//        for (;beginIndex<=endIndex&&beginIndex<employees.size();beginIndex++){
//            displayEmployees.add(employees.get(beginIndex));
//        }
//        return displayEmployees;
//    }

    @GetMapping
    public List<Employee> getEmployeesByGender(@RequestParam String gender){
        List<Employee> employees = createNewEmployees();
        List<Employee> displayEmployees = new ArrayList<>();
        for (int index = 0;index<employees.size();index++){
            if(employees.get(index).getGender().equals(gender)){
                displayEmployees.add(employees.get(index));
            }
        }
        return displayEmployees;
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee){
        return new Employee(employee.getId(),employee.getName(),employee.getAge(),employee.getGender(),employee.getSalary());
    }

    @PutMapping("/{id}")
    public Employee updateEmployeeById(@PathVariable int id,@RequestBody Employee employee){
        List<Employee> employees = createNewEmployees();
        for (int index=0;index<employees.size();index++){
            if(employees.get(index).getId()==id){
                employees.get(index).setGender(employee.getGender());
                employees.get(index).setAge(employee.getAge());
                employees.get(index).setName(employee.getName());
                employees.get(index).setSalary(employee.getSalary());
                return employees.get(index);
            }
        }
        return null;
    }

    private List<Employee> createNewEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(4,"alibaba1",20,"male",6000));
        employees.add(new Employee(11,"tengxun2",19,"female",7000));
        employees.add(new Employee(6,"alibaba3",19,"male",8000));
        employees.add(new Employee(13,"alibaba4",20,"female",9000));
        employees.add(new Employee(15,"alibaba5",19,"male",5000));
        employees.add(new Employee(17,"alibaba6",20,"female",4000));
        return employees;
    }
}

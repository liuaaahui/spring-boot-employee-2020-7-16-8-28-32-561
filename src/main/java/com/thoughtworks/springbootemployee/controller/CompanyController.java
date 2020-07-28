package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @GetMapping
    public List<Company> getAllCompany(){
        List<Company> companies =createNewCompany();

        return companies;
    }
    @GetMapping("/{id}")
    public Company getCompany(@PathVariable int id){
        List<Company> companies =createNewCompany();
        for(int i = 0; i < companies.size(); i++){
            if(companies.get(i).getId() == id){
                return companies.get(i);
            }
        }
        return null;
    }
    @GetMapping("/{id}/employees")
    public List<Employee> getEmployees(@PathVariable int id){
        List<Company> companies = createNewCompany();
        for(int i = 0; i < companies.size(); i++){
            if(companies.get(i).getId() == id){
                return companies.get(i).getEmployees();
            }
        }
        return null;
    }
    @GetMapping("/companies")
    public List<Company> getCompaniesByPage(@PathVariable int page,@PathVariable int pageSize){
        List<Company> companies = createNewCompany();
        int beginIndex = (page-1)*pageSize;
        int endIndex = page*pageSize-1;
        List<Company> displayCompanies = new ArrayList<>();
        for (;beginIndex<=endIndex&&beginIndex<companies.size();beginIndex++){
            displayCompanies.add(companies.get(beginIndex));
        }
        return displayCompanies;
    }

    private List<Company> createNewCompany() {
        List<Company> companies = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,"alibaba1",20,"male",6000));
        employees.add(new Employee(2,"tengxun2",19,"female",7000));
        employees.add(new Employee(3,"alibaba3",19,"male",8000));
        companies.add(new Company(1,"alibaba",200,employees));
        return companies;
    }
}

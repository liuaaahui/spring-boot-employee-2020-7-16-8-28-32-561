package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    List<Company> companies =createNewCompany();

    @GetMapping
    public List<Company> getCompanies(@RequestParam(value = "page",required = false) Integer page, @RequestParam(value = "pageSize",required = false) Integer pageSize){
        if (page == null || pageSize == null)
            return companies;
        return companies.stream().skip((page-1)*pageSize).limit(pageSize).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Company getCompany(@PathVariable int id) {
        List<Company> companies = createNewCompany();
        for (Company company : companies) {
            if (company.getId() == id) {
                return company;
            }
        }
        return null;
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployees(@PathVariable int id) {
        List<Company> companies = createNewCompany();
        for (Company company : companies) {
            if (company.getId() == id) {
                return company.getEmployees();
            }
        }
        return null;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Company addCompany(@RequestBody Company company) {
        return new Company(company.getId(), company.getCompanyName(), company.getEmployeesNumber(), company.getEmployees());
    }

    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable int id, @RequestBody Company updateCompany) {
        List<Company> companies = createNewCompany();
        for (Company company : companies) {
            if (company.getId() == id) {
                company.setCompanyName(updateCompany.getCompanyName());
                company.setEmployees(updateCompany.getEmployees());
                company.setEmployeesNumber(updateCompany.getEmployeesNumber());
                return company;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Company deleteCompanyById(@PathVariable int id) {
        List<Company> companies = createNewCompany();
        for (int index = 0; index < companies.size(); index++) {
            if (companies.get(index).getId() == id) {
                return companies.remove(index);
            }
        }
        return null;
    }

    private List<Company> createNewCompany() {
        List<Company> companies = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "alibaba1", 20, "male", 6000));
        employees.add(new Employee(2, "tengxun2", 19, "female", 7000));
        employees.add(new Employee(3, "alibaba3", 19, "male", 8000));
        companies.add(new Company(1, "alibaba", 200, employees));
        return companies;
    }
}

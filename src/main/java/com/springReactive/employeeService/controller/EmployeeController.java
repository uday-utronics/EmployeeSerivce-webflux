package com.springReactive.employeeService.controller;

import com.springReactive.employeeService.model.Employee;
import com.springReactive.employeeService.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/byID/{emp_id}")
    public Flux<Employee> getEmployeeById(@PathVariable int emp_id){
        return employeeRepository.findByemployeeId(emp_id);
    }

    @GetMapping("/getAll")
    public Flux<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }

    @PostMapping("/create")
    public Mono<Employee> createEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }
}

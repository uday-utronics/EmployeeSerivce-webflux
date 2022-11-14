package com.springReactive.employeeService.controller;

import com.springReactive.employeeService.model.Employee;
import com.springReactive.employeeService.model.EmployeeSkillSet;
import com.springReactive.employeeService.repository.EmployeeSkillSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/skillset")
public class SkillSetController {

    @Autowired
    EmployeeSkillSetRepository employeeSkillSetRepository;

    @GetMapping("/byID/{emp_id}")
    public Mono<EmployeeSkillSet> getSkillById(@PathVariable int emp_id){
        return employeeSkillSetRepository.findByemployeeId(emp_id);
    }

    @GetMapping("/byExp/{java_exp}")
    public Flux<EmployeeSkillSet> getjavaExp(@PathVariable double java_exp){
        return employeeSkillSetRepository.findByjavaExperienceGreaterThan(java_exp);
    }
    @GetMapping("/getAll")
    public Flux<EmployeeSkillSet> getAllSkill(){
        return employeeSkillSetRepository.findAll();
    }

    @PostMapping("/create")
    public Mono<EmployeeSkillSet> createEmployee(@RequestBody EmployeeSkillSet skillSet){

        return employeeSkillSetRepository.save(skillSet);
    }
}

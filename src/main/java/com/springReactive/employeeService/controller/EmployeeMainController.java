package com.springReactive.employeeService.controller;

import com.springReactive.employeeService.model.EmployeeRequest;
import com.springReactive.employeeService.model.EmployeeResponse;
import com.springReactive.employeeService.service.EmployeeMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/empmain")
public class EmployeeMainController {


    @Autowired
    private EmployeeMainService employeeMainService;

    @PostMapping("/create")
    Mono<EmployeeResponse> createEmployee(@RequestBody @Valid EmployeeRequest employeeRequest){
        return employeeMainService.createEmployeeMain(employeeRequest);
    }

    @GetMapping("/ExpgreaterThan/{exp}")
    Flux<EmployeeRequest> findAllGreater(@PathVariable double exp){
        return employeeMainService.findByJavaExp(exp);
    }
}

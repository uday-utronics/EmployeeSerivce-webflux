package com.springReactive.employeeService.service;

import com.springReactive.employeeService.model.Employee;
import com.springReactive.employeeService.model.EmployeeRequest;
import com.springReactive.employeeService.model.EmployeeResponse;
import com.springReactive.employeeService.model.EmployeeSkillSet;
import com.springReactive.employeeService.repository.EmployeeRepository;
import com.springReactive.employeeService.repository.EmployeeSkillSetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeMainService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Value(value = "${PRODUCER_TOPIC}")
    private String topic;

    @Autowired
    private KafkaProducerEmp kafkaProducerEmp;
    @Autowired
    private EmployeeSkillSetRepository employeeSkillSetRepository;

    Logger log = LoggerFactory.getLogger(EmployeeMainService.class);

    public Mono<EmployeeResponse> createEmployeeMain(EmployeeRequest employeeRequest) {

        log.debug("inside create Employee" + employeeRequest.getEmployeeId());

        String status = "";

        kafkaProducerEmp.sendMessages(employeeRequest);

        Employee employee = new Employee(employeeRequest.getEmployeeId(), employeeRequest.getEmployeeName(),
                employeeRequest.getEmployeeCity(), employeeRequest.getEmployeePhone());
        EmployeeSkillSet employeeSkillSet = new EmployeeSkillSet(employeeRequest.getEmployeeId(), employeeRequest.getJavaExperience(),
                employeeRequest.getSpringExperience());

        return employeeRepository.existsByemployeeId(employeeRequest.getEmployeeId())
                .flatMap(exists -> {
                    if (exists) {
                        log.debug("Employee already exists");
                        return Mono.zip(
                                Mono.just(employee), Mono.just(employeeSkillSet)
                        ).map(t -> new EmployeeResponse(t.getT1().getEmployeeId(),
                                t.getT1().getEmployeeName(), t.getT1().getEmployeeCity(), t.getT1().getEmployeePhone(),
                                t.getT2().getJavaExperience(), t.getT2().getSpringExperience(), "Already Exists"));
                    } else {
                        log.debug("Employee Create new");
                        return Mono.zip(Mono.just(employee)
                                                .flatMap(employeeRepository::save).log("Employee object")
                                        , Mono.just(employeeSkillSet).flatMap(employeeSkillSetRepository::save).log("Employee skill set object"))
                                .map(t -> new EmployeeResponse(t.getT1().getEmployeeId(),
                                        t.getT1().getEmployeeName(), t.getT1().getEmployeeCity(), t.getT1().getEmployeePhone(),
                                        t.getT2().getJavaExperience(), t.getT2().getSpringExperience(), "Created"));

                    }

                });

    }

    public Flux<EmployeeRequest> findByJavaExp(double java_exp){


        Flux<EmployeeSkillSet> skillSetFlux = employeeSkillSetRepository.findByjavaExperienceGreaterThan(java_exp);
        Flux<Employee> employeeFlux = skillSetFlux.concatMap(emp-> { return employeeRepository.findByemployeeId(emp.getEmployeeId());});
        Flux<EmployeeRequest> employeeRequestFlux = Flux.zip(employeeFlux,skillSetFlux).map(t-> new EmployeeRequest(t.getT1().getEmployeeId(),
                t.getT1().getEmployeeName(),t.getT1().getEmployeeCity(),
                t.getT1().getEmployeePhone(),t.getT2().getJavaExperience(),
                t.getT2().getSpringExperience()));

        return employeeRequestFlux;
    }

}

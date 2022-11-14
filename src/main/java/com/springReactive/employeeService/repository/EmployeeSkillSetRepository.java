package com.springReactive.employeeService.repository;

import com.springReactive.employeeService.model.EmployeeSkillSet;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface EmployeeSkillSetRepository extends ReactiveCassandraRepository<EmployeeSkillSet, Integer> {

    @AllowFiltering
    Mono<EmployeeSkillSet> findByemployeeId(Integer emp_id);

    @AllowFiltering
    Flux<EmployeeSkillSet> findByjavaExperience(double java_exp);

    @AllowFiltering
    Flux<EmployeeSkillSet> findByjavaExperienceGreaterThan(double java_exp);
    @AllowFiltering
    Flux<EmployeeSkillSet> findByspringExperience(double spring_exp);
}

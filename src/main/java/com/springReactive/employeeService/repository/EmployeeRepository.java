package com.springReactive.employeeService.repository;

import com.springReactive.employeeService.model.Employee;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface EmployeeRepository extends ReactiveCassandraRepository<Employee, Integer> {

    @AllowFiltering
    @Query(value ="emp_name" )
    Flux<Employee> findByEMP_NAME(String emp_name);

    Flux<Employee> findByemployeeId(Integer emp_id);

    Mono<Boolean> existsByemployeeId(Integer emp_id);
}

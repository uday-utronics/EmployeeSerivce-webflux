package com.springReactive.employeeService.config;

import java.lang.Object;

import com.springReactive.employeeService.model.EmployeeRequest;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

//@Configuration
public class ReactiveKafkaProducerConfig  {

//    @Bean
//    public ReactiveKafkaProducerTemplate<String, EmployeeRequest> reactiveKafkaProducerTemplate(
//            KafkaProperties properties) {
//        Map<String, Object> props = properties.buildProducerProperties();
//
//        return new ReactiveKafkaProducerTemplate<String, EmployeeRequest>(SenderOptions.create(props));
//    }

}

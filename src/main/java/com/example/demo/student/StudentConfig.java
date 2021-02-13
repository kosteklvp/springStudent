package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

  @Bean
  CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
    return args -> {
      Student adamStudent = new Student("Adam", "adam.dobrze.wkladam@gmail.com", LocalDate.of(1996, Month.AUGUST, 3));
      Student mariuszStudent = new Student("Mariusz", "mariusz.pudzianowski@gmail.com", LocalDate.of(1992, Month.AUGUST, 31));

      studentRepository.saveAll(List.of(adamStudent, mariuszStudent));
    };

  };
}

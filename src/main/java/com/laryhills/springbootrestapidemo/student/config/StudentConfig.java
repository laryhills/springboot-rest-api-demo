package com.laryhills.springbootrestapidemo.student.config;

import com.laryhills.springbootrestapidemo.student.model.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.laryhills.springbootrestapidemo.student.repository.StudentRepository;

import java.time.LocalDate;
import java.time.Month;

import java.util.List;

@Configuration
public class StudentConfig {

  /*
   * A command line runner is a way to execute specific code when an application
   * is fully started.
   * this populates the db with data
   */
  @Bean
  CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
    return args -> {
      Student lary = new Student(
          "Lary",
          "lary@example.com",
          LocalDate.of(2000, Month.JANUARY, 5));
      Student alex = new Student(
          "Alex",
          "alex@example.com",
          LocalDate.of(2004, Month.MARCH, 5));
      Student maria = new Student(
          "Maria",
          "maria@example.com",
          LocalDate.of(2002, Month.SEPTEMBER, 5));

      studentRepository.saveAll(
          List.of(lary, alex, maria));
    };
  }

  ;

}

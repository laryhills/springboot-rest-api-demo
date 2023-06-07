package com.laryhills.springbootrestapidemo.student.controller;

import com.laryhills.springbootrestapidemo.student.model.Student;
import com.laryhills.springbootrestapidemo.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
    API Layer
    Controller has resources for api

    containing routes
 */
@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {

  private final StudentService studentService; // for dependency injection

  @Autowired // coupled with a bean
  public StudentController(StudentService studentService) {
    // studentService is injected here
    this.studentService = studentService;
  }

  @GetMapping
  public List<Student> getStudents() {
    return studentService.getStudents();
  }

  // @PostMapping
  // public void registerNewStudent(@RequestBody Student student) {
  // // add student and return a response
  // studentService.addNewStudent(student);

  // }

  @PostMapping
  public ResponseEntity<Student> registerNewStudent(@RequestBody Student student) {
    // Add student and return a response
    Student addedStudent = studentService.addNewStudent(student);
    return ResponseEntity.status(HttpStatus.CREATED).body(addedStudent);
  }

  @DeleteMapping(path = "{studentId}")
  public ResponseEntity<Void> deleteStudent(@PathVariable("studentId") Long studentId) {
    studentService.deleteStudent(studentId);
    return ResponseEntity.noContent().build();
  }

  @PutMapping(path = "{studentId}")
  public ResponseEntity<Student> updateStudent(@PathVariable("studentId") Long studentId,
      @RequestBody Student student) {
    student.setId(studentId); // Set the ID of the student from the URL path
    Student updatedStudent = studentService.updateStudent(student);
    return ResponseEntity.ok(updatedStudent);
  }
}
